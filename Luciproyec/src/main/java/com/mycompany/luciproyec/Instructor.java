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

    public void mostrarClientes() {
        System.out.println("Clientes asignados a " + getNombre() + ":");
        for (Cliente c : clientes) {
            System.out.println("- " + c.getNombre());
        }
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Instructor de Nagomi Pilates 🧑‍🏫");
    }
}