package com.mycompany.sairaproyec;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {

    // ============================
    //  ESTILOS Y COLORES
    // ============================
    private static final BaseColor COLOR_PRIMARIO = new BaseColor(52, 152, 219);       // Azul suave
    private static final BaseColor COLOR_SECUNDARIO = new BaseColor(236, 240, 241);   // Gris claro
    private static final BaseColor COLOR_HEADER = new BaseColor(44, 62, 80);          // Azul oscuro

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, COLOR_HEADER);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.WHITE);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 11);

    // ============================
    //  MÉTODO PARA AGREGAR LOGO
    // ============================
    private static void agregarLogo(Document doc) {
        try {
            Image logo = Image.getInstance("imagenes/logo.jpg"); // <-- Cambia la ruta si es necesario
            logo.scaleToFit(120, 120);
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.setSpacingAfter(15);
            doc.add(logo);
        } catch (Exception e) {
            System.out.println("⚠ No se pudo cargar el logo: " + e.getMessage());
        }
    }

    // ============================
    //  TÍTULO CON LÍNEA ESTÉTICA
    // ============================
    private static void agregarTitulo(Document doc, String titulo) throws DocumentException {
        Paragraph p = new Paragraph(titulo, TITLE_FONT);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(12);
        doc.add(p);

        LineSeparator ls = new LineSeparator();
        ls.setLineColor(COLOR_PRIMARIO);
        ls.setPercentage(80);
        doc.add(ls);
        doc.add(new Paragraph(" "));
    }

    private static void agregarHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, HEADER_FONT));
        cell.setBackgroundColor(COLOR_PRIMARIO);
        cell.setPadding(7);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static PdfPCell celda(String texto) {
        PdfPCell c = new PdfPCell(new Phrase(texto, NORMAL_FONT));
        c.setPadding(6);
        c.setBackgroundColor(COLOR_SECUNDARIO);
        return c;
    }

    // ============================
    //  PDF INDIVIDUAL POR CLIENTE
    // ============================
    public static void generarPDFCliente(Cliente cliente) {
        try {
            String fileName = "Cliente_" + cliente.getNombre().replace(" ", "_") + ".pdf";
            Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            doc.open();

            agregarLogo(doc);
            agregarTitulo(doc, "Ficha del Cliente");
            agregarClienteCompleto(doc, cliente);

            doc.close();
            System.out.println("✔ PDF individual generado: " + fileName);

        } catch (Exception e) {
            System.out.println("❌ Error generando PDF cliente: " + e.getMessage());
        }
    }

    // ============================
    //  PDF CON TODOS LOS CLIENTES
    // ============================
    public static void generarPDFClientes(List<Cliente> clientes) {
        try {
            Document doc = new Document(PageSize.A4.rotate(), 40, 40, 40, 40);
            PdfWriter.getInstance(doc, new FileOutputStream("Clientes_Todos.pdf"));
            doc.open();

            agregarLogo(doc);
            agregarTitulo(doc, "Listado de Clientes");

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 4, 3, 3, 3, 4});

            agregarHeader(table, "Nombre");
            agregarHeader(table, "Correo");
            agregarHeader(table, "Fecha Registro");
            agregarHeader(table, "Tarjeta");
            agregarHeader(table, "Instructor");
            agregarHeader(table, "Rutina");

            for (Cliente c : clientes) {
                table.addCell(celda(c.getNombre()));
                table.addCell(celda(c.getCorreo()));
                table.addCell(celda(c.getFechaRegistro().toString()));
                table.addCell(celda(c.getTarjeta() != null ? c.getTarjeta().getTipo() : "-"));
                table.addCell(celda(c.getInstructor() != null ? c.getInstructor().getNombre() : "-"));
                table.addCell(celda(c.getRutinaAsignada() != null ? c.getRutinaAsignada().getNombre() : "-"));
            }

            doc.add(table);
            doc.close();
            System.out.println("✔ PDF listado clientes generado.");

        } catch (Exception e) {
            System.out.println("❌ Error generando PDF clientes: " + e.getMessage());
        }
    }

    // ============================
    //  PDF INSTRUCTORES
    // ============================
    public static void generarPDFInstructores(List<Instructor> instructores) {
        try {
            Document doc = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(doc, new FileOutputStream("Instructores.pdf"));
            doc.open();

            agregarLogo(doc);
            agregarTitulo(doc, "Listado de Instructores");

            for (Instructor i : instructores) {
                Paragraph p = new Paragraph(i.getNombre(), TITLE_FONT);
                p.setSpacingBefore(15);
                doc.add(p);

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(90);
                table.setSpacingBefore(10);

                agregarHeader(table, "Dato");
                agregarHeader(table, "Valor");

                table.addCell(celda("Correo"));
                table.addCell(celda(i.getCorreo()));

                table.addCell(celda("Cantidad Clientes"));
                table.addCell(celda(String.valueOf(i.getCantidadClientes())));

                doc.add(table);

                if (!i.getClientesAsignados().isEmpty()) {
                    Paragraph sub = new Paragraph("Clientes asignados:", HEADER_FONT);
                    sub.setSpacingBefore(8);
                    doc.add(sub);

                    for (Cliente c : i.getClientesAsignados()) {
                        doc.add(new Paragraph("- " + c.getNombre(), NORMAL_FONT));
                    }
                }

                doc.add(new LineSeparator());
            }

            doc.close();
            System.out.println("✔ PDF instructores generado.");

        } catch (Exception e) {
            System.out.println("❌ Error generando PDF instructores: " + e.getMessage());
        }
    }

    // ============================
    //  DATOS COMPLETOS DEL CLIENTE
    // ============================
    private static void agregarClienteCompleto(Document doc, Cliente c) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 7});

        table.addCell(celda("Nombre"));
        table.addCell(celda(c.getNombre()));

        table.addCell(celda("Correo"));
        table.addCell(celda(c.getCorreo()));

        table.addCell(celda("Fecha registro"));
        table.addCell(celda(c.getFechaRegistro().toString()));

        table.addCell(celda("Tarjeta"));
        table.addCell(celda(c.getTarjeta() != null ? c.getTarjeta().toString() : "Sin tarjeta"));

        table.addCell(celda("Instructor"));
        table.addCell(celda(c.getInstructor() != null ? c.getInstructor().getNombre() : "Sin instructor"));

        table.addCell(celda("Rutina"));
        table.addCell(celda(c.getRutinaAsignada() != null ? c.getRutinaAsignada().getNombre() : "Sin rutina"));

        doc.add(table);
        
        if (c.getRutinaAsignada() != null && !c.getRutinaAsignada().getEjercicios().isEmpty()) {
    doc.add(new Paragraph("\nEjercicios:", HEADER_FONT));
    for (Ejercicio e : c.getRutinaAsignada().getEjercicios()) {
        doc.add(new Paragraph("• " + e.toString(), NORMAL_FONT));
    }
}
    }
}