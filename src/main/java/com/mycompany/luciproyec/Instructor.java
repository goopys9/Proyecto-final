package com.mycompany.luciproyec;

import java.util.ArrayList;

public class Instructor extends Persona {
    private final ArrayList<Cliente> clientes; // Agregación

    public Instructor(String id, String nombre, String correo) {
        super(id, nombre, correo);
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Sobrecarga: mostrarTodos (sin filtro) y mostrarClientes(nombreFiltro)
    public void mostrarClientes() {
        System.out.println("Clientes asignados a " + getNombre() + ":");
        for (Cliente c : clientes) {
            System.out.println("- " + c.getNombre());
        }
    }

    public void mostrarClientes(String nombreFiltro) {
        System.out.println("Clientes de " + getNombre() + " que coinciden con '" + nombreFiltro + "':");
        for (Cliente c : clientes) {
            if (c.getNombre().toLowerCase().contains(nombreFiltro.toLowerCase())) {
                System.out.println("- " + c.getNombre());
            }
        }
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Instructor de Nagomi Pilates 🧑‍🏫");
    }
}
