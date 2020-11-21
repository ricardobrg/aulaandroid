package com.example.aulaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    String nomeDoRestaurante;
    int notaDoRestaurante;
    private Button saveButton;
    private EditText campoNome;
    private EditText campoNota;
    private int idDoRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        saveButton = findViewById(R.id.saveMensagem);
        campoNome = findViewById(R.id.editNome);
        campoNota = findViewById(R.id.editNota);
        setupData();
        setupButton();
    }

    private void setupData() {
        Intent intent = getIntent();
        idDoRestaurante = intent.getIntExtra("idDoRestaurante",-1);
        if(idDoRestaurante >= 0){
            nomeDoRestaurante = intent.getStringExtra("nomeDoRestaurante");
            notaDoRestaurante = intent.getIntExtra("notaDoRestaurante", 0);
            campoNome.setText(nomeDoRestaurante);
            campoNota.setText(String.valueOf(notaDoRestaurante));
        }
    }

    private void setupButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeDoRestaurante = campoNome.getText().toString();
                notaDoRestaurante = Integer.parseInt(campoNota.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("nomeDoRestaurante", nomeDoRestaurante);
                intent.putExtra("notaDoRestaurante", notaDoRestaurante);
                intent.putExtra("idDoRestaurante", idDoRestaurante);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}