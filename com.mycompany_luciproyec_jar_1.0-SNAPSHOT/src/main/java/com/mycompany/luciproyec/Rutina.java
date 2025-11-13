package com.mycompany.luciproyec;

import java.util.ArrayList;

public class Rutina {
    private final String nombreRutina;
    private final ArrayList<Ejercicio> ejercicios;

    public Rutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
        this.ejercicios = new ArrayList<>();
    }

    public void agregarEjercicio(Ejercicio e) { ejercicios.add(e); }

    public void mostrarRutina() {
        System.out.println("Rutina: " + nombreRutina);
        for (Ejercicio e : ejercicios) e.mostrarInfo();
    }

    public String getNombreRutina() { return nombreRutina; }

    Object getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}