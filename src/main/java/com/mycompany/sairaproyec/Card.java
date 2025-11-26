package com.mycompany.sairaproyec;

import java.util.List;

public abstract class Card {
    protected String tipo;
    protected double precio;
    protected List<String> serviciosDisponibles;

    public Card(String tipo, double precio, List<String> serviciosDisponibles) {
        this.tipo = tipo;
        this.precio = precio;
        this.serviciosDisponibles = serviciosDisponibles;
    }

    public String getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public List<String> getServiciosDisponibles() { return serviciosDisponibles; }

    @Override
    public String toString() {
        return "Tarjeta: " + tipo + " | Precio: $" + precio + " | Servicios: " + serviciosDisponibles;
    }
}