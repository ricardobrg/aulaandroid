package com.example.aulaandroid.modelo;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private final String nome;
    private int nota;

    public Restaurante(String nome, int nota){
        this.nome = nome;
        this.nota = nota;
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

    public static ArrayList<Restaurante> createRestaurateList(int qtde){
        ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
        for (int i = 0; i < qtde; i++){
            restaurantes.add(new Restaurante("Restaurante " + i, i));
        }
        return restaurantes;
    }
}
