package com.vaxwe.mascotasapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Patterns;
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

import java.util.HashMap;
import java.util.Map;

public class registrar_usuario extends AppCompatActivity {

    EditText NombreUser,ApellidoUser,EmailUser,PassUser,PassRUser;
    Button BtnCrearUsers;

    RequestQueue requestQueue;

    private static final String URL1 = "http://10.0.2.2/veterinaria/registrar_usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        requestQueue = Volley.newRequestQueue(this);

        NombreUser = findViewById(R.id.NombreUser);
        ApellidoUser = findViewById(R.id.ApellidoUser);
        EmailUser = findViewById(R.id.EmailUser);
        PassUser = findViewById(R.id.PassUser);
        BtnCrearUsers = findViewById(R.id.BtnCrearUser);
        PassRUser = findViewById(R.id.PassRUser);



        BtnCrearUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String nombreuser = NombreUser.getText().toString().trim();
                    String apellidouser = ApellidoUser.getText().toString().trim();
                    String eamiluser = EmailUser.getText().toString().trim();
                    String passuser = PassUser.getText().toString().trim();
                    String passRuser = PassRUser.getText().toString().trim();

                    if (nombreuser.equals("")) {
                        NombreUser.setError("El campo no debe estar vacio.");
                        Toast.makeText(registrar_usuario.this, "El campo no debe estar vacio.", Toast.LENGTH_SHORT).show();
                        NombreUser.setFocusable(true);

                    }
                    else if (apellidouser.equals("")) {
                        ApellidoUser.setError("El campo no debe estar vacio.");
                        Toast.makeText(registrar_usuario.this, "El campo no debe estar vacio.", Toast.LENGTH_SHORT).show();
                        ApellidoUser.setFocusable(true);

                    }
                else if (!Patterns.EMAIL_ADDRESS.matcher(eamiluser).matches()){
                    EmailUser.setError("Correo invalido.");
                        Toast.makeText(registrar_usuario.this, "Email invalido.", Toast.LENGTH_SHORT).show();
                    EmailUser.setFocusable(true);

                }else if(!passuser.equals(passRuser)){
                    PassUser.setError("La contraseña no coincide.");
                        Toast.makeText(registrar_usuario.this, "La contraseña no coincide.", Toast.LENGTH_SHORT).show();
                    PassUser.setFocusable(true);

                }else if(passuser.length()<6){
                        PassUser.setError("La contaseña debe ser igual o mayor a 6 caracteres.");
                        Toast.makeText(registrar_usuario.this, "La contaseña debe ser igual o mayor a 6 caracteres.", Toast.LENGTH_SHORT).show();
                        PassUser.setFocusable(true);

                    }else {
                        crearuser(nombreuser,apellidouser,eamiluser,passuser);
                        NombreUser.requestFocus();
                        /*NombreUser.setText("");
                        ApellidoUser.setText("");
                        EmailUser.setText("");
                        PassUser.setText("");
                        PassRUser.setText("");*/
                    }


            }

        });

    }


    private void crearuser(final String nombreuser, final String apellidouser, final String eamiluser, final String passuser) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                startActivity(new Intent(registrar_usuario.this, InicioSesion.class));
                Toast.makeText(registrar_usuario.this, "Usuario dado de alta", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(registrar_usuario.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre",nombreuser);
                params.put("apellido",apellidouser);
                params.put("correo",eamiluser);
                params.put("contraseña",passuser);
                params.put("accion","crear");
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

}