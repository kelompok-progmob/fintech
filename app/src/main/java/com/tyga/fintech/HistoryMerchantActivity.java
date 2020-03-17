package com.tyga.fintech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tyga.fintech.adapter.HistoryMerchantAdapter;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.model.Transaksi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryMerchantActivity extends AppCompatActivity {

    private List<Transaksi> listTransaksi = new ArrayList<>();
    private RecyclerView mTransaksiRv;
    private TextView saldoMerchant;
    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_merchant);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("History");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        saldoMerchant = findViewById(R.id.saldoHistoryMerchant);
        callApi(String.valueOf(tokenManager.getToken().getUser().getIDUser()));
    }

    private void callApi(String idUser){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, this)
                .getHistoryMerchant()
                .enqueue(new Callback<List<Transaksi>>() {
                    @Override
                    public void onResponse(Call<List<Transaksi>> call, Response<List<Transaksi>> response) {
                        if (response.body() != null){
                            listTransaksi = response.body();
                            settingRecyclerView();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Tidak ada data",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Transaksi>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failed get data, check your connection!!",Toast.LENGTH_SHORT).show();
                        Log.e("error call api history",t.toString()+" dan message = "+t.getMessage());
                    }
                });
    }

    private void settingRecyclerView(){
        mTransaksiRv = findViewById(R.id.rvHistoryMerchant);
        mTransaksiRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        HistoryMerchantAdapter historyMerchantAdapter = new HistoryMerchantAdapter(this, new HistoryMerchantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Transaksi transaksi) {
                double total = 0;
                total += transaksi.getNominal();
                saldoMerchant.setText(String.valueOf(total));
            }
        });
        historyMerchantAdapter.setListTransaksi(listTransaksi);
        mTransaksiRv.setAdapter(historyMerchantAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
