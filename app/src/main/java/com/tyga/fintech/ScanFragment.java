package com.tyga.fintech;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.FragmentScanBinding;
import com.tyga.fintech.model.ResponseMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment {

    private CodeScanner mCodeScanner;
    View rootView;
    FragmentScanBinding binding;
    private TokenManager tokenManager;
    private ProgressDialog dialog;
    Double saldo;
    public ScanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan, container, false);
        rootView = binding.getRoot();

        tokenManager = TokenManager.getInstance(getActivity().getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE));
        final SharedPreferences preferences = getContext().getSharedPreferences("prefs",MODE_PRIVATE);
        if (preferences.contains("saldo_topup")){
            saldo = Double.parseDouble(preferences.getString("saldo_topup",null));
        }
        else{
            saldo = 0.0;
        }

        mCodeScanner = new CodeScanner(getContext(), binding.scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog = ProgressDialog.show(getContext(), "",
                                "Proses. Please wait...", true);
                        dialog.setCanceledOnTouchOutside(false);
                        String[] data = result.getText().split("&");
                        Log.e("data" , data.toString());
                        Log.e("data",String.valueOf(data.length));
                        String id_merchant = data[1];
                        String nominal = data[0];
                        Double nominalInt = Double.parseDouble(data[0]);
                        if (saldo < nominalInt){
                            Toast.makeText(getContext(), "Saldo anda kurang, silakan melakukan topup terlebih dahulu", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScanFragment()).commit();
                            dialog.dismiss();
                        }
                        else{
                            insertTransaction(id_merchant,nominal);
                        }

                    }
                });
            }
        });

        //check permission kamera
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            mKamera.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        else{
            mCodeScanner.startPreview();
        }

        return rootView;
    }

    private void insertTransaction(String id_merchant, final String nominal){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .insertTransaksi(nominal,id_merchant)
                .enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        Double nominalInt = Double.parseDouble(nominal);
                        saldo -= nominalInt;
                        SharedPreferences preferences = getContext().getSharedPreferences("prefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("saldo_topup",saldo.toString());
                        editor.apply();
                        dialog.dismiss();
                        Intent intent = new Intent(getContext(),HistoryNasabahActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed Topup Check your connection", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    //    fungsi untuk meminta permission kamera ke user
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScanFragment()).commit();
                mCodeScanner.startPreview();
            }
        }
    }

}
