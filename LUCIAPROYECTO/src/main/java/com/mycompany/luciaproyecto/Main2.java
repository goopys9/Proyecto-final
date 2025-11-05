package com.mycompany.luciaproyecto;  // Paquete del proyecto

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // Importaciones necesarias

public class Main2 {
    // Scanner para leer datos del usuario
    private static final Scanner sc = new Scanner(System.in);
    // Listas para guardar clientes, instructores y rutinas
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Instructor> instructores = new ArrayList<>();
    private static final List<Rutina> rutinas = new ArrayList<>();

    public static void Main2(String[] args) {  
        System.out.println("=== Nagomi Pilates - App de seguimiento (Main2) ===");  
        cargarRutinaEjemplo();  // Carga una rutina por defecto

        boolean salir = false; // Controla que el menú se repita hasta elegir salir
        while (!salir) {  // Menú principal en bucle hasta que el usuario elija salir
            mostrarMenu();  
            String entrada = sc.nextLine().trim();  // Lee la opción del usuario
            try {  
                int opcion = Integer.parseInt(entrada);  // Convierte la entrada a número
                switch (opcion) {  
                    case 1 -> registrarCliente(false);  // Cliente básico
                    case 2 -> registrarCliente(true);   // Cliente premium
                    case 3 -> registrarInstructor();    // Nuevo instructor
                    case 4 -> crearRutinaInteractiva(); // Crear rutina con ejercicios
                    case 5 -> asignarRutinaACliente();  // Asignar rutina a cliente
                    case 6 -> mostrarClientesYRutinas();// Mostrar información
                    case 7 -> comprarPremium();         // Actualizar a premium
                    case 8 -> usarServicioPremium();    // Usar servicios premium
                    case 9 -> NotificationService.enviarMotivacionales(clientes); // Enviar mensajes
                    case 0 -> {  
                        System.out.println("Saliendo... ¡Muchos exitos en tu entrega!");  
                        salir = true;  // Finaliza el bucle
                    }  
                    default -> System.out.println("Opcion no valida, intenta otra vez."); //opcion no valida 
                }  
                
            } catch (NumberFormatException e) {  
                System.out.println("Entrada invalida: debes ingresar un numero.");  
            } catch (Exception e) {  
                System.out.println("Ocurrio un error: " + e.getMessage());  
            }  
            System.out.println();  
        }  
    }  

    private static void mostrarMenu() {  // Imprime el menú principal
        System.out.println("----- Menú -----");  
        System.out.println("1. Registrar cliente basico");  
        System.out.println("2. Registrar cliente premium");  
        System.out.println("3. Registrar instructor");  
        System.out.println("4. Crear rutina y ejercicios");  
        System.out.println("5. Asignar rutina a cliente");  
        System.out.println("6. Mostrar clientes y rutinas");  
        System.out.println("7. Actualizar cliente a PREMIUM");  
        System.out.println("8. Usar servicio premium");  
        System.out.println("9. Enviar mensajes motivacionales");  
        System.out.println("0. Salir");  
        System.out.print("Seleccione una opcion: ");  
    }  

    private static void cargarRutinaEjemplo() {  // Crea una rutina de ejemplo
        Rutina r = new Rutina("Rutina Inicial");  
        r.agregarEjercicio(new Ejercicio("Calentamiento", "Bajo", 5));  
        r.agregarEjercicio(new Ejercicio("Estiramiento", "Bajo", 10));  
        rutinas.add(r);  
    }  

    private static void registrarCliente(boolean premium) {  // Registra cliente
        try {  
            System.out.print("ID: ");  
            String id = sc.nextLine().trim();  
            if (id.isEmpty()) { System.out.println("ID no puede estar vacio."); return; }  

            System.out.print("Nombre: ");  
            String nombre = sc.nextLine().trim();  
            if (nombre.isEmpty()) { System.out.println("Nombre no puede estar vacio."); return; }  

            System.out.print("Correo: ");  
            String correo = sc.nextLine().trim();  
            if (correo.isEmpty()) { System.out.println("Correo no puede estar vacio."); return; }  

            // Datos físicos del cliente
            System.out.print("Peso (kg): ");  
            double peso = Double.parseDouble(sc.nextLine().trim());  
            System.out.print("Altura (m): ");  
            double altura = Double.parseDouble(sc.nextLine().trim());  
            System.out.print("Edad: ");  
            int edad = Integer.parseInt(sc.nextLine().trim());  

            // Si es premium crea una tarjeta
            if (premium) {  
                PremiumCard card = new PremiumCard("Premium Mensual");  
                PremiumCliente pc = new PremiumCliente(id, nombre, correo, peso, altura, edad, card);  
                clientes.add(pc);  
                System.out.println("Cliente premium creado.");  
            } else {  
                Cliente c = new Cliente(id, nombre, correo, peso, altura, edad);  
                clientes.add(c);  
                System.out.println("Cliente basico creado.");  
            }  
        } catch (NumberFormatException e) {  
            System.out.println("Error: formato numerico invalido. Operacion cancelada.");  
        }  
    }  

    private static void registrarInstructor() {  // Registra un nuevo instructor
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

    private static Cliente buscarClientePorId(String id) {  // Busca cliente por ID
        for (Cliente c : clientes) {  
            if (c.getId().equals(id)) return c;  
        }  
        return null;  
    }  

    private static void crearRutinaInteractiva() {  // Permite crear rutina manualmente
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
                System.out.print("Duracion (min): ");  
                int dur = Integer.parseInt(sc.nextLine().trim());  
                r.agregarEjercicio(new Ejercicio(ne, nivel, dur));  
                System.out.println("Ejercicio agregado.");  
            } catch (NumberFormatException ex) {  
                System.out.println("Duracion invalida. No se agrego el ejercicio.");  
            }  
        }  
        rutinas.add(r);  
        System.out.println("Rutina creada: " + r.getNombreRutina());  
    }  

    private static void asignarRutinaACliente() {  // Asigna una rutina a un cliente
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
            System.out.print("Seleccione numero de rutina: ");  
            int sel = Integer.parseInt(sc.nextLine().trim());  
            if (sel < 1 || sel > rutinas.size()) {  
                System.out.println("Selección invalida.");  
                return;  
            }  
            c.asignarRutina(rutinas.get(sel - 1));  
            System.out.println("Rutina asignada a " + c.getNombre());  
        } catch (NumberFormatException e) {  
            System.out.println("Entrada invalida.");  
        }  
    }  

    private static void mostrarClientesYRutinas() {  // Muestra todos los clientes y rutinas
        System.out.println("---- CLIENTES ----");  
        if (clientes.isEmpty()) {  
            System.out.println("No hay clientes registrados.");  
        } else {  
            for (Cliente c : clientes) {  
                System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | Correo: " + c.getCorreo());  
                System.out.printf("Peso: %.2f kg | Altura: %.2f m | Edad: %d\n", c.getPesoKg(), c.getAlturaM(), c.getEdad());  
                System.out.println("Tipo: " + (c instanceof PremiumCliente ? "Premium" : "Basico"));  
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

    private static void comprarPremium() {  // Convierte cliente básico a premium
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
        pc.asignarRutina(c.getRutina());  // Mantiene su rutina anterior
        clientes.remove(c);  
        clientes.add(pc);  
        System.out.println("Cliente actualizado a PREMIUM.");  
    }  

    private static void usarServicioPremium() {  // Usa un servicio de la tarjeta premium
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