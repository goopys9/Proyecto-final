package com.mycompany.pruebat;

public class MediumCliente extends Cliente {
    public MediumCliente(String nombre, String correo) {
        super(nombre, correo);
        this.tarjeta = new MediumCard();
    }
}
