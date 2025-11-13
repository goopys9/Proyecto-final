package com.mycompany.luciproyec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Cliente> clientes = new ArrayList<>();
            List<Instructor> instructores = new ArrayList<>();
            boolean salir = false;

            while (!salir) {
                System.out.println("\n--- MENÚ NAGOMI PILATES ---");
                System.out.println("1. Registrar cliente");
                System.out.println("2. Registrar instructor");
                System.out.println("3. Asignar cliente a instructor");
                System.out.println("4. Mostrar clientes");
                System.out.println("5. Mostrar instructores");
                System.out.println("6. Mostrar top 3 instructores con más clientes");
                System.out.println("7. Mostrar top 3 clientes más antiguos");
                System.out.println("8. Mostrar servicios de tarjeta Premium");
                System.out.println("9. Mostrar precios de las tarjetas");
                System.out.println("10. Generar PDF de clientes e instructores");
                System.out.println("11. Generar PDF top 3 instructores");
                System.out.println("12. Generar PDF top 3 clientes antiguos");
                System.out.println("13. Salir");
                System.out.print("Seleccione una opción: ");

                int op;
                try {
                    op = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("❌ Ingrese un número válido.");
                    continue;
                }

                switch (op) {
                    case 1: {
                        System.out.println("Ingrese ID del cliente:");
                        String id = sc.nextLine();
                        System.out.println("Ingrese nombre:");
                        String nombre = sc.nextLine();
                        System.out.println("Ingrese correo:");
                        String correo = sc.nextLine();

                        double peso;
                        double altura;
                        int edad;

                        try {
                            System.out.println("Ingrese peso (kg):");
                            peso = Double.parseDouble(sc.nextLine());
                            System.out.println("Ingrese altura (m):");
                            altura = Double.parseDouble(sc.nextLine());
                            System.out.println("Ingrese edad:");
                            edad = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Entrada numérica inválida. Registro cancelado.");
                            break;
                        }

                        System.out.println("Seleccione tipo de tarjeta (1. Premium / 2. Básica):");
                        int tipo;
                        try {
                            tipo = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Entrada inválida. Se asignará tarjeta Básica por defecto.");
                            tipo = 2;
                        }

                        Cliente nuevo;
                        if (tipo == 1) {
                            nuevo = new PremiumCliente(id, nombre, correo, peso, altura, edad);
                            System.out.println("💳 Tarjeta Premium adquirida por $60,000.");
                        } else {
                            nuevo = new BasicCliente(id, nombre, correo, peso, altura, edad);
                            System.out.println("💳 Tarjeta Básica adquirida por $30,000.");
                        }

                        clientes.add(nuevo);
                        System.out.println("✅ Cliente registrado correctamente.");
                        break;
                    }

                    case 2: {
                        System.out.println("Ingrese ID del instructor:");
                        String id = sc.nextLine();
                        System.out.println("Ingrese nombre:");
                        String nombre = sc.nextLine();
                        System.out.println("Ingrese correo:");
                        String correo = sc.nextLine();

                        Instructor ins = new Instructor(id, nombre, correo);
                        instructores.add(ins);
                        System.out.println("✅ Instructor registrado correctamente.");
                        break;
                    }

                    case 3: {
                        if (clientes.isEmpty() || instructores.isEmpty()) {
                            System.out.println("⚠ Debe haber al menos un cliente y un instructor registrados.");
                            break;
                        }

                        System.out.println("Ingrese ID del instructor:");
                        String idIns = sc.nextLine();
                        System.out.println("Ingrese ID del cliente:");
                        String idCli = sc.nextLine();

                        Instructor ins = instructores.stream()
                                .filter(i -> i.getId().equalsIgnoreCase(idIns))
                                .findFirst()
                                .orElse(null);

                        Cliente cli = clientes.stream()
                                .filter(c -> c.getId().equalsIgnoreCase(idCli))
                                .findFirst()
                                .orElse(null);

                        if (ins != null && cli != null) {
                            ins.agregarCliente(cli);
                            System.out.println("✅ Cliente asignado correctamente al instructor.");
                        } else {
                            System.out.println("❌ Instructor o cliente no encontrado.");
                        }
                        break;
                    }

                    case 4: {
                        System.out.println("\n--- CLIENTES REGISTRADOS ---");
                        if (clientes.isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                        } else {
                            clientes.forEach(System.out::println);
                        }
                        break;
                    }

                    case 5: {
                        System.out.println("\n--- INSTRUCTORES REGISTRADOS ---");
                        if (instructores.isEmpty()) {
                            System.out.println("No hay instructores registrados.");
                        } else {
                            instructores.forEach(System.out::println);
                        }
                        break;
                    }

                    case 6: {
                        if (instructores.isEmpty()) {
                            System.out.println("No hay instructores registrados.");
                            break;
                        }
                        instructores.sort((a, b) -> Integer.compare(b.getClientesCount(), a.getClientesCount()));
                        System.out.println("\n🏆 TOP 3 INSTRUCTORES CON MÁS CLIENTES 🏆");
                        for (int i = 0; i < Math.min(3, instructores.size()); i++) {
                            Instructor ins = instructores.get(i);
                            System.out.println((i + 1) + ". " + ins.getNombre() + " - " + ins.getClientesCount() + " clientes");
                        }
                        break;
                    }

                    case 7: {
                        if (clientes.isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                            break;
                        }
                        clientes.sort(Comparator.comparing(Cliente::getFechaRegistro));
                        System.out.println("\n👴 TOP 3 CLIENTES MÁS ANTIGUOS 👵");
                        for (int i = 0; i < Math.min(3, clientes.size()); i++) {
                            Cliente c = clientes.get(i);
                            System.out.println((i + 1) + ". " + c.getNombre() + " (registrado el " + c.getFechaRegistro() + ")");
                        }
                        break;
                    }

                    case 8: {
                        PremiumCard card = new PremiumCard("Premium");
                        System.out.println("Servicios disponibles en la Tarjeta Premium:");
                        for (String serv : card.getServiciosDisponibles()) {
                            System.out.println("- " + serv);
                        }
                        break;
                    }

                    case 9: {
                        System.out.println("💰 Precio Tarjeta Premium: $60,000");
                        System.out.println("💰 Precio Tarjeta Básica: $30,000");
                        break;
                    }

                    case 10: {
                        try {
                            PDFGenerator.generarReporte(clientes, instructores, "Reporte_Nagomi.pdf");
                            System.out.println("✅ PDF general generado correctamente.");
                        } catch (Exception e) {
                            System.err.println("❌ Error al generar PDF general: " + e.getMessage());
                        }
                        break;
                    }

                    case 11: {
                        try {
                            PDFGenerator.generarTop3Instructores(instructores);
                            System.out.println("✅ PDF top 3 instructores generado correctamente.");
                        } catch (Exception e) {
                            System.err.println("❌ Error al generar PDF de instructores: " + e.getMessage());
                        }
                        break;
                    }

                    case 12: {
                        try {
                            PDFGenerator.generarTop3Antiguos(clientes);
                            System.out.println("✅ PDF top 3 clientes antiguos generado correctamente.");
                        } catch (Exception e) {
                            System.err.println("❌ Error al generar PDF de clientes antiguos: " + e.getMessage());
                        }
                        break;
                    }

                    case 13: {
                        System.out.println("👋 Saliendo del sistema...");
                        salir = true;
                        break;
                    }

                    default: {
                        System.out.println("❌ Opción no válida.");
                        break;
                    }
                }
            }
        }
    }
}
