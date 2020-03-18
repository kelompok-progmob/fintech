package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tyga.fintech.databinding.ActivityDetailMerchantBinding;
import com.tyga.fintech.model.Merchant;

public class DetailMerchantActivity extends AppCompatActivity {

    private Merchant merchant;
    private ActivityDetailMerchantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_detail_merchant);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Detail Merchant");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        merchant = getIntent().getParcelableExtra("merchant");

        //set data
        binding.alamatDetailMerchant.setText(merchant.getAlamat());
        binding.namaDetailMerchant.setText(merchant.getNama());
        binding.telpDetailMerchant.setText(merchant.getNo_hp());
        binding.lpdDetailMerchant.setText(merchant.getNama_lpd());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
