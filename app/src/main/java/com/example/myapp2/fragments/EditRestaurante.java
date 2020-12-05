package com.example.myapp2.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp2.MainActivity;
import com.example.myapp2.R;
import com.example.myapp2.modelo.Restaurante;
public class EditRestaurante extends Fragment {

    private Restaurante restaurante;
    private Button saveButton, favoritoButton;
    private EditText campoNome, campoNota;
    private ImageView imageView;
    private Integer idDoRestaurante;

    public EditRestaurante() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        restaurante = activity.restaurante;
        View view = inflater.inflate(R.layout.fragment_edit_restaurante, container, false);
        saveButton = view.findViewById(R.id.saveMensagem);
        favoritoButton = view.findViewById(R.id.fav_button);
        campoNome = view.findViewById(R.id.editNome);
        campoNota = view.findViewById(R.id.editNota);
        imageView = view.findViewById(R.id.restaurante_image);
        setupData();
        setupButtons();
        return view;
    }

    private void setupButtons() {
        setFavoritoText();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(checkPermissions()){
                // pickImageFromGallery();
                // }
            }
        });
        favoritoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurante.setFavorito(!restaurante.isFavorito());
                setFavoritoText();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.restaurante = restaurante;
                activity.saveRestaurante();
                Fragment fragment = new Restaurantes();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragment)
                        .commit();
            }
        });
    }

    private void setFavoritoText() {
        String buttonText = restaurante.isFavorito() ? "- Lista" : "+ Lista";
        favoritoButton.setText(buttonText);
    }

    private void setupData() {
        idDoRestaurante = restaurante.getId();
        if (idDoRestaurante != null && idDoRestaurante >= 0) {
            String nomeDoRestaurante = restaurante.getNome();
            int notaDoRestaurante = restaurante.getNota();
            String imageUriString = restaurante.getImageUriString();
            campoNome.setText(nomeDoRestaurante);
            campoNota.setText(String.valueOf(notaDoRestaurante));
            //if(imageUriString != null)
            //  imageView.setImageURI(Uri.parse(imageUriString));
        }
    }

}