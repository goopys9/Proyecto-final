package com.mycompany.luciproyec;

public class PremiumCliente extends Cliente implements IPremiumFeatures {
    private final PremiumCard card;

    public PremiumCliente(String id, String nombre, String correo,
                          double pesoKg, double alturaM, int edad, PremiumCard card) {
        super(id, nombre, correo, pesoKg, alturaM, edad);
        this.card = card;
    }

    public void mostrarBeneficios() {
        System.out.println("---- Beneficios " + card.getNivel() + " ----");
        for (String s : card.getServiciosDisponible()) {
            System.out.println("- " + s);
        }
    }

    public void usarServicio(String servicio) throws ServicioNoDisponibleException {
        if (!card.tieneServicio(servicio)) {
            throw new ServicioNoDisponibleException("El servicio '" + servicio + "' no está disponible para este plan.");
        }
        System.out.println(getNombre() + " está disfrutando: " + servicio + " ✨");
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Cliente PREMIUM de Nagomi Pilates 🌟");
    }
}