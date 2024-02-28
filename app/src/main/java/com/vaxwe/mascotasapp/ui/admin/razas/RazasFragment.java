package com.vaxwe.mascotasapp.ui.admin.razas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vaxwe.mascotasapp.databinding.FragmentRazasBinding;


public class RazasFragment extends Fragment {

    private FragmentRazasBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        RazasViewModel razasViewModel = new ViewModelProvider(this).get(RazasViewModel.class);

        binding = FragmentRazasBinding.inflate(inflater,container,false);
        View root = binding.getRoot();




        return root;

    }
}