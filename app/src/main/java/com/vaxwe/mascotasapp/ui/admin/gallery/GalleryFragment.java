package com.vaxwe.mascotasapp.ui.admin.gallery;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.vaxwe.mascotasapp.R;
import com.vaxwe.mascotasapp.databinding.FragmentGalleryBinding;
import com.vaxwe.mascotasapp.ui.admin.lista_users.ListaFragmentA;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    TextView UidAdministrador, NombreAdministrador,NombreAdministrador2,ApellidoAdministrador,ApellidoAdministrador2,EmailAdministrador,PassdordAdministrador;
    Button PasswordActualizarAdministrador,DatosActualizarAdministrador;

    int position;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.vaxwe.mascotasapp.ui.admin.gallery.GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(com.vaxwe.mascotasapp.ui.admin.gallery.GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}