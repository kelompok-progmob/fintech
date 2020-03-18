package com.tyga.fintech;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.FragmentProfileBinding;
import com.tyga.fintech.model.Lpd;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    View rootView;
    FragmentProfileBinding binding;
    private TokenManager tokenManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        rootView = binding.getRoot();

        tokenManager = TokenManager.getInstance(getActivity().getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE));

        binding.namaProfile.setText(tokenManager.getNasabah().getNasabah().getNama());
        binding.nikProfile.setText(tokenManager.getNasabah().getNasabah().getNik());
        binding.telpProfile.setText(tokenManager.getUser().getUser().getNoHP());
        callLPD();

        binding.btnLogoutNasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tokenManager.deleteToken();
                SharedPreferences settings = getContext().getSharedPreferences("prefs", MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }

    private void callLPD(){
        ApiClient.createServiceWithAuth(ApiService.class, tokenManager, getContext())
                .getLpdNasabah()
                .enqueue(new Callback<List<Lpd>>() {
                    @Override
                    public void onResponse(Call<List<Lpd>> call, Response<List<Lpd>> response) {
                        if (response.body() != null){
                            binding.lpdProfile.setText(response.body().get(0).getNama());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Lpd>> call, Throwable t) {

                    }
                });
    }

}
