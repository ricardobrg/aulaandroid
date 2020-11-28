package com.example.aulaandroid.modelo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private final String nome;
    private int nota;
    private boolean favorito;
    private String imageUriString;

    public Restaurante(String nome, int nota, boolean favorito, String imageUriString){
        this.nome = nome;
        this.nota = nota;
        this.favorito = favorito;
        this.imageUriString = imageUriString;
    }

    public int getNota() {
        return nota;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public boolean isFavorito() { return this.favorito; }

    public void setFavorito(boolean favorito) { this.favorito = favorito; }

    public String getImageUriString() { return imageUriString; }

    public void setImageUriString(String imageUri) { this.imageUriString = imageUri;}
}
