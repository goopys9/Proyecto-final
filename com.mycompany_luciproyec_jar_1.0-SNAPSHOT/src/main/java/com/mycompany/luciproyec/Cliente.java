package com.mycompany.luciproyec;

import java.time.LocalDate;

public abstract class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private double peso;
    private double altura;
    private int edad;
    private LocalDate fechaRegistro;

    public Cliente(String id, String nombre, String correo, double peso, double altura, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
        this.fechaRegistro = LocalDate.now();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
    public int getEdad() { return edad; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }

    public void setCorreo(String correo) { this.correo = correo; }
    public void setPeso(double peso) { this.peso = peso; }
    public void setAltura(double altura) { this.altura = altura; }
    public void setEdad(int edad) { this.edad = edad; }

    @Override
    public String toString() {
        return String.format("ID:%s | %s | correo:%s | peso:%.1fkg | altura:%.2fm | edad:%d | registrado:%s",
                id, nombre, correo, peso, altura, edad, fechaRegistro.toString());
    }

    // Cada subclase puede mostrar su rol
    public abstract void mostrarRol();

    void asignarRutina(Rutina r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void comprarTarjeta(BasicCard basicCard) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getTarjeta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getPesoKg() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getAlturaM() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getRutina() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getTipoTarjeta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setRutina(Rutina r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
