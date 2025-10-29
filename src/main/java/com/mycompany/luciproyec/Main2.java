package com.mycompany.luciproyec;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
     
public class Main2 {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Instructor> instructores = new ArrayList<>();
    private static final List<Rutina> rutinas = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Nagomi Pilates - App de seguimiento (Main2) ===");
        cargarRutinaEjemplo();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String entrada = sc.nextLine().trim();
            try {
                int opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1 -> registrarCliente(false);
                    case 2 -> registrarCliente(true);
                    case 3 -> registrarInstructor();
                    case 4 -> crearRutinaInteractiva();
                    case 5 -> asignarRutinaACliente();
                    case 6 -> mostrarClientesYRutinas();
                    case 7 -> comprarPremium();
                    case 8 -> usarServicioPremium();
                    case 9 -> NotificationService.enviarMotivacionales(clientes);
                    case 0 -> {
                        System.out.println("Saliendo... ¡Muchos éxitos en tu entrega!");
                        salir = true;
                    }
                    default -> System.out.println("Opción no válida, intenta otra vez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: debes ingresar un número.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("----- Menú -----");
        System.out.println("1. Registrar cliente básico");
        System.out.println("2. Registrar cliente premium");
        System.out.println("3. Registrar instructor");
        System.out.println("4. Crear rutina y ejercicios");
        System.out.println("5. Asignar rutina a cliente");
        System.out.println("6. Mostrar clientes y rutinas");
        System.out.println("7. Actualizar cliente a PREMIUM");
        System.out.println("8. Usar servicio premium");
        System.out.println("9. Enviar mensajes motivacionales");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarRutinaEjemplo() {
        Rutina r = new Rutina("Rutina Inicial");
        r.agregarEjercicio(new Ejercicio("Calentamiento", "Bajo", 5));
        r.agregarEjercicio(new Ejercicio("Estiramiento", "Bajo", 10));
        rutinas.add(r);
    }

    private static void registrarCliente(boolean premium) {
        try {
            System.out.print("ID: ");
            String id = sc.nextLine().trim();
            if (id.isEmpty()) { System.out.println("ID no puede estar vacío."); return; }

            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) { System.out.println("Nombre no puede estar vacío."); return; }

            System.out.print("Correo: ");
            String correo = sc.nextLine().trim();
            if (correo.isEmpty()) { System.out.println("Correo no puede estar vacío."); return; }

            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Altura (m): ");
            double altura = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Edad: ");
            int edad = Integer.parseInt(sc.nextLine().trim());

            if (premium) {
                PremiumCard card = new PremiumCard("Premium Mensual");
                PremiumCliente pc = new PremiumCliente(id, nombre, correo, peso, altura, edad, card);
                clientes.add(pc);
                System.out.println("Cliente premium creado.");
            } else {
                Cliente c = new Cliente(id, nombre, correo, peso, altura, edad);
                clientes.add(c);
                System.out.println("Cliente básico creado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: formato numérico inválido. Operación cancelada.");
        }
    }

    private static void registrarInstructor() {
        System.out.print("ID Instructor: ");
        String id = sc.nextLine().trim();
        System.out.print("Nombre Instructor: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Correo Instructor: ");
        String correo = sc.nextLine().trim();
        Instructor ins = new Instructor(id, nombre, correo);
        instructores.add(ins);
        System.out.println("Instructor registrado.");
    }

    private static Cliente buscarClientePorId(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    private static void crearRutinaInteractiva() {
        System.out.print("Nombre de la nueva rutina: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Nombre inválido.");
            return;
        }
        Rutina r = new Rutina(nombre);
        while (true) {
            System.out.print("Agregar ejercicio (nombre) o 'salir' para terminar: ");
            String ne = sc.nextLine().trim();
            if (ne.equalsIgnoreCase("salir")) break;

            System.out.print("Nivel del ejercicio: ");
            String nivel = sc.nextLine().trim();

            try {
                System.out.print("Duración (min): ");
                int dur = Integer.parseInt(sc.nextLine().trim());
                r.agregarEjercicio(new Ejercicio(ne, nivel, dur));
                System.out.println("Ejercicio agregado.");
            } catch (NumberFormatException ex) {
                System.out.println("Duración inválida. No se agregó el ejercicio.");
            }
        }
        rutinas.add(r);
        System.out.println("Rutina creada: " + r.getNombreRutina());
    }

    private static void asignarRutinaACliente() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Clientes:");
        for (Cliente c : clientes) {
            System.out.println("- " + c.getId() + " : " + c.getNombre());
        }

        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine().trim();
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas disponibles.");
            return;
        }

        System.out.println("Rutinas disponibles:");
        for (int i = 0; i < rutinas.size(); i++) {
            System.out.println((i + 1) + ". " + rutinas.get(i).getNombreRutina());
        }

        try {
            System.out.print("Seleccione número de rutina: ");
            int sel = Integer.parseInt(sc.nextLine().trim());
            if (sel < 1 || sel > rutinas.size()) {
                System.out.println("Selección inválida.");
                return;
            }
            c.asignarRutina(rutinas.get(sel - 1));
            System.out.println("Rutina asignada a " + c.getNombre());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void mostrarClientesYRutinas() {
        System.out.println("---- CLIENTES ----");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente c : clientes) {
                System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | Correo: " + c.getCorreo());
                System.out.printf("Peso: %.2f kg | Altura: %.2f m | Edad: %d\n", c.getPesoKg(), c.getAlturaM(), c.getEdad());
                System.out.println("Tipo: " + (c instanceof PremiumCliente ? "Premium" : "Básico"));
                System.out.println("Rutina asignada: " + (c.getRutina() != null ? c.getRutina().getNombreRutina() : "Ninguna"));
                System.out.println("----------------------------");
            }
        }

        System.out.println("---- RUTINAS ----");
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas creadas.");
        } else {
            for (Rutina r : rutinas) {
                r.mostrarRutina();
            }
        }
    }

    private static void comprarPremium() {
        System.out.print("Ingrese ID del cliente a actualizar a PREMIUM: ");
        String id = sc.nextLine().trim();
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        if (c instanceof PremiumCliente) {
            System.out.println("El cliente ya es premium.");
            return;
        }

        PremiumCard card = new PremiumCard("Premium Mensual");
        PremiumCliente pc = new PremiumCliente(c.getId(), c.getNombre(), c.getCorreo(),
                c.getPesoKg(), c.getAlturaM(), c.getEdad(), card);
        pc.asignarRutina(c.getRutina());
        clientes.remove(c);
        clientes.add(pc);
        System.out.println("Cliente actualizado a PREMIUM.");
    }

    private static void usarServicioPremium() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine().trim();
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        if (!(c instanceof PremiumCliente)) {
            System.out.println("Este cliente no tiene tarjeta premium.");
            return;
        }

        PremiumCliente pc = (PremiumCliente) c;
        pc.mostrarBeneficios();
        System.out.print("Ingrese servicio exacto que desea usar: ");
        String servicio = sc.nextLine().trim();
        try {
            pc.usarServicio(servicio);
        } catch (ServicioNoDisponibleException e) {
            System.out.println("No disponible: " + e.getMessage());
        }
    }
}
