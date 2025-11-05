package com.mycompany.luciaproyecto;

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

    String getNombreRutina() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}