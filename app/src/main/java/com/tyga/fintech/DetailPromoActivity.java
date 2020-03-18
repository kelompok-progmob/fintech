package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tyga.fintech.databinding.ActivityDetailPromoBinding;
import com.tyga.fintech.databinding.ActivityListPromoBinding;
import com.tyga.fintech.model.Promo;

public class DetailPromoActivity extends AppCompatActivity {

    private Promo promo;
    ActivityDetailPromoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_detail_promo);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Detail Promo");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        promo = getIntent().getParcelableExtra("promo");
        Glide.with(this)
                .load(promo.getImage_promo())
                .apply(new RequestOptions())
                .placeholder(R.drawable.placeholder)
                .into(binding.imgDetailPromo);
        binding.nominalDetailPromo.setText(String.valueOf(promo.getNominal()));
        binding.tglmulaiDetailPromo.setText(promo.getTanggal_mulai());
        binding.tglakhirDetailPromo.setText(promo.getTanggal_berakhir());
        binding.syaratDetailPromo.setText(promo.getSyarat_penggunaan());
        //set data
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
