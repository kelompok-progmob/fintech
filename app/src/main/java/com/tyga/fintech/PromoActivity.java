package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tyga.fintech.adapter.PromoAdapter;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.model.Promo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoActivity extends AppCompatActivity {

    private List<Promo> listPromo = new ArrayList<>();
    private RecyclerView mPromoRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.promo_activity);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Promo");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

//        callApi();
    }

    private void callApi(String idLpd){
        ApiClient.getClient()
                .create(ApiService.class)
                .getPromo(idLpd)
                .enqueue(new Callback<List<Promo>>() {
                    @Override
                    public void onResponse(Call<List<Promo>> call, Response<List<Promo>> response) {
                        if (response.body() != null){
                            listPromo = response.body();
                            settingRecyclerView();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Tidak ada data",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Promo>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failed get data, check your connection!!",Toast.LENGTH_SHORT).show();
                        Log.e("error call api promo",t.toString()+" dan message = "+t.getMessage());
                    }
                });
    }

    private void settingRecyclerView(){
//        mPromoRv = findViewById(R.id.);
        mPromoRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PromoAdapter promoAdapter = new PromoAdapter(this, new PromoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Promo promo) {
                Intent intent = new Intent(getApplicationContext(), DetailPromoActivity.class);
                intent.putExtra("promo", promo);
                startActivity(intent);
            }
        });
        promoAdapter.setListPromo(listPromo);
        mPromoRv.setAdapter(promoAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
