package com.example.myapp2.modelo;

public class Restaurante {
    private String nome;
    private Integer nota, id;
    private boolean favorito;
    private String imageUriString;

    public Restaurante(String nome, int nota, boolean favorito, String imageUriString){
        this.nome = nome;
        this.nota = nota;
        this.favorito = favorito;
        this.imageUriString = imageUriString;
    }

    public Restaurante() {}

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

    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
