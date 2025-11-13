package com.mycompany.pruebat;

public class PremiumCliente extends Cliente {
    public PremiumCliente(String nombre, String correo) {
        super(nombre, correo);
        this.tarjeta = new PremiumCard();
    }
}