package com.vaxwe.mascotasapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vaxwe.mascotasapp.ui.admin.lista_users.ListaFragmentA;

import java.util.HashMap;
import java.util.Map;

public class EditarUser extends AppCompatActivity {

    private static final String URL5 = "http://10.0.2.2/veterinaria/registrar_usuario.php";

    EditText Nombre3, Apellido3, Nombre_user3, PasswordUser3, CorreoUser3, Direccion3, Telefono3,
            Identificacion3, Id_Pertil3, Estado3;

    TextView Id_User3;

    Button GuardarUser3;

    private int position;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_user);

        Nombre3 = findViewById(R.id.Nombre3);
        Apellido3 = findViewById(R.id.Apellido3);
        Nombre_user3 = findViewById(R.id.Nombre_user3);
        PasswordUser3 = findViewById(R.id.PasswordUser3);
        CorreoUser3 = findViewById(R.id.CorreoUser3);
        Direccion3 = findViewById(R.id.Direccion3);
        Telefono3 = findViewById(R.id.Telefono3);
        Identificacion3 = findViewById(R.id.Identificacion3);
        Id_Pertil3 = findViewById(R.id.Id_Pertil3);
        Id_User3 = findViewById(R.id.Id_User3);
        Estado3 = findViewById(R.id.Estado3);

        GuardarUser3 = findViewById(R.id.GuardarUser3);

        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        Nombre3.setText(ListaFragmentA.usuariosArrayList.get(position).getNombre());
        Apellido3.setText(ListaFragmentA.usuariosArrayList.get(position).getApellido());
        Nombre_user3.setText(ListaFragmentA.usuariosArrayList.get(position).getNombre_usuario());
        PasswordUser3.setText(ListaFragmentA.usuariosArrayList.get(position).getContraseña());
        CorreoUser3.setText(ListaFragmentA.usuariosArrayList.get(position).getCorreo());
        Direccion3.setText(ListaFragmentA.usuariosArrayList.get(position).getDireccion());
        Telefono3.setText(ListaFragmentA.usuariosArrayList.get(position).getTelefono());
        Identificacion3.setText(ListaFragmentA.usuariosArrayList.get(position).getIdentificacion());
        Id_Pertil3.setText(ListaFragmentA.usuariosArrayList.get(position).getId_perfil());
        Id_User3.setText(ListaFragmentA.usuariosArrayList.get(position).getId());
        Estado3.setText(ListaFragmentA.usuariosArrayList.get(position).getEstado());


        GuardarUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });


    }
    public void actualizar(){
        final String id = Id_User3.getText().toString().trim();
        final String nombre = Nombre3.getText().toString().trim();
        final String apellido = Apellido3.getText().toString().trim();
        final String nombre_usuario = Nombre_user3.getText().toString().trim();
        final String contraseña = PasswordUser3.getText().toString().trim();
        final String correo = CorreoUser3.getText().toString().trim();
        final String direccion = Direccion3.getText().toString().trim();
        final String telefono = Telefono3.getText().toString().trim();
        final String identificacion = Identificacion3.getText().toString().trim();
        final String id_perfil = Id_Pertil3.getText().toString().trim();
        final String estado = Estado3.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizado");
        progressDialog.dismiss();

        if(nombre.isEmpty() || apellido.isEmpty() || nombre_usuario.isEmpty() || contraseña.isEmpty() || correo.isEmpty()||
        id_perfil.isEmpty()){
            Toast.makeText(this, "Los campos Nombre, Apellido, Nombre Usuario," +
                    "Contraseña y Perfil no deben estar vacios", Toast.LENGTH_SHORT).show();


        }else{
            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, URL5, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("RESPONSE", response.toString());
                    if (response.equals("Usuario actualizado exitosamente")){
                        Toast.makeText(EditarUser.this, "Usuario actualizado exitosamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditarUser.this,MainActivityAdmin.class));
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EditarUser.this, "El usuario no existe", Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("id_usuario",id);
                    params.put("nombre",nombre);
                    params.put("apellido",apellido);
                    params.put("nombre_usuario",nombre_usuario);
                    params.put("contraseña",contraseña);
                    params.put("correo",correo);
                    params.put("direccion",direccion);
                    params.put("telefono",telefono);
                    params.put("identificacion",identificacion);
                    params.put("estado",estado);
                    if (id_perfil.equals("Administrador")){
                        params.put("id_perfil","1");

                    }else{
                        params.put("id_perfil","2");
                    }
                    params.put("accion","editar");

                    return params;
                }
            };
            requestQueue.add(request);

        }



    }


}