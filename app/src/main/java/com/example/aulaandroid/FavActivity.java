package com.example.aulaandroid;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aulaandroid.modelo.Restaurante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FavActivity extends AppCompatActivity {

    ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
    RestauranteAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setTitle("Restaurantes Favoritos");
        this.setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupRestauranteList();
    }

    private void setupRestauranteList() {
        //restaurantes = MainActivity.restaurantes;
        RecyclerView recyclerViewRestaurantes = findViewById(R.id.rv_restaurantes);
        adapter = new RestauranteAdapter(restaurantes, true);
        recyclerViewRestaurantes.setAdapter(adapter);
        recyclerViewRestaurantes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewRestaurantes.setLayoutManager(new LinearLayoutManager(this));
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