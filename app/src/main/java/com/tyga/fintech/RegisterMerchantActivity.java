package com.tyga.fintech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.databinding.ActivityRegisterMerchantBinding;
import com.tyga.fintech.model.Lpd;
import com.tyga.fintech.model.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMerchantActivity extends AppCompatActivity {

    String idLpd;
    ProgressDialog progressDialog;
    ActivityRegisterMerchantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register_merchant);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
            getSupportActionBar().setTitle("Registrasi Nasabah");
        }   //null check

        getLpd();

        binding.buttonRegistrasiMerchantRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.nama.getText().toString().isEmpty() || binding.alamat.getText().toString().isEmpty() || binding.nohp.getText().toString().isEmpty() || binding.pin.getText().toString().isEmpty() || idLpd.isEmpty()){
                    Toast.makeText(RegisterMerchantActivity.this,"Lengkapi data",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog = new ProgressDialog(RegisterMerchantActivity.this);
                    progressDialog.setTitle("Register....");
                    progressDialog.setMessage("Please wait");
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    checkPhoneNumber();
                }

            }
        });

    }

    private void checkPhoneNumber(){
        ApiClient.createService(ApiService.class)
                .checkPhone(binding.nohp.getText().toString())
                .enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        if (response.body() != null){
                            progressDialog.dismiss();
                            if (response.body().getMessage().equals("Available")){

                                Intent intent = new Intent(RegisterMerchantActivity.this, OtpRegisterMerchantActivity.class);
                                intent.putExtra("nama",binding.nama.getText().toString());
                                intent.putExtra("alamat",binding.alamat.getText().toString());
                                intent.putExtra("no_hp",binding.nohp.getText().toString());
                                intent.putExtra("password",binding.pin.getText().toString());
                                intent.putExtra("id_lpd",idLpd);
                                startActivity(intent);
                            }
                            else{
                                binding.nohp.setError("Already Used");
                                Toast.makeText(RegisterMerchantActivity.this, "Phone Number already used",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
    }

    private void getLpd(){
        ApiClient.createService(ApiService.class)
                .allLpd()
                .enqueue(new Callback<List<Lpd>>() {
                    @Override
                    public void onResponse(Call<List<Lpd>> call, Response<List<Lpd>> response) {
                        if (response.body() != null){
                            settingSpinnerLpd(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Lpd>> call, Throwable t) {
                        Log.e("error",t.getMessage()+" gg = "+t.toString());
                    }
                });
    }

    private void settingSpinnerLpd(List<Lpd> lpdList){
        ArrayList<String> label = new ArrayList<>();

        for (int i =0;i<lpdList.size();i++){
            label.add(lpdList.get(i).getNama());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, label);

        binding.spinnerMerchantRegister.setAdapter(adapter);
        idLpd = adapter.getItem(0);
        binding.spinnerMerchantRegister.setSelection(0, true);
        View v = binding.spinnerMerchantRegister.getSelectedView();
        ((TextView)v).setTextColor(Color.parseColor("#000000"));
        // mengeset listener untuk mengetahui saat item dipilih
        binding.spinnerMerchantRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idLpd = adapter.getItem(i);
                ((TextView) view).setTextColor(Color.parseColor("#000000"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
