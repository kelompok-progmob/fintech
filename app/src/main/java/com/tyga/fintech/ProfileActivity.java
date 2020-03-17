package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_profile);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Profile");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        binding.namaProfile.setText(tokenManager.getToken().getNasabah().getNama());
        binding.nikProfile.setText(tokenManager.getToken().getNasabah().getNik());
        binding.telpProfile.setText(tokenManager.getToken().getUser().getNoHP());

    }

    private void callLpd(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
