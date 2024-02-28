package com.vaxwe.mascotasapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;

public class reset_pass extends AppCompatActivity {

    EditText RecuperarEmail;
    Button BtnAccederResetPass;


    RequestQueue requestQueue;

    private static final String URL3 = "http://10.0.2.2/veterinaria/recovery.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        requestQueue = Volley.newRequestQueue(this);

        RecuperarEmail = findViewById(R.id.RecuperarEmail);
        BtnAccederResetPass = findViewById(R.id.BtnAccederResetPass);

        BtnAccederResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String recuperarEmail = RecuperarEmail.getText().toString().trim();

                if (recuperarEmail.equals("")) {
                    RecuperarEmail.setError("El campo no debe estar vacio.");
                    Toast.makeText(reset_pass.this, "El campo no debe estar vacio.", Toast.LENGTH_SHORT).show();
                    RecuperarEmail.setFocusable(true);

                }else{

                    EnviarMail(recuperarEmail);
                }


            }
        });



    }

    private void EnviarMail(final String recuperarEmail){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response.toString());
                if (response.equals("Credenciales enviadas al correo")) {
                    //Inicio de sesión exitoso, dirigir a la actividad principal
                    startActivity(new Intent(reset_pass.this, InicioSesion.class));
                    Toast.makeText(reset_pass.this, "Credenciales enviadas al correo", Toast.LENGTH_SHORT).show();

                } else {
                    // Usuario o contraseña incorrectos, mostrar un mensaje de error
                    Toast.makeText(reset_pass.this, "El Usuario no está registrado en el Sistema", Toast.LENGTH_SHORT).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(reset_pass.this, "El Usuario no está registrado en el Sistema", Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre_usuario",recuperarEmail);

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}



