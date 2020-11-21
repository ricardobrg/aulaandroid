package com.example.aulaandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.aulaandroid.modelo.Restaurante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
    RestauranteAdapter adapter;
    public static final int NEW_RESTAURANTE = 123;
    public static final int EDIT_RESTAURANTE = 124;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setTitle("Lista de Restaurantes");
        this.setSupportActionBar(myToolbar);
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
        String nomeDoRestaurante;
        int notaDoRestaurante;
        Restaurante restaurante;
        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case NEW_RESTAURANTE:
                    nomeDoRestaurante = data.getStringExtra("nomeDoRestaurante");
                    notaDoRestaurante = data.getIntExtra("notaDoRestaurante", 0);
                    restaurante = new Restaurante(nomeDoRestaurante, notaDoRestaurante);
                    restaurantes.add(restaurante);
                    break;
                case EDIT_RESTAURANTE:
                    int id = data.getIntExtra("idDoRestaurante", 0);
                    nomeDoRestaurante = data.getStringExtra("nomeDoRestaurante");
                    notaDoRestaurante = data.getIntExtra("notaDoRestaurante", 0);
                    restaurante = new Restaurante(nomeDoRestaurante, notaDoRestaurante);
                    restaurantes.set(id, restaurante);
                    break;
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_buscar:
                Toast.makeText(this, "Buscar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_fav:
                Toast.makeText(this, "Favoritos", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}