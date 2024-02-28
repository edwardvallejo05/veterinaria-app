package com.vaxwe.mascotasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vaxwe.mascotasapp.ui.admin.lista_users.ListaFragmentA;

public class DetallesUser extends AppCompatActivity {

    TextView Nombre2, Apellido2, Nombre_user2, PasswordUser2, CorreoUser2, Direccion2, Telefono2,
            Identificacion2, Id_Pertil2, Id_User2;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_user);

        Nombre2 = findViewById(R.id.Nombre2);
        Apellido2 = findViewById(R.id.Apellido2);
        Nombre_user2 = findViewById(R.id.Nombre_user2);
        PasswordUser2 = findViewById(R.id.PasswordUser2);
        CorreoUser2 = findViewById(R.id.CorreoUser2);
        Direccion2 = findViewById(R.id.Direccion2);
        Telefono2 = findViewById(R.id.Telefono2);
        Identificacion2 = findViewById(R.id.Identificacion2);
        Id_Pertil2 = findViewById(R.id.Id_Pertil2);
        Id_User2 = findViewById(R.id.Id_User2);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        Id_User2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getId());
        Nombre2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getNombre());
        Apellido2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getApellido());
        Nombre_user2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getNombre_usuario());
        PasswordUser2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getContraseña());
        CorreoUser2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getCorreo());
        Direccion2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getDireccion());
        Telefono2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getTelefono());
        Identificacion2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getIdentificacion());
        Id_Pertil2.setText("" + ListaFragmentA.usuariosArrayList.get(position).getId_perfil());

    }
}