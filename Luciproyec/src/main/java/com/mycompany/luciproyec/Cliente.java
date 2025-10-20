package com.mycompany.luciproyec;

public class Cliente extends Persona {
    private final double pesoKg;
    private final double alturaM;
    private final int edad;
    private Rutina rutina;

    public Cliente(String id, String nombre, String correo, double pesoKg, double alturaM, int edad) {
        super(id, nombre, correo);
        this.pesoKg = pesoKg;
        this.alturaM = alturaM;
        this.edad = edad;
    }

    public double getPesoKg() { return pesoKg; }
    public double getAlturaM() { return alturaM; }
    public int getEdad() { return edad; }

    public void asignarRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public Rutina getRutina() {
        return rutina;
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Cliente de Nagomi Pilates 🧘‍♀️");
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
