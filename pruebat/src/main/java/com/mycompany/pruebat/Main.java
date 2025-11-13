package com.mycompany.pruebat;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Instructor> instructores = new ArrayList<>();

    public static void main(String[] args) {
        inicializarInstructores();
        mostrarMenu();
    }

    private static void inicializarInstructores() {
        instructores.add(new Instructor("Valentina"));
        instructores.add(new Instructor("Andrés"));
        instructores.add(new Instructor("Sofía"));
        instructores.add(new Instructor("Carlos"));
    }

    private static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n========== NAGOMI PILATES ==========");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar instructor");
            System.out.println("3. Asignar instructor a cliente");
            System.out.println("4. Asignar rutina");
            System.out.println("5. Comprar tarjeta");
            System.out.println("6. Mostrar clientes");
            System.out.println("7. Mostrar instructores");
            System.out.println("8. Top 3 instructores");
            System.out.println("9. Generar PDF individual de cliente");
            System.out.println("10. Generar PDF general de clientes");
            System.out.println("11. Generar PDF de instructores");
            System.out.println("12. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> registrarInstructor();
                case 3 -> asignarInstructorACliente();
                case 4 -> asignarRutina();
                case 5 -> comprarTarjeta();
                case 6 -> mostrarClientes();
                case 7 -> mostrarInstructores();
                case 8 -> mostrarTop3Instructores();
                case 9 -> generarPDFCliente();
                case 10 -> PDFGenerator.generarPDFClientes(clientes);
                case 11 -> PDFGenerator.generarPDFInstructores(instructores);
                case 12 -> System.out.println("¡Gracias por usar Nagomi Pilates!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 12);
    }

    private static void registrarCliente() {
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Correo del cliente: ");
        String correo = sc.nextLine();

        if (!correo.contains("@")) {
            System.out.println("❌ Correo inválido.");
            return;
        }

        Cliente cliente = new Cliente(nombre, correo);
        clientes.add(cliente);
        System.out.println("✅ Cliente registrado correctamente.");
    }

    private static void registrarInstructor() {
        System.out.print("Nombre del nuevo instructor: ");
        String nombre = sc.nextLine();
        instructores.add(new Instructor(nombre));
        System.out.println("✅ Instructor agregado correctamente.");
    }

    private static void asignarInstructorACliente() {
        if (clientes.isEmpty() || instructores.isEmpty()) {
            System.out.println("⚠ Registra clientes e instructores primero.");
            return;
        }

        mostrarClientes();
        System.out.print("Selecciona cliente: ");
        int ci = sc.nextInt() - 1;
        sc.nextLine();
        mostrarInstructores();
        System.out.print("Selecciona instructor: ");
        int ii = sc.nextInt() - 1;
        sc.nextLine();

        if (ci < 0 || ci >= clientes.size() || ii < 0 || ii >= instructores.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Cliente cliente = clientes.get(ci);
        Instructor instructor = instructores.get(ii);
        cliente.setInstructor(instructor);
        instructor.asignarCliente(cliente);
        System.out.println("✅ Instructor asignado.");
    }

    private static void asignarRutina() {
        if (clientes.isEmpty()) {
            System.out.println("⚠ No hay clientes registrados.");
            return;
        }
        mostrarClientes();
        System.out.print("Selecciona cliente: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();

        if (i < 0 || i >= clientes.size()) return;
        Cliente c = clientes.get(i);
        if (c.getTarjeta() == null) {
            System.out.println("⚠ Debe tener tarjeta primero.");
            return;
        }
        System.out.println("💪 Rutina asignada al cliente " + c.getNombre());
    }

    private static void comprarTarjeta() {
        if (clientes.isEmpty()) {
            System.out.println("⚠ No hay clientes registrados.");
            return;
        }

        mostrarClientes();
        System.out.print("Selecciona cliente: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();

        if (i < 0 || i >= clientes.size()) return;
        Cliente c = clientes.get(i);

        System.out.println("1. Básica - $20.000 - Pilates");
        System.out.println("2. Media - $40.000 - Spa");
        System.out.println("3. Premium - $60.000 - Pilates, Spa, Yoga");
        System.out.print("Elige tarjeta: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> c.setTarjeta(new BasicCard());
            case 2 -> c.setTarjeta(new MediumCard());
            case 3 -> c.setTarjeta(new PremiumCard());
            default -> {
                System.out.println("Opción inválida.");
                return;
            }
        }
        System.out.println("✅ " + c.getTarjeta().getTipo() + " asignada a " + c.getNombre());
    }

    private static void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
    }

    private static void mostrarInstructores() {
        for (int i = 0; i < instructores.size(); i++) {
            System.out.println((i + 1) + ". " + instructores.get(i));
        }
    }

    private static void mostrarTop3Instructores() {
        instructores.stream()
                .sorted((a, b) -> Integer.compare(b.getCantidadClientes(), a.getCantidadClientes()))
                .limit(3)
                .forEach(i -> System.out.println(i.getNombre() + " - " + i.getCantidadClientes() + " clientes"));
    }

    private static void generarPDFCliente() {
        if (clientes.isEmpty()) {
            System.out.println("⚠ No hay clientes.");
            return;
        }
        mostrarClientes();
        System.out.print("Selecciona cliente: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();
        if (i >= 0 && i < clientes.size()) {
            PDFGenerator.generarPDFCliente(clientes.get(i));
        }
    }
}