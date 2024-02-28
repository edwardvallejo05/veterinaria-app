package com.vaxwe.mascotasapp.ui.admin.lista_users;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vaxwe.mascotasapp.AgregarUser;
import com.vaxwe.mascotasapp.DetallesUser;
import com.vaxwe.mascotasapp.EditarUser;
import com.vaxwe.mascotasapp.MainActivityAdmin;
import com.vaxwe.mascotasapp.MainActivityCliente;
import com.vaxwe.mascotasapp.R;
import com.vaxwe.mascotasapp.databinding.FragmentListaABinding;
import com.vaxwe.mascotasapp.ui.Adapter;
import com.vaxwe.mascotasapp.ui.Lista_usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListaFragmentA extends Fragment {



    //List<Lista_usuarios> lista_usuariosList;
    Adapter adapter;
    ListView listView;
    Lista_usuarios usuarios;
    public static ArrayList<Lista_usuarios>usuariosArrayList = new ArrayList<>();
    String URL4 = "http://10.0.2.2/veterinaria/registrar_usuario.php";

    Button buttonNavigate;

    RequestQueue requestQueue;

    private FragmentListaABinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListaViewModel listaViewModel = new ViewModelProvider(this).get(ListaViewModel.class);

        binding = FragmentListaABinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        buttonNavigate = root.findViewById(R.id.buttonNavigate);
        listView = root.findViewById(R.id.listMostar);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        adapter = new Adapter(getContext(),usuariosArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position,long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[]dialogItem ={"Ver datos", "Editar datos", "Eliminar datos"};
                builder.setTitle(usuariosArrayList.get(position).getNombre());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int i){

                        switch (i){
                            case 0:

                                startActivity(new Intent(new Intent(getActivity(), DetallesUser.class)
                                        .putExtra("position",position)));

                                break;

                            case 1:
                                startActivity(new Intent(new Intent(getActivity(), EditarUser.class)
                                        .putExtra("position",position)));

                                break;

                            case 2:

                                deleteData(usuariosArrayList.get(position).getId());

                                break;

                        }

                    }
                });
                                builder.create().show();

            }
        });
                                ListarDatos();


        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AgregarUser.class));
            }
        });




        //lista_usuariosList = new ArrayList<>();

        return root;


    }

    private void deleteData(String id) {


        StringRequest request = new StringRequest(Request.Method.POST, URL4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("RESPONSE", response);
                if (response.equals("Usuario eliminado exitosamente cambiando su estado a 'inactivo'")) {

                    Toast.makeText(getActivity(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivityAdmin.class));
                } else {
                    Toast.makeText(getActivity(), "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "El usuario no existe", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id_usuario", id);
                params.put("accion","eliminar");
                return params;
            }
        };
        requestQueue.add(request);

    }


    private void ListarDatos() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                usuariosArrayList.clear();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("RESPONSE", response.toString());

                    String exito = jsonObject.getString("status");
                    Log.d("RESPONSE", exito);
                    JSONArray jsonArray = jsonObject.getJSONArray("usuarios");
                    //Log.d("RESPONSE", response.getJSONArray("Usuarios"));
                    if (exito.equals("OK")) {
                        Log.d("ENTRO","ENTRO");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String nombre = object.getString("nombre");
                            String apellido = object.getString("apellido");
                            String nombre_usuario = object.getString("nombre_usuario");
                            String contraseña = object.getString("contraseña");
                            String correo = object.getString("correo");
                            String id_perfil = object.getString("id_perfil");
                            String direccion = object.getString("direccion");
                            String telefono = object.getString("telefono");
                            String identificacion = object.getString("identificacion");
                            String estado = object.getString("estado");

                            usuarios = new Lista_usuarios(id, nombre, apellido, nombre_usuario, contraseña,
                                    correo, id_perfil, direccion, telefono, identificacion,estado);

                            usuariosArrayList.add(usuarios);
                            adapter.notifyDataSetChanged();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error " +error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Parámetros de la solicitud (nombre_usuario y contraseña)
                Map<String, String> params = new HashMap<>();
                params.put("accion","listar");
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }


}