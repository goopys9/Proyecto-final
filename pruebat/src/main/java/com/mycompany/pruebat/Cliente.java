package com.mycompany.pruebat;

import java.time.LocalDate;

public class Cliente {
    protected String nombre;
    protected String correo;
    protected LocalDate fechaRegistro;
    protected Card tarjeta;
    protected Instructor instructor;

    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.fechaRegistro = LocalDate.now();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Card getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Card tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre +
               " | Correo: " + correo +
               " | Fecha Registro: " + fechaRegistro +
               (tarjeta != null ? " | Tarjeta: " + tarjeta.getTipo() : "") +
               (instructor != null ? " | Instructor: " + instructor.getNombre() : "");
    }
}