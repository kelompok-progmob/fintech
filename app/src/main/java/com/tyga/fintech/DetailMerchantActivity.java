package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tyga.fintech.model.Merchant;

public class DetailMerchantActivity extends AppCompatActivity {

    private Merchant merchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_merchant);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Detail Merchant");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        merchant = getIntent().getParcelableExtra("merchant");

        //set data

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
