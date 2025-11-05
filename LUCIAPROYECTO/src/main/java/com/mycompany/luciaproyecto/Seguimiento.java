package com.mycompany.luciaproyecto;

public class Seguimiento {
    private final Cliente cliente; // Asociación
    private final String fecha;
    private final String comentario;

    public Seguimiento(Cliente cliente, String fecha, String comentario) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public void mostrarSeguimiento() {
        System.out.println("Seguimiento de " + cliente.getNombre() + " (" + fecha + ")");
        System.out.println("Comentario: " + comentario);
    }
}
