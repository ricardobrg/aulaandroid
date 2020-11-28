package com.example.aulaandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aulaandroid.modelo.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestauranteAdapter extends
        RecyclerView.Adapter<RestauranteAdapter.ViewHolder> {

    private ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
    boolean favoritos;

    public RestauranteAdapter(List<Restaurante> restaurantes, boolean favoritos){
        this.favoritos = favoritos;
        ArrayList<Restaurante> restaurantesFiltrados = new ArrayList<Restaurante>();
        if(favoritos) {
            for (Restaurante restaurante : restaurantes) {
                if (restaurante.isFavorito()){
                    restaurantesFiltrados.add(restaurante);
                }
            }
        }else{
            restaurantesFiltrados = (ArrayList<Restaurante>) restaurantes;
        }
        this.restaurantes = restaurantesFiltrados;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView resturanteImg;
        public TextView restauranteName;
        public TextView restauranteNota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restauranteName = (TextView) itemView.findViewById(R.id.restaurante_name);
            restauranteNota = (TextView) itemView.findViewById(R.id.restaurante_nota);
            resturanteImg = (ImageView) itemView.findViewById(R.id.restaurante_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View restauranteView = layoutInflater.inflate(R.layout.restaurante_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(restauranteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurante restaurante = restaurantes.get(position);
        String nomeRestaurante = restaurante.getNome();
        boolean restauranteFavorito = restaurante.isFavorito();
        String imageUriString = restaurante.getImageUriString();
        int notaRestaurante = restaurante.getNota();
        TextView nameView = holder.restauranteName;
        nameView.setText(nomeRestaurante);
        TextView notaView = holder.restauranteNota;
        notaView.setText(String.valueOf(notaRestaurante));
        ImageView imageView = holder.resturanteImg;
        Log.e("image",restaurante.getImageUriString());
        //    imageView.setImageURI(Uri.parse(restaurante.getImageUriString()));
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Activity activity = (Activity) v.getContext();
                if(!favoritos) {
                    Intent intent = new Intent(activity, EditActivity.class);
                    intent.putExtra("nomeDoRestaurante", nomeRestaurante);
                    intent.putExtra("notaDoRestaurante", notaRestaurante);
                    intent.putExtra("idDoRestaurante", position);
                    intent.putExtra("restauranteFavorito", restauranteFavorito);
                    intent.putExtra("imageUriString", imageUriString);
                    activity.startActivityForResult(intent, MainActivity.EDIT_RESTAURANTE);
                }else{
                    Intent intent = new Intent(activity, ViewActivity.class);
                    intent.putExtra("nomeDoRestaurante", nomeRestaurante);
                    intent.putExtra("notaDoRestaurante", notaRestaurante);
                    intent.putExtra("idDoRestaurante", position);
                    intent.putExtra("restauranteFavorito", restauranteFavorito);
                    intent.putExtra("imageUriString", imageUriString);
                    activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.restaurantes.size();
    }


}
