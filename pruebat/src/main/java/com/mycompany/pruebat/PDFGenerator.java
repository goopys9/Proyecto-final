package com.mycompany.pruebat;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFGenerator {

    // ===========================
    // PDF INDIVIDUAL DEL CLIENTE
    // ===========================
    public static void generarPDFCliente(Cliente cliente) {
        String RUTA_BASE = "./PDFs/";
        crearCarpetaSiNoExiste(RUTA_BASE);

        String nombreArchivo = RUTA_BASE + "Cliente_" + cliente.getNombre().replace(" ", "_") + ".pdf";

        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // Encabezado bonito 💖
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.PINK);
            Paragraph titulo = new Paragraph("Reporte del Cliente - Nagomi Pilates\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Info del cliente
            documento.add(new Paragraph("Nombre: " + cliente.getNombre()));
            documento.add(new Paragraph("Correo: " + cliente.getCorreo()));
            documento.add(new Paragraph("Fecha de Registro: " + 
                    cliente.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

            if (cliente.getTarjeta() != null) {
                documento.add(new Paragraph("Tipo de Tarjeta: " + cliente.getTarjeta().getTipo()));
                documento.add(new Paragraph("Precio: $" + cliente.getTarjeta().getPrecio()));
                documento.add(new Paragraph("Servicios: " + cliente.getTarjeta().getServiciosDisponibles()));
            } else {
                documento.add(new Paragraph("Tarjeta: No asignada."));
            }

            if (cliente.getInstructor() != null) {
                documento.add(new Paragraph("Instructor asignado: " + cliente.getInstructor().getNombre()));
            } else {
                documento.add(new Paragraph("Instructor: No asignado."));
            }

            documento.add(new Paragraph("\nGracias por ser parte de Nagomi Pilates ❤"));

            documento.close();
            System.out.println("✅ PDF generado correctamente: " + nombreArchivo);

            abrirPDF(nombreArchivo);

        } catch (Exception e) {
            System.err.println("❌ Error al generar PDF: " + e.getMessage());
        }
    }

    // ===========================
    // PDF GENERAL DE CLIENTES
    // ===========================
    public static void generarPDFClientes(List<Cliente> clientes) {
        String RUTA_BASE = "./PDFs/";
        crearCarpetaSiNoExiste(RUTA_BASE);

        String nombreArchivo = RUTA_BASE + "Clientes_General.pdf";

        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.PINK);
            Paragraph titulo = new Paragraph("Listado de Clientes - Nagomi Pilates\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.addCell("Nombre");
            tabla.addCell("Correo");
            tabla.addCell("Fecha Registro");
            tabla.addCell("Tarjeta");
            tabla.addCell("Instructor");

            for (Cliente c : clientes) {
                tabla.addCell(c.getNombre());
                tabla.addCell(c.getCorreo());
                tabla.addCell(c.getFechaRegistro().toString());
                tabla.addCell(c.getTarjeta() != null ? c.getTarjeta().getTipo() : "Sin tarjeta");
                tabla.addCell(c.getInstructor() != null ? c.getInstructor().getNombre() : "Sin instructor");
            }

            documento.add(tabla);
            documento.close();

            System.out.println("✅ PDF general de clientes generado: " + nombreArchivo);

            abrirPDF(nombreArchivo);

        } catch (Exception e) {
            System.err.println("❌ Error al generar PDF general: " + e.getMessage());
        }
    }

    // ===========================
    // PDF GENERAL DE INSTRUCTORES
    // ===========================
    public static void generarPDFInstructores(List<Instructor> instructores) {
        String RUTA_BASE = "./PDFs/";
        crearCarpetaSiNoExiste(RUTA_BASE);

        String nombreArchivo = RUTA_BASE + "Instructores_General.pdf";

        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.PINK);
            Paragraph titulo = new Paragraph("Listado de Instructores - Nagomi Pilates\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.addCell("Nombre");
            tabla.addCell("Cantidad de Clientes");
            tabla.addCell("Clientes Asignados");

            for (Instructor i : instructores) {
                tabla.addCell(i.getNombre());
                tabla.addCell(String.valueOf(i.getCantidadClientes()));
                tabla.addCell(i.getClientesAsignados().isEmpty()
                        ? "Sin clientes"
                        : String.join(", ", i.getClientesAsignados()
                                .stream()
                                .map(Cliente::getNombre)
                                .toList()));
            }

            documento.add(tabla);
            documento.close();

            System.out.println("✅ PDF de instructores generado: " + nombreArchivo);

            abrirPDF(nombreArchivo);

        } catch (Exception e) {
            System.err.println("❌ Error al generar PDF de instructores: " + e.getMessage());
        }
    }

    // ===========================
    // MÉTODOS AUXILIARES
    // ===========================

    // Crea la carpeta PDFs si no existe
    private static void crearCarpetaSiNoExiste(String ruta) {
        File carpeta = new File(ruta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
            System.out.println("📂 Carpeta creada: " + ruta);
        }
    }

    // Abre el PDF automáticamente (si tu sistema lo permite)
    private static void abrirPDF(String rutaArchivo) {
        try {
            File pdf = new File(rutaArchivo);
            if (pdf.exists()) {
                java.awt.Desktop.getDesktop().open(pdf);
            }
        } catch (Exception e) {
            System.err.println("⚠ No se pudo abrir el PDF automáticamente.");
        }
    }
}