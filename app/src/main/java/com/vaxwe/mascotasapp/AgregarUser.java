package com.vaxwe.mascotasapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vaxwe.mascotasapp.R;
import com.vaxwe.mascotasapp.ui.admin.lista_users.ListaFragmentA;

import java.util.HashMap;
import java.util.Map;

public class AgregarUser extends AppCompatActivity {

    private static final String URL1 = "http://10.0.2.2/veterinaria/registrar_usuario.php";

    EditText Nombre1, Apellido1, Nombre_user, PasswordUser, CorreoUser, Direccion, Telefono,
            Identificacion, Id_Pertil;

    Button GuardarUser;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_user);

        Nombre1 = findViewById(R.id.Nombre1);
        Apellido1 = findViewById(R.id.Apellido1);
        Nombre_user = findViewById(R.id.Nombre_user);
        PasswordUser = findViewById(R.id.PasswordUser);
        CorreoUser = findViewById(R.id.CorreoUser);
        Direccion = findViewById(R.id.Direccion);
        Telefono = findViewById(R.id.Telefono);
        Identificacion = findViewById(R.id.Identificacion);
        Id_Pertil = findViewById(R.id.Id_Pertil);

        GuardarUser = findViewById(R.id.GuardarUser);
        requestQueue = Volley.newRequestQueue(this);

        GuardarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });

    }


    private void insertarDatos(){
        final String nombre = Nombre1.getText().toString().trim();
        final String apellido = Apellido1.getText().toString().trim();
        final String nombreU = Nombre_user.getText().toString().trim();
        final String passU = PasswordUser.getText().toString().trim();
        final String correoU = CorreoUser.getText().toString().trim();
        final String direccionU = Direccion.getText().toString().trim();
        final String telefono = Telefono.getText().toString().trim();
        final String identificacion = Identificacion.getText().toString().trim();
        final String idPerfil = Id_Pertil.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando");

        if (nombre.isEmpty() || apellido.isEmpty() || nombreU.isEmpty() || passU.isEmpty() || correoU.isEmpty() ||
                idPerfil.isEmpty()){
            Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show();
            return;
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (idPerfil.equals("Administrador")){

                        if (response.equals("Usuario dado de alta")) {
                            Toast.makeText(AgregarUser.this, "Datos insertados", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(AgregarUser.this,MainActivityAdmin.class));
                            //finish();
                        } else {
                            Toast.makeText(AgregarUser.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                    }else {

                        if (response.equals("Cliente registrado exitosamente")) {
                            Toast.makeText(AgregarUser.this, "Datos insertados", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(AgregarUser.this,MainActivityAdmin.class));
                            //finish();
                        } else {
                            Toast.makeText(AgregarUser.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AgregarUser.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<>();

                    params.put("nombre",nombre);
                    params.put("apellido",apellido);
                    params.put("nombre_usuario",nombreU);
                    params.put("contraseña",passU);
                    params.put("correo",correoU);
                    params.put("direccion",direccionU);
                    params.put("telefono",telefono);
                    params.put("identificacion",identificacion);
                    if (idPerfil.equals("Administrador")){
                        params.put("id_perfil","1");

                    }else if(idPerfil.equals("Usuario")){
                        params.put("id_perfil","2");
                    }

                    params.put("accion","crear");

                    return params;
                }
            };

            requestQueue.add(request);
        }


    }


}