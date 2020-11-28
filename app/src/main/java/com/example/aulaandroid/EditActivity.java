package com.example.aulaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    String nomeDoRestaurante, imageUriString;
    int notaDoRestaurante;
    private Button saveButton;
    private EditText campoNome;
    private EditText campoNota;
    private int idDoRestaurante;
    private boolean restauranteFavorito;
    private Button favoritoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        saveButton = findViewById(R.id.saveMensagem);
        favoritoButton = findViewById(R.id.fav_button);
        campoNome = findViewById(R.id.editNome);
        campoNota = findViewById(R.id.editNota);
        setupData();
        setupButtons();
    }

    private void setupData() {
        Intent intent = getIntent();
        idDoRestaurante = intent.getIntExtra("idDoRestaurante",-1);
        if(idDoRestaurante >= 0){
            nomeDoRestaurante = intent.getStringExtra("nomeDoRestaurante");
            notaDoRestaurante = intent.getIntExtra("notaDoRestaurante", 0);
            restauranteFavorito = intent.getBooleanExtra("restauranteFavorito", false);
            imageUriString = intent.getStringExtra("imageUriString");
            campoNome.setText(nomeDoRestaurante);
            campoNota.setText(String.valueOf(notaDoRestaurante));
        }
    }

    private void setupButtons() {
        setFavoritoText();
        favoritoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restauranteFavorito = !restauranteFavorito;
                setFavoritoText();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeDoRestaurante = campoNome.getText().toString();
                notaDoRestaurante = Integer.parseInt(campoNota.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("nomeDoRestaurante", nomeDoRestaurante);
                intent.putExtra("notaDoRestaurante", notaDoRestaurante);
                intent.putExtra("restauranteFavorito", restauranteFavorito);
                intent.putExtra("imageUriString", imageUriString);
                intent.putExtra("idDoRestaurante", idDoRestaurante);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setFavoritoText() {
        String buttonText = restauranteFavorito ? "- Lista" : "+ Lista";
        favoritoButton.setText(buttonText);
    }
}