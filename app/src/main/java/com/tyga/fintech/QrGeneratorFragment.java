package com.tyga.fintech;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.tyga.fintech.databinding.ActivityQrgeneratorBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class QrGeneratorFragment extends Fragment {

    View rootView;
    ActivityQrgeneratorBinding binding;

    public QrGeneratorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_qrgenerator, container, false);
        rootView = binding.getRoot();

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.nominalTopUp.getText().toString().equals("") || !binding.nominalTopUp.getText().toString().isEmpty()){
                    Intent intent = new Intent(getContext(),QRGeneratorActivity.class);
                    intent.putExtra("nominal",binding.nominalTopUp.getText().toString());
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return rootView;
    }

}
