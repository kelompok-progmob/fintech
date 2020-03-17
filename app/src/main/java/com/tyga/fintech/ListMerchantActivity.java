package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tyga.fintech.adapter.MerchantAdapter;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.ActivityListMerchantBinding;
import com.tyga.fintech.model.Lpd;
import com.tyga.fintech.model.Merchant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMerchantActivity extends AppCompatActivity {

    ApiService service;
    TokenManager tokenManager;

    private List<Merchant> listMerchant = new ArrayList<>();
    private RecyclerView mMerchantRv;
    ActivityListMerchantBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_list_merchant);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("List Merchant");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        callLPD();
        callApi();
    }

    private void callLPD(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this)
                .getLpdNasabah()
                .enqueue(new Callback<List<Lpd>>() {
                    @Override
                    public void onResponse(Call<List<Lpd>> call, Response<List<Lpd>> response) {
                        if (response.body() != null){
                            binding.lpdListMerchant.setText(response.body().get(0).getNama());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Lpd>> call, Throwable t) {

                    }
                });
    }

    private void callApi(){
        service = ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this);
        service
                .getMerchant()
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
        binding.listMerchantRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MerchantAdapter merchantAdapter = new MerchantAdapter(this, new MerchantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Merchant merchant) {
                Intent intent = new Intent(getApplicationContext(), DetailMerchantActivity.class);
                intent.putExtra("merchant", merchant);
                startActivity(intent);
            }
        });
        merchantAdapter.setListMerchant(listMerchant);
        binding.listMerchantRecyclerView.setAdapter(merchantAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
