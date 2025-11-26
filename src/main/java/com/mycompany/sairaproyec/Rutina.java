package com.mycompany.sairaproyec;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String nombre;
    private List<Ejercicio> ejercicios;

    public Rutina(String nombre) {
        this.nombre = nombre;
        this.ejercicios = new ArrayList<>(); // composición
    }

    public String getNombre() { return nombre; }
    public List<Ejercicio> getEjercicios() { return ejercicios; }

    public void agregarEjercicio(Ejercicio e) {
        ejercicios.add(e);
    }

    // SOBRECARGA: permitir crear un ejercicio directo con parámetros (polimorfismo por sobrecarga)
    public void agregarEjercicio(String nombre, int reps, int minutos, int segundos) {
        ejercicios.add(new Ejercicio(nombre, reps, minutos, segundos));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rutina: " + nombre + "\n");
        for (Ejercicio e : ejercicios) {
            sb.append(" - ").append(e).append("\n");
        }
        return sb.toString();
    }
}