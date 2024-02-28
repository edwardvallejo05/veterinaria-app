package com.vaxwe.mascotasapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vaxwe.mascotasapp.R;
import com.vaxwe.mascotasapp.ui.admin.lista_users.ListaFragmentA;

import java.util.List;

public class Adapter extends ArrayAdapter<Lista_usuarios> {

    Context context;
    List<Lista_usuarios> listaUser;

public Adapter(Context context, List<Lista_usuarios>listaUser){
    super(context,R.layout.listausuariosmostar,listaUser);
    this.context=context;
    this.listaUser=listaUser;
}

public View getView(int position, View convertView, ViewGroup parent){

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listausuariosmostar,null,true);

    TextView txtID = view.findViewById(R.id.txt_id);
    TextView txtNombre = view.findViewById(R.id.txt_NombreUser);

        txtID.setText(listaUser.get(position).getId());
    txtNombre.setText(listaUser.get(position).getNombre());
    return view;
}



}
