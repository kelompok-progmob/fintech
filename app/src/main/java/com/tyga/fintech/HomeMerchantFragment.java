package com.tyga.fintech;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.FragmentHomeMerchantBinding;
import com.tyga.fintech.model.MappingUser;
import com.tyga.fintech.model.Merchant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class HomeMerchantFragment extends Fragment {

    FragmentHomeMerchantBinding binding;
    private TokenManager tokenManager;
    private View rootView;

    public HomeMerchantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_merchant, container, false);
        rootView = binding.getRoot();

        tokenManager = TokenManager.getInstance(getActivity().getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE));

        binding.namaHomeMerchant.setText(tokenManager.getMerchant().getMerchant().getNama());
        getSaldo();
        getPendapatanToday();
        getTransaksiToday();

        return rootView;
    }

    private void getSaldo(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getSaldoMerchant()
                .enqueue(new Callback<List<Merchant>>() {
                    @Override
                    public void onResponse(Call<List<Merchant>> call, Response<List<Merchant>> response) {
                        if (response.body() != null){
                            binding.tvNominalsaldoHome.setText(String.valueOf(response.body().get(0).getTotal_saldo()));
                        }
                        else{
                            binding.tvNominalsaldoHome.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Merchant>> call, Throwable t) {

                    }
                });
    }

    private void getPendapatanToday(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getPendapatanToday()
                .enqueue(new Callback<Double>() {
                    @Override
                    public void onResponse(Call<Double> call, Response<Double> response) {
                        if (response.body() != null){
                            binding.pendapatanHomeMerchant.setText(String.valueOf(response.body()));
                        }
                        else{
                            binding.pendapatanHomeMerchant.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<Double> call, Throwable t) {

                    }
                });
    }

    private void getTransaksiToday(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getTransaksiToday()
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() != null){
                            binding.jumlahHomeMerchant.setText(String.valueOf(response.body()));
                        }
                        else{
                            binding.jumlahHomeMerchant.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
    }

}
