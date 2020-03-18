package com.tyga.fintech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.ActivityTopUpBinding;
import com.tyga.fintech.model.ResponseMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpActivity extends AppCompatActivity {

    private TokenManager tokenManager;
    Double saldo;
    ActivityTopUpBinding binding;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_top_up);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        if (preferences.contains("saldo_topup")){
            saldo = Double.parseDouble(preferences.getString("saldo_topup",null));
        }
        else{
            saldo = 0.0;
        }

        binding.saldoTopUp.setText(String.valueOf(saldo));
        binding.saldoTopUp.setEnabled(false);
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(getApplicationContext(), "",
                        "Proses. Please wait...", true);
                dialog.setCanceledOnTouchOutside(false);
                if(binding.nominalTopUp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Lengkapi Data", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else{
                    insertTopup();
                }
            }
        });

    }

    private void insertTopup(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this)
                .TopupSaldo(binding.nominalTopUp.getText().toString())
                .enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        Double nominalInt = Double.parseDouble(binding.nominalTopUp.getText().toString());
                        saldo += nominalInt;
                        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("saldo_topup",saldo.toString());
                        editor.apply();
                        dialog.dismiss();
                        Intent intent = new Intent(TopUpActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failed Topup Check your connection", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
