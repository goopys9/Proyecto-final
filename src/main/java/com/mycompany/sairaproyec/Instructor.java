package com.mycompany.sairaproyec;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Persona {
    private List<Cliente> clientesAsignados;

    public Instructor(String nombre) {
        super(nombre, nombre + "@correo.com");
        this.clientesAsignados = new ArrayList<>();
    }

    // Permite asignar un cliente (asociación/agrégación)
    public void asignarCliente(Cliente cliente) {
        if (!clientesAsignados.contains(cliente)) {
            clientesAsignados.add(cliente);
        }
    }

    // SOBRECARGA: asignar varios clientes a la vez (ejemplo de polimorfismo por sobrecarga)
    public void asignarCliente(List<Cliente> clientes) {
        for (Cliente c : clientes) {
            asignarCliente(c);
        }
    }

    public int getCantidadClientes() { return clientesAsignados.size(); }
    public List<Cliente> getClientesAsignados() { return clientesAsignados; }

    @Override
    public String toString() {
        return "Instructor: " + nombre + " | Clientes: " + getCantidadClientes();
    }
}