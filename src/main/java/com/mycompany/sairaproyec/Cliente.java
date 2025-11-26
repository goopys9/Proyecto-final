package com.mycompany.sairaproyec;

import java.time.LocalDate;

public class Cliente extends Persona {
    protected LocalDate fechaRegistro;
    protected Card tarjeta; // asociación
    protected Instructor instructor; // asociación/agr.
    protected Rutina rutinaAsignada; // asociación

    public Cliente(String nombre, String correo) {
        super(nombre, correo);
        this.fechaRegistro = LocalDate.now();
    }

    // CONSTRUCTOR SOBRECARGADO (polimorfismo por sobrecarga)
    public Cliente(String nombre, String correo, LocalDate fechaRegistro) {
        super(nombre, correo);
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaRegistro() { return fechaRegistro; }

    public Card getTarjeta() { return tarjeta; }
    public void setTarjeta(Card tarjeta) { this.tarjeta = tarjeta; } // encapsulado

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public Rutina getRutinaAsignada() { return rutinaAsignada; }
    public void setRutinaAsignada(Rutina rutina) { this.rutinaAsignada = rutina; }

    @Override
    public String toString() {
        return super.toString()
                + " | Fecha Registro: " + fechaRegistro
                + (tarjeta != null ? " | Tarjeta: " + tarjeta.getTipo() : "")
                + (instructor != null ? " | Instructor: " + instructor.getNombre() : "")
                + (rutinaAsignada != null ? " | Rutina: " + rutinaAsignada.getNombre() : "");
    }
}