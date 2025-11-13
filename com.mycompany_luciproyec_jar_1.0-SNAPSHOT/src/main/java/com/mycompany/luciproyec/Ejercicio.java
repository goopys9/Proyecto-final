package com.mycompany.luciproyec;

import java.util.Objects;

/**
 * Representa un ejercicio que puede asignarse a clientes o usarse en clases.
 */
public class Ejercicio {

    void mostrarInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public enum Nivel {
        BASICO,
        INTERMEDIO,
        AVANZADO
    }

    private String id;
    private String nombre;
    private String descripcion;
    private int duracionMinutos;
    private Nivel nivel;

    /**
     * Constructor principal.
     *
     * @param id              Identificador único del ejercicio
     * @param nombre          Nombre breve del ejercicio
     * @param descripcion     Descripción / instrucciones
     * @param duracionMinutos Duración estimada en minutos
     * @param nivel           Nivel de dificultad
     */
    public Ejercicio(String id, String nombre, String descripcion, int duracionMinutos, Nivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMinutos = Math.max(0, duracionMinutos);
        this.nivel = nivel == null ? Nivel.BASICO : nivel;
    }

    /* Getters */
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public Nivel getNivel() { return nivel; }

    /* Setters (opcionales) */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = Math.max(0, duracionMinutos); }
    public void setNivel(Nivel nivel) { this.nivel = nivel == null ? Nivel.BASICO : nivel; }

    /**
     * Asigna / aplica este ejercicio a un cliente (sólo imprime una confirmación).
     * No modifica datos internos del cliente — sirve como utilidad de consola.
     *
     * @param cliente cliente al que se asigna el ejercicio
     */
    public void asignarA(Cliente cliente) {
        if (cliente == null) {
            System.out.println("No se puede asignar ejercicio: cliente nulo.");
            return;
        }
        System.out.printf("Ejercicio '%s' (nivel %s, %d min) asignado a %s (ID: %s)%n",
                nombre, nivel, duracionMinutos, cliente.getNombre(), cliente.getId());
    }

    @Override
    public String toString() {
        return String.format("Ejercicio{id='%s', nombre='%s', nivel=%s, duracion=%dmin}",
                id, nombre, nivel, duracionMinutos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ejercicio)) return false;
        Ejercicio ejercicio = (Ejercicio) o;
        return Objects.equals(id, ejercicio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
