package com.tyga.fintech;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyga.fintech.adapter.PromoAdapter;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.FragmentHomeBinding;
import com.tyga.fintech.model.MappingUser;
import com.tyga.fintech.model.Promo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    private TokenManager tokenManager;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        rootView = binding.getRoot();

        tokenManager = TokenManager.getInstance(getActivity().getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE));

        binding.textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PromoActivity.class);
                startActivity(intent);
            }
        });

        binding.ivHistoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryNasabahActivity.class);
                startActivity(intent);
            }
        });

        binding.tvHistoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryNasabahActivity.class);
                startActivity(intent);
            }
        });

        binding.ivMerchantHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListMerchantActivity.class);
                startActivity(intent);
            }
        });

        binding.tvMerchantHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListMerchantActivity.class);
                startActivity(intent);
            }
        });

        binding.ivTopupHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopUpActivity.class);
                startActivity(intent);
            }
        });

        binding.tvTopupHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopUpActivity.class);
                startActivity(intent);
            }
        });

        getSaldo();
        callApiPromo();
        return rootView;

    }

    private void callApiPromo(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getPromo()
                .enqueue(new Callback<List<Promo>>() {
                    @Override
                    public void onResponse(Call<List<Promo>> call, Response<List<Promo>> response) {

                        if (response.body() != null){
                            settingRecyclerView(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Promo>> call, Throwable t) {

                    }
                });
    }

    private void settingRecyclerView(List<Promo> promoList){

        binding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewHome.setNestedScrollingEnabled(false);
        PromoAdapter promoAdapter = new PromoAdapter(getContext(), new PromoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Promo promo) {
                Intent intent = new Intent(getContext(), DetailPromoActivity.class);
                intent.putExtra("promo", promo);
                startActivity(intent);
            }
        });
        promoAdapter.setListPromo(promoList);
        binding.recyclerViewHome.setAdapter(promoAdapter);
    }

    private void getSaldo(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getSaldoNasabah()
                .enqueue(new Callback<List<MappingUser>>() {
                    @Override
                    public void onResponse(Call<List<MappingUser>> call, Response<List<MappingUser>> response) {
                        if (response.body() != null){
                            binding.tvNominalsaldoHome.setText(String.valueOf(response.body().get(0).getTotal_saldo_topup()));
                        }
                        else{
                            binding.tvNominalsaldoHome.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MappingUser>> call, Throwable t) {

                    }
                });
    }

}
