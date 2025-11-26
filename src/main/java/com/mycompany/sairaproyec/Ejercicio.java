package com.mycompany.sairaproyec;

public class Ejercicio {
    private String nombre;
    private int repeticiones;
    private int minutos;
    private int segundos;

    public Ejercicio(String nombre, int repeticiones, int minutos, int segundos) {
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public String getNombre() { return nombre; }
    public int getRepeticiones() { return repeticiones; }
    public int getMinutos() { return minutos; }
    public int getSegundos() { return segundos; }

    @Override
    public String toString() {
        return nombre + " | Reps: " + repeticiones +
                " | Tiempo: " + minutos + "m " + segundos + "s";
    }
}