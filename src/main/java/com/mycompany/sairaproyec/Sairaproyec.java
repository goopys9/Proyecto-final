package com.mycompany.sairaproyec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sairaproyec {

  private static final Scanner sc = new Scanner(System.in);
  private static final List<Cliente> clientes = new ArrayList<>();
  private static final List<Instructor> instructores = new ArrayList<>();
  private static final Persistible persist = new ArchivoPersistencia();

  public static void main(String[] args) {

      // Instructores iniciales    
      instructores.add(new Instructor("Carlos"));    
      instructores.add(new Instructor("Ana"));    
      instructores.add(new Instructor("Luis"));    

      int opcion;    
      do {    
          System.out.println("\n===== NAGOMI PILATES - GESTION =====");    
          System.out.println("1. Registrar cliente");    
          System.out.println("2. Comprar tarjeta");    
          System.out.println("3. Asignar instructor a cliente");    
          System.out.println("4. Registrar instructor");    
          System.out.println("5. Mostrar clientes e instructores");    
          System.out.println("6. Guardar datos");    
          System.out.println("7. Cargar datos");
          System.out.println("8. Generar PDF individual por cliente");
          System.out.println("9. Generar PDF con todos los clientes");
          System.out.println("10. Generar PDF de instructores");
          System.out.println("0. Salir");    
          System.out.print("Seleccione opcion: ");    
          opcion = readInt();    

          switch (opcion) {    
              case 1 -> registrarCliente();    
              case 2 -> comprarTarjeta();    
              case 3 -> asignarInstructor();    
              case 4 -> registrarInstructor();    
              case 5 -> mostrarDatos();    
              case 6 -> guardarDatos();    
              case 7 -> cargarDatos();    
              case 8 -> generarPDFsIndividuales();
              case 9 -> PDFGenerator.generarPDFClientes(clientes);
              case 10 -> PDFGenerator.generarPDFInstructores(instructores);
              case 0 -> System.out.println("Saliendo del sistema...");    
              default -> System.out.println("Opción no válida.");    
          }    

      } while (opcion != 0);

  }

  // ------------------------ Registrar cliente ------------------------
  private static void registrarCliente() {
      try {
          System.out.print("Nombre del cliente: ");
          String nombre = sc.nextLine().trim();
          System.out.print("Correo: ");
          String correo = sc.nextLine().trim();

          validarEmail(correo);

          Cliente c = new Cliente(nombre, correo);    
          clientes.add(c);    
          System.out.println("✔ Cliente registrado correctamente.");    

      } catch (InvalidEmailException e) {    
          System.out.println("❌ Email inválido: " + e.getMessage());    
      } catch (Exception e) {    
          System.out.println("❌ Error registrando cliente: " + e.getMessage());    
      }
  }

  // ------------------------ Comprar tarjeta ------------------------
  private static void comprarTarjeta() {
      if (clientes.isEmpty()) {
          System.out.println("⚠ No hay clientes registrados.");
          return;
      }
      mostrarClientesSimple();
      System.out.print("Seleccione cliente (nº): ");
      int i = readInt() - 1;
      if (i < 0 || i >= clientes.size()) {
          System.out.println("Índice inválido.");
          return;
      }
      Cliente c = clientes.get(i);
      System.out.println("Seleccione tipo de tarjeta:");
      System.out.println("1. Básica");
      System.out.println("2. Media");
      System.out.println("3. Premium");
      System.out.print("Opcion: ");
      int op = readInt();

      switch (op) {    
          case 1 -> c.setTarjeta(new BasicCard());    
          case 2 -> c.setTarjeta(new MediumCard());    
          case 3 -> c.setTarjeta(new PremiumCard());    
          default -> System.out.println("❌ Opción no válida.");    
      }    

      System.out.println("✔ Tarjeta asignada correctamente a " + c.getNombre());
  }

  // ------------------------ Asignar Instructor ------------------------
  private static void asignarInstructor() {
      if (clientes.isEmpty()) {
          System.out.println("⚠ No hay clientes.");
          return;
      }
      if (instructores.isEmpty()) {
          System.out.println("⚠ No hay instructores.");
          return;
      }
      mostrarClientesSimple();
      System.out.print("Seleccione cliente Nº: ");
      int cIndex = readInt() - 1;
      if (cIndex < 0 || cIndex >= clientes.size()) {
          System.out.println("Índice cliente inválido.");
          return;
      }
      Cliente cliente = clientes.get(cIndex);

      mostrarInstructoresSimple();    
      System.out.print("Seleccione instructor Nº: ");    
      int iIndex = readInt() - 1;    
      if (iIndex < 0 || iIndex >= instructores.size()) {    
          System.out.println("Índice instructor inválido.");    
          return;    
      }    
      Instructor instructor = instructores.get(iIndex);    

      cliente.setInstructor(instructor);    
      instructor.asignarCliente(cliente);    

      System.out.println("✔ Instructor asignado correctamente.");
  }

  // ------------------------ Registrar instructor ------------------------
  private static void registrarInstructor() {
      System.out.print("Nombre del nuevo instructor: ");
      String nombre = sc.nextLine().trim();
      Instructor ins = new Instructor(nombre);
      instructores.add(ins);
      System.out.println("✔ Instructor registrado: " + nombre);
  }

  // ------------------------ Mostrar datos ------------------------
  private static void mostrarDatos() {
      System.out.println("\n--- CLIENTES ---");
      if (clientes.isEmpty()) System.out.println("Sin clientes.");
      for (Cliente c : clientes) {
          System.out.println(c);
      }

      System.out.println("\n--- INSTRUCTORES ---");    
      if (instructores.isEmpty()) System.out.println("Sin instructores.");    
      for (Instructor i : instructores) {    
          System.out.println(i);    
          if (!i.getClientesAsignados().isEmpty()) {    
              System.out.print("  Lista clientes: ");    
              for (Cliente cl : i.getClientesAsignados()) {    
                  System.out.print(cl.getNombre() + ", ");    
              }    
              System.out.println();    
          }    
      }

  }

  // ------------------------ Guardar y cargar ------------------------
  private static void guardarDatos() {
      try {
          persist.guardarClientes(clientes, "clientes.txt");
          persist.guardarInstructores(instructores, "instructores.txt");
          System.out.println("✔ Datos guardados.");
      } catch (Exception e) {
          System.out.println("❌ Error guardando: " + e.getMessage());
      }
  }

  private static void cargarDatos() {
      try {
          List<Cliente> cargados = persist.cargarClientes("clientes.txt");
          List<Instructor> insCargados = persist.cargarInstructores("instructores.txt");

          if (!cargados.isEmpty()) {
              clientes.clear();
              clientes.addAll(cargados);
          }
          if (!insCargados.isEmpty()) {
              instructores.clear();
              instructores.addAll(insCargados);
          }
          System.out.println("✔ Datos cargados.");
      } catch (Exception e) {
          System.out.println("❌ Error cargando: " + e.getMessage());
      }
  }

  // ------------------------ PDF INDIVIDUAL POR CLIENTE ------------------------
  private static void generarPDFsIndividuales() {
      if (clientes.isEmpty()) {
          System.out.println("⚠ No hay clientes para generar PDF.");
          return;
      }

      for (Cliente c : clientes) {
          PDFGenerator.generarPDFCliente(c);
      }

      System.out.println("✔ PDFs individuales generados correctamente.");
  }

  // ------------------------ Auxiliares ------------------------
  private static void mostrarClientesSimple() {
      System.out.println("\n--- CLIENTES ---");
      for (int i = 0; i < clientes.size(); i++) {
          System.out.println((i + 1) + ". " + clientes.get(i).getNombre() + " (" + clientes.get(i).getCorreo() + ")");
      }
  }

  private static void mostrarInstructoresSimple() {
      System.out.println("\n--- INSTRUCTORES ---");
      for (int i = 0; i < instructores.size(); i++) {
          System.out.println((i + 1) + ". " + instructores.get(i).getNombre());
      }
  }

  private static int readInt() {
      try {
          String line = sc.nextLine();
          return Integer.parseInt(line.trim());
      } catch (Exception e) {
          return -1;
      }
  }

  // ------------------------ Validación email ------------------------
  private static void validarEmail(String correo) throws InvalidEmailException {
      if (correo == null || correo.isBlank()) {
          throw new InvalidEmailException("El correo no puede estar vacío.");
      }
      int at = correo.indexOf('@');
      if (at <= 0 || at == correo.length() - 1) {
          throw new InvalidEmailException("El correo debe contener '@' en posición válida.");
      }
      String dominio = correo.substring(at + 1);
      if (!dominio.contains(".") || dominio.startsWith(".") || dominio.endsWith(".")) {
          throw new InvalidEmailException("Dominio del correo no válido.");
      }
  }
}