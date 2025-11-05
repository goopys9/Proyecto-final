package com.mycompany.luciaproyecto;

public interface IPremiumFeatures {
    void mostrarBeneficios();
    void usarServicio(String servicio) throws ServicioNoDisponibleException;
}
