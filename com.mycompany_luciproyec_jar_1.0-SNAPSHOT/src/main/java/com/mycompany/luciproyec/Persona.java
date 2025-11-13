package com.mycompany.luciproyec;

public abstract class Persona {
    private final String id;
    private final String nombre;
    private String correo;

    public Persona(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public abstract void mostrarRol();
}