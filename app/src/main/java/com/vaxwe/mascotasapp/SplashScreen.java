package com.vaxwe.mascotasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.vaxwe.mascotasapp.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //DURACION DE LA CARGA
        final int duracion = 1000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // CODIGO QUE SE EJECUTA DE LA CARGA
                Intent intent = new Intent(SplashScreen.this, InicioSesion.class);
                startActivity(intent);
                finish();
            }
        },duracion);
    }
}