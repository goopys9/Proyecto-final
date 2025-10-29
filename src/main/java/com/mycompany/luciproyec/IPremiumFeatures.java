package com.mycompany.luciproyec;

public interface IPremiumFeatures {
    void mostrarBeneficios();
    void usarServicio(String servicio) throws ServicioNoDisponibleException;
}
