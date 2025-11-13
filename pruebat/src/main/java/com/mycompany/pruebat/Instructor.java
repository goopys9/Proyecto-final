package com.mycompany.pruebat;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private String nombre;
    private List<Cliente> clientesAsignados;

    public Instructor(String nombre) {
        this.nombre = nombre;
        this.clientesAsignados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cliente> getClientesAsignados() {
        return clientesAsignados;
    }

    public void asignarCliente(Cliente cliente) {
        clientesAsignados.add(cliente);
    }

    public int getCantidadClientes() {
        return clientesAsignados.size();
    }

    @Override
    public String toString() {
        return "Instructor: " + nombre + " | Clientes: " + getCantidadClientes();
    }
}