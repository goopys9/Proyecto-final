package com.mycompany.luciproyec;

import java.util.ArrayList;

public class Rutina {
    private final String nombreRutina;
    private final ArrayList<Ejercicio> ejercicios; // Composición

    public Rutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
        this.ejercicios = new ArrayList<>();
    }

    public void agregarEjercicio(Ejercicio e) {
        ejercicios.add(e);
    }

    public void mostrarRutina() {
        System.out.println("Rutina: " + nombreRutina);
        for (Ejercicio e : ejercicios) {
            e.mostrarInfo();
        }
    }
}