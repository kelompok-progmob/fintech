package com.tyga.fintech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.ActivityLoginBinding;
import com.tyga.fintech.model.UserWithToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnRegister;
    Context mContext;

    ActivityLoginBinding binding;

    ApiService service;
    TokenManager tokenManager;
    Call<UserWithToken> call;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_login);
        mContext = this;
        initComponents();

        SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
        if(!settings.getString("no_hp","").equals("")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        service = ApiClient.createService(ApiService.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        binding.buttonSigninLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login(){
        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "Loggin in. Please wait...", true);

        call = service.login(binding.etPhoneLogin.getText().toString(), binding.password.getText().toString());
        call.enqueue(new Callback<UserWithToken>() {
            @Override
            public void onResponse(Call<UserWithToken> call, Response<UserWithToken> response) {

                Log.w("LoginActivity", "onResponse: " + response);

                if (response.isSuccessful()) {
                    tokenManager.saveToken(response.body());
                    if(response.body().getUser().getRole().equals("1")){

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("no_hp",response.body().getUser().getNoHP());
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        startActivity(new Intent(LoginActivity.this, MerchantActivity.class));
                    }
                    finish();
                }

                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserWithToken> call, Throwable t) {
                Log.w("LoginActivity", "onFailure: " + t.getMessage());
            }
        });
    }

    private void initComponents(){
        btnRegister = (Button) findViewById(R.id.button_joinNow_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
                finish();
            }
        });
    }
}
