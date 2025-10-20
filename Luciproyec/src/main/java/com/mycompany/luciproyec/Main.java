package com.mycompany.luciproyec;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Bienvenido a Nagomi Pilates 🧘 ===");
        System.out.print("Ingrese su ID: ");
        String id = sc.nextLine();

        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese su correo: ");
        String correo = sc.nextLine();

        System.out.print("Ingrese su peso (kg): ");
        double peso = sc.nextDouble();

        System.out.print("Ingrese su altura (m): ");
        double altura = sc.nextDouble();

        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        Cliente cliente = new Cliente(id, nombre, correo, peso, altura, edad);
        cliente.mostrarRol();

        System.out.print("\nNombre de la rutina personalizada: ");
        String nombreRutina = sc.nextLine();
        Rutina rutina = new Rutina(nombreRutina);

        System.out.println("Agregue un ejercicio a la rutina:");
        System.out.print("Nombre del ejercicio: ");
        String nombreE = sc.nextLine();
        System.out.print("Nivel del ejercicio: ");
        String nivelE = sc.nextLine();
        System.out.print("Duración (minutos): ");
        int duracionE = sc.nextInt();

        Ejercicio ejercicio = new Ejercicio(nombreE, nivelE, duracionE);
        rutina.agregarEjercicio(ejercicio);

        cliente.asignarRutina(rutina);

        // Mostrar información
        System.out.println("\n--- Datos del cliente ---");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Peso: " + cliente.getPesoKg() + " kg");
        System.out.println("Altura: " + cliente.getAlturaM() + " m");
        System.out.println("Edad: " + cliente.getEdad() + " años");

        System.out.println("\n--- Rutina asignada ---");
        cliente.getRutina().mostrarRutina();

        System.out.println("\n💬 Mensaje motivacional: “Cada día más fuerte, más flexible, más tú.” 🌿");
    }
}
