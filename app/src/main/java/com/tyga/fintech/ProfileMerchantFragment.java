package com.tyga.fintech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.MODE_PRIVATE;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.FragmentProfileBinding;
import com.tyga.fintech.databinding.FragmentProfileMerchantBinding;
import com.tyga.fintech.model.Lpd;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class ProfileMerchantFragment extends Fragment {


    public ProfileMerchantFragment() {
        // Required empty public constructor
    }

    FragmentProfileMerchantBinding binding;
    private TokenManager tokenManager;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_profile_merchant, container, false);
//
//        Button btnLogout = view.findViewById(R.id.btnLogoutMerchant);
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences settings = getContext().getSharedPreferences("prefs", MODE_PRIVATE);
//                settings.edit().clear().commit();
//                Intent intent = new Intent(getContext(),LoginActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });
//
//        return view;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_merchant, container, false);
        rootView = binding.getRoot();
        tokenManager = TokenManager.getInstance(getActivity().getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE));
        binding.namaProfileMerchant.setText(tokenManager.getMerchant().getMerchant().getNama());
        binding.telpProfileMerchant.setText(tokenManager.getUser().getUser().getNoHP());
        binding.alamatProfileMerchant.setText(tokenManager.getMerchant().getMerchant().getAlamat());

        binding.btnLogoutMerchant.setOnClickListener(new View.OnClickListener() {
                        @Override
            public void onClick(View view) {
                SharedPreferences settings = getContext().getSharedPreferences("prefs", MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        callLPD();
        return rootView;
    }

    private void callLPD(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getLpdMerchant()
                .enqueue(new Callback<List<Lpd>>() {
                    @Override
                    public void onResponse(Call<List<Lpd>> call, Response<List<Lpd>> response) {
                        if (response.body() != null){
                            binding.lpdProfileMerchant.setText(response.body().get(0).getNama());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Lpd>> call, Throwable t) {

                    }
                });
    }

}
