package com.example.aulaandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aulaandroid.modelo.Restaurante;

import java.util.List;

public class RestauranteAdapter extends
        RecyclerView.Adapter<RestauranteAdapter.ViewHolder> {

    private List<Restaurante> restaurantes;

    public RestauranteAdapter(List<Restaurante> restaurantes){
        this.restaurantes = restaurantes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView restauranteName;
        public TextView restauranteNota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restauranteName = (TextView) itemView.findViewById(R.id.restaurante_name);
            restauranteNota = (TextView) itemView.findViewById(R.id.restaurante_nota);
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
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Activity activity = (Activity) v.getContext();
                Intent intent = new Intent(activity, EditActivity.class);
                intent.putExtra("nomeDoRestaurante", nomeRestaurante);
                intent.putExtra("notaDoRestaurante", notaRestaurante);
                intent.putExtra("idDoRestaurante", position);
                intent.putExtra("restauranteFavorito", restauranteFavorito);
                intent.putExtra("imageUriString", imageUriString);
                activity.startActivityForResult(intent, MainActivity.EDIT_RESTAURANTE );
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.restaurantes.size();
    }


}
