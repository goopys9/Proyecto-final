package com.mycompany.luciaproyecto;

//atributos
public class Cliente extends Persona {
    private final double pesoKg;
    private final double alturaM;
    private final int edad;
    private Rutina rutina;
//constructor
    public Cliente(String id, String nombre, String correo, double pesoKg, double alturaM, int edad) {
        super(id, nombre, correo);
        this.pesoKg = pesoKg;
        this.alturaM = alturaM;
        this.edad = edad;
    }
//metodos
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
        System.out.println("Rol: Cliente de Nagomi Pilates 🧘‍♀");
    }

    // CORRECCIÓN: devolver el nombre heredado para que no falle
    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCorreo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
