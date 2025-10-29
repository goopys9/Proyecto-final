package com.mycompany.luciproyec;

import java.util.ArrayList;
import java.util.List;

public class PremiumCard {
    private final String nivel; // p.ej. "Premium Mensual"
    private final List<String> serviciosDisponible;

    public PremiumCard(String nivel) {
        this.nivel = nivel;
        this.serviciosDisponible = new ArrayList<>();
        serviciosDisponible.add("Silla masajes");
        serviciosDisponible.add("Spa");
        serviciosDisponible.add("Mecedora");
        serviciosDisponible.add("Batidos naturales");
        serviciosDisponible.add("Skin Care");
        serviciosDisponible.add("Cuidado personal");
    }

    public String getNivel() { return nivel; }
    public List<String> getServiciosDisponible() { return serviciosDisponible; }

    public boolean tieneServicio(String servicio) {
        return serviciosDisponible.stream()
                .anyMatch(s -> s.equalsIgnoreCase(servicio));
    }
}