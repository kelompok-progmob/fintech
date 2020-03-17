package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tyga.fintech.model.Promo;

public class DetailPromoActivity extends AppCompatActivity {

    private Promo promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Detail Promo");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        promo = getIntent().getParcelableExtra("promo");

        //set data
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
