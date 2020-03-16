package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tyga.fintech.adapter.MerchantAdapter;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.model.Merchant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantActivity extends AppCompatActivity {

    ApiService service;
    TokenManager tokenManager;

    private List<Merchant> listMerchant = new ArrayList<>();
    private RecyclerView mMerchantRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.merchant_activity);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Merchant");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

//        callApi();
    }

    private void callApi(String idLpd){
        service = ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this);
        service
                .getMerchant(idLpd)
                .enqueue(new Callback<List<Merchant>>() {
                    @Override
                    public void onResponse(Call<List<Merchant>> call, Response<List<Merchant>> response) {
                        if (response.body() != null){
                            listMerchant = response.body();
                            settingRecyclerView();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Tidak ada data",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Merchant>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failed get data, check your connection!!",Toast.LENGTH_SHORT).show();
                        Log.e("error call api merchant",t.toString()+" dan message = "+t.getMessage());
                    }
                });
    }

    private void settingRecyclerView(){
//        mMerchantRv = findViewById(R.id.);
        mMerchantRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MerchantAdapter merchantAdapter = new MerchantAdapter(this, new MerchantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Merchant merchant) {
                Intent intent = new Intent(getApplicationContext(), DetailMerchantActivity.class);
                intent.putExtra("merchant", merchant);
                startActivity(intent);
            }
        });
        merchantAdapter.setListMerchant(listMerchant);
        mMerchantRv.setAdapter(merchantAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
