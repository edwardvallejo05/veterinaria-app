package com.vaxwe.mascotasapp.ui;

import androidx.recyclerview.widget.RecyclerView;

public class Lista_usuarios {

    private String id;
    private String nombre;
    private String apellido;
    private String nombre_usuario;
    private String contraseña;
    private String estado;

    public Lista_usuarios(String id, String nombre, String apellido,
                          String nombre_usuario, String contraseña,
                          String correo, String id_perfil, String direccion, String telefono,
                          String identificacion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.id_perfil = id_perfil;
        this.direccion = direccion;
        this.telefono = telefono;
        this.identificacion = identificacion;
        this.estado = estado;
    }

    private String correo;
    private String id_perfil;
    private String direccion;
    private String telefono;
    private String identificacion;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getId_perfil() {
        return id_perfil;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }
    public String getEstado() {
        return estado;
    }

}
