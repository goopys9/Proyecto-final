package com.mycompany.pruebat;

public class BasicCliente extends Cliente {
    public BasicCliente(String nombre, String correo) {
        super(nombre, correo);
        this.tarjeta = new BasicCard();
    }
}