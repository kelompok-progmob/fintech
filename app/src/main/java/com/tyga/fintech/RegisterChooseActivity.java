package com.tyga.fintech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterChooseActivity extends AppCompatActivity {
    CardView cvNasabah;
    CardView cvMerchant;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_choose);

        mContext = this;
        initComponents();
    }

    private void initComponents(){
        cvNasabah = (CardView) findViewById(R.id.cv_nasabah_regcho);
        cvMerchant = (CardView) findViewById(R.id.cv_merchant_regcho);

        cvNasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
                finish();
            }
        });

        cvMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterMerchantActivity.class));
                finish();
            }
        });
    }
}
