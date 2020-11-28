package com.example.aulaandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.widget.Toast;

import java.net.URI;

public class EditActivity extends AppCompatActivity {

    private static final int IMAGE_PICKER_CODE = 123;
    private static final int EXTERNAL_STORAGE_PERMISSION_CODE = 124;

    String nomeDoRestaurante, imageUriString;
    int notaDoRestaurante;
    private Button saveButton;
    private EditText campoNome;
    private EditText campoNota;
    private int idDoRestaurante;
    private boolean restauranteFavorito;
    private Button favoritoButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        saveButton = findViewById(R.id.saveMensagem);
        favoritoButton = findViewById(R.id.fav_button);
        campoNome = findViewById(R.id.editNome);
        campoNota = findViewById(R.id.editNota);
        imageView = findViewById(R.id.restaurante_image);
        setupData();
        setupButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case IMAGE_PICKER_CODE :
                if(resultCode == RESULT_OK){
                    Uri imageUri = data.getData();
                    imageView.setImageURI(imageUri);
                    imageUriString = imageUri.toString();
                }
        }
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
            //if(imageUriString != null)
              //  imageView.setImageURI(Uri.parse(imageUriString));
        }
    }

    private void setupButtons() {
        setFavoritoText();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(checkPermissions()){
                    pickImageFromGallery();
               // }
            }
        });
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

    private boolean checkPermissions() {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
            return true;
        }else{
            boolean perm = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED;
            if(!perm){
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, EXTERNAL_STORAGE_PERMISSION_CODE);
                return false;
            }else{
                return true;
            }
        }
    }

    private void setFavoritoText() {
        String buttonText = restauranteFavorito ? "- Lista" : "+ Lista";
        favoritoButton.setText(buttonText);
    }

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICKER_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case EXTERNAL_STORAGE_PERMISSION_CODE :
                if(grantResults.length > 0 &&
                   grantResults[0] == PackageManager.PERMISSION_GRANTED
                ){
                    pickImageFromGallery();
                }else {
                    Toast.makeText(
                            this,
                            "Sem permissão para acessar as imagens!",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}