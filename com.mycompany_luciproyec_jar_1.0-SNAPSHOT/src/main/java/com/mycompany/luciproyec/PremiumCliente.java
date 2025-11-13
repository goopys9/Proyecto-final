package com.mycompany.luciproyec;

public class PremiumCliente extends Cliente implements IPremiumFeatures {

    private final PremiumCard card;

    // Constructor corregido
    public PremiumCliente(String id, String nombre, String correo, double pesoKg, double alturaM, int edad) {
        super(id, nombre, correo, pesoKg, alturaM, edad);
        this.card = new PremiumCard("Premium"); // ✅ se inicializa correctamente la tarjeta
    }

    // Muestra los beneficios de la tarjeta Premium
    public void mostrarBeneficios() {
        System.out.println("---- Beneficios de la Tarjeta " + card.getNivel() + " ----");
        for (String s : card.getServiciosDisponibles()) { // ✅ corregido nombre del método
            System.out.println("- " + s);
        }
        System.out.println("💰 Precio: $" + card.getPrecio());
    }

    // Permite usar un servicio, validando que esté disponible
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
