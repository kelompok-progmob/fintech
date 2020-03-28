package com.tyga.fintech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.ActivityValidasiTransaksiBinding;
import com.tyga.fintech.model.ResponseMessage;
import com.tyga.fintech.model.ValidasiTransaksi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidasiTransaksiActivity extends AppCompatActivity {

    ActivityValidasiTransaksiBinding binding;

    TokenManager tokenManager;
    String id_merchant, nominal;
    Double saldo;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_validasi_transaksi);
        tokenManager = TokenManager.getInstance(getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE));
        final SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        if (preferences.contains("saldo_topup")){
            saldo = Double.parseDouble(preferences.getString("saldo_topup",null));
        }
        else{
            saldo = 0.0;
        }

        Bundle extras = getIntent().getExtras();
        id_merchant = extras.getString("id_merchant");
        nominal = extras.getString("nominal");

        Log.e("Validasi","id_merchant "+id_merchant+" nominal "+nominal);

        getValidasiTransaksi();

        binding.buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValidasiTransaksiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.buttonBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(ValidasiTransaksiActivity.this, "",
                        "Proses. Please wait...", true);
                dialog.setCanceledOnTouchOutside(false);
                insertTransaction(id_merchant,nominal);
            }
        });
    }

    private void getValidasiTransaksi(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this)
                .getValidasiTransaksi(id_merchant,nominal)
                .enqueue(new Callback<ValidasiTransaksi>() {
                    @Override
                    public void onResponse(Call<ValidasiTransaksi> call, Response<ValidasiTransaksi> response) {
                        if (response.body() != null){
                            Log.e("validasi","Validasi not null");
                            binding.total.setText("Rp. "+String.valueOf(response.body().getNominal()));
                            binding.nohp.setText(String.valueOf(response.body().getNohp()));
                            binding.points.setText(String.valueOf(response.body().getPoints()));
                            binding.alamat.setText(String.valueOf(response.body().getAlamat()));
                            binding.nama.setText(String.valueOf(response.body().getNama()));
                        }
                        else{
                            Log.e("validasi","Validasi null");

                        }
                    }

                    @Override
                    public void onFailure(Call<ValidasiTransaksi> call, Throwable t) {

                    }
                });
    }

    private void insertTransaction(String id_merchant, final String nominal){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this)
                .insertTransaksi(nominal,id_merchant)
                .enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        Double nominalDouble = Double.parseDouble(nominal);
                        saldo -= nominalDouble;
                        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("saldo_topup",saldo.toString());
                        editor.apply();
                        dialog.dismiss();
                        Intent intent = new Intent(ValidasiTransaksiActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        Toast.makeText(ValidasiTransaksiActivity.this, "Failed Topup Check your connection", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }
}
