package com.mycompany.luciproyec;

import java.util.ArrayList;
import java.util.List;

public class PremiumCard extends Card {
    private final List<String> serviciosDisponible;

    public PremiumCard(String nivel) {
        super(nivel, 60000); // 💵 Precio premium
        this.serviciosDisponible = new ArrayList<>();
        serviciosDisponible.add("Silla masajes");
        serviciosDisponible.add("Spa");
        serviciosDisponible.add("Mecedora");
        serviciosDisponible.add("Batidos naturales");
        serviciosDisponible.add("Skin Care");
        serviciosDisponible.add("Cuidado personal");
    }

    public List<String> getServiciosDisponible() { return serviciosDisponible; }

    public boolean tieneServicio(String servicio) {
        return serviciosDisponible.stream().anyMatch(s -> s.equalsIgnoreCase(servicio));
    }

    Iterable<String> getServiciosDisponibles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}