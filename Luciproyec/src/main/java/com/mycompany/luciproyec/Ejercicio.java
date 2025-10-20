package com.mycompany.luciproyec;

public class Ejercicio {
    private final String nombre;
    private final String nivel;
    private final int duracionMin;

    public Ejercicio(String nombre, String nivel, int duracionMin) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.duracionMin = duracionMin;
    }

    public String getNombre() { return nombre; }
    public String getNivel() { return nivel; }
    public int getDuracionMin() { return duracionMin; }

    public void mostrarInfo() {
        System.out.println("Ejercicio: " + nombre + " | Nivel: " + nivel + " | Duración: " + duracionMin + " min");
    }
}