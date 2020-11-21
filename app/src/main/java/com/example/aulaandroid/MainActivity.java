package com.example.aulaandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.aulaandroid.modelo.Restaurante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
    RestauranteAdapter adapter;
    private final int NEW_RESTAURANTE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRestauranteList();
        setupButtons();
    }

    private void setupButtons() {
        FloatingActionButton fab = findViewById(R.id.addNew);
        fab.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), EditActivity.class);
                    startActivityForResult(intent, NEW_RESTAURANTE);
                }
            }

        );
    }

    private void setupRestauranteList() {
        RecyclerView recyclerViewRestaurantes = findViewById(R.id.rv_restaurantes);
        adapter = new RestauranteAdapter(restaurantes);
        recyclerViewRestaurantes.setAdapter(adapter);
        recyclerViewRestaurantes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewRestaurantes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case NEW_RESTAURANTE :
                String nomeDoRestaurante = data.getStringExtra("nomeDoRestaurante");
                int notaDoRestaurante = data.getIntExtra("notaDoRestaurante", 0);
                Restaurante restaurante = new Restaurante(nomeDoRestaurante,notaDoRestaurante);
                restaurantes.add(restaurante);
                break;
        }
        adapter.notifyDataSetChanged();
    }
}