package com.mycompany.luciproyec;

public class BasicCliente extends Cliente {

    public BasicCliente(String id, String nombre, String correo, double peso, double altura, int edad) {
        super(id, nombre, correo, peso, altura, edad);
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Cliente BÁSICO de Nagomi Pilates");
    }
}
