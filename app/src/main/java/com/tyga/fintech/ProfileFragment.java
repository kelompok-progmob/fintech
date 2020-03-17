package com.tyga.fintech;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.FragmentProfileBinding;

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

        binding.namaProfile.setText(tokenManager.getToken().getNasabah().getNama());
        binding.nikProfile.setText(tokenManager.getToken().getNasabah().getNik());
        binding.telpProfile.setText(tokenManager.getToken().getUser().getNoHP());

        return rootView;
    }

    private void callLpd(){

    }

}
