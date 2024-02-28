package com.vaxwe.mascotasapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vaxwe.mascotasapp.ui.Adapter;
import com.vaxwe.mascotasapp.ui.Lista_usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InicioSesion extends AppCompatActivity{

    EditText Email,Password;
    TextView BtnResetPass;
    Button Acceder, Registrate;

    RequestQueue requestQueue;


    private static final String URL2 = "http://10.0.2.2/veterinaria/login.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        requestQueue = Volley.newRequestQueue(this);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Acceder = findViewById(R.id.Acceder);
        Registrate = findViewById(R.id.Registrate);
        BtnResetPass = findViewById(R.id.BtnResetPass);





        //BOTON PARA RECUPERAR CONTRASEÑA
        BtnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicioSesion.this, reset_pass.class));

            }
        });

        //BOTON PARA RECUPERAR CONTRASEÑA
        Registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicioSesion.this, registrar_usuario.class));

            }
        });

        //BOTON PARA ACCEDER
        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CONVERTIR A STRING

                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                if (email.equals("")){
                    Email.setError("El campo no debe estar vacio.");
                    Email.setFocusable(true);

                }else if(pass.equals("")){
                    Password.setError("El campo no debe estar vacio.");
                    Password.setFocusable(true);

                }else if(pass.length()<6){
                    Password.setError("La contaseña debe ser igual o mayor a 6 caracteres.");
                    Password.setFocusable(true);

                }
                else{
                    /*startActivity(new Intent(InicioSesion.this, MainActivityCliente.class));
                    finish();*/
                    IniciarSesion(URL2);

                }

            }

        });

    }

   private void IniciarSesion( String URL2 ) {
        // En la parte de onResponse del StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Inicio de sesión exitoso")) {
                    //Inicio de sesión exitoso, dirigir a la actividad principal
                    startActivity(new Intent(InicioSesion.this, MainActivityAdmin.class));
                    Toast.makeText(InicioSesion.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                } else {
                    // Usuario o contraseña incorrectos, mostrar un mensaje de error
                    Toast.makeText(InicioSesion.this, "Usuario o Contraseña Errados", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error en la solicitud, mostrar un mensaje de error
                Toast.makeText(InicioSesion.this, "Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Parámetros de la solicitud (nombre_usuario y contraseña)
                Map<String, String> params = new HashMap<>();
                params.put("nombre_usuario", Email.getText().toString());
                params.put("contraseña", Password.getText().toString());
                return params;
            }
        };
        // Agregar la solicitud a la cola
        requestQueue.add(stringRequest);
    }



}