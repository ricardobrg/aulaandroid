package com.example.aulaandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewActivity extends AppCompatActivity {

    private static final int IMAGE_PICKER_CODE = 123;
    private static final int EXTERNAL_STORAGE_PERMISSION_CODE = 124;

    String nomeDoRestaurante, imageUriString;
    int notaDoRestaurante;
    private Button saveButton;
    private TextView campoNome;
    private TextView campoNota;
    private int idDoRestaurante;
    private boolean restauranteFavorito;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setTitle("Restaurantes Favoritos");
        this.setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        saveButton = findViewById(R.id.saveMensagem);
        campoNome = findViewById(R.id.textnome);
        campoNota = findViewById(R.id.textnota);
        imageView = findViewById(R.id.restaurante_image);
        setupData();
    }

    private void setupData() {
        Intent intent = getIntent();
        idDoRestaurante = intent.getIntExtra("idDoRestaurante",-1);
        if(idDoRestaurante >= 0){
            nomeDoRestaurante = intent.getStringExtra("nomeDoRestaurante");
            notaDoRestaurante = intent.getIntExtra("notaDoRestaurante", 0);
            restauranteFavorito = intent.getBooleanExtra("restauranteFavorito", false);
            imageUriString = intent.getStringExtra("imageUriString");
            Log.e("imG",imageUriString);
            campoNome.setText(nomeDoRestaurante);
            campoNota.setText(String.valueOf(notaDoRestaurante));
            //if(imageUriString != null)
              //  imageView.setImageURI(Uri.parse(imageUriString));
        }
    }
}