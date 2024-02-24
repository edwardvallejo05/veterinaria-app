package com.vaxwe.mascotasapp.ui.cliente.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vaxwe.mascotasapp.R;
import com.vaxwe.mascotasapp.databinding.FragmentHomeBinding;
import com.vaxwe.mascotasapp.databinding.FragmentHomeCBinding;


public class fragment_home_c extends Fragment {

    private FragmentHomeCBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentHomeCVewModel fragmentHomeCVewModel = new ViewModelProvider(this).get(fragmentHomeCVewModel.class);

        binding = FragmentHomeCBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment
        return root;
    }
}