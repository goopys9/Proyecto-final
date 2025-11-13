package com.mycompany.luciproyec;

public abstract class Card {
    protected String nivel;
    protected double precio;

    public Card(String nivel, double precio) {
        this.nivel = nivel;
        this.precio = precio;
    }

    public String getNivel() { return nivel; }
    public double getPrecio() { return precio; }
}