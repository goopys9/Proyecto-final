package com.mycompany.luciproyec;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase que genera reportes PDF de clientes e instructores.
 * Usa iText sin depender de clases adicionales.
 */
public class PDFGenerator {

    /**
     * Genera un PDF con la lista completa de clientes.
     */
    public static void generarClientesPDF(List<Cliente> clientes) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Clientes_NagomiPilates.pdf"));
            doc.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            doc.add(new Paragraph("🏋️‍♀️ Lista de Clientes - Nagomi Pilates\n\n", tituloFont));

            PdfPTable table = new PdfPTable(6); // columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            // Encabezados
            String[] headers = {"ID", "Nombre", "Correo", "Peso", "Altura", "Fecha Registro"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Datos
            for (Cliente c : clientes) {
                table.addCell(new Phrase(c.getId(), normalFont));
                table.addCell(new Phrase(c.getNombre(), normalFont));
                table.addCell(new Phrase(c.getCorreo(), normalFont));
                table.addCell(new Phrase(String.valueOf(c.getPeso()), normalFont));
                table.addCell(new Phrase(String.valueOf(c.getAltura()), normalFont));
                table.addCell(new Phrase(c.getFechaRegistro().format(fmt), normalFont));
            }

            doc.add(table);
            doc.add(new Paragraph("\nTotal de clientes: " + clientes.size(), normalFont));

            System.out.println("✅ PDF generado correctamente: Clientes_NagomiPilates.pdf");

        } catch (FileNotFoundException | DocumentException e) {
            System.err.println("❌ Error al generar el PDF de clientes: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    /**
     * Genera un PDF con el top 3 de instructores con más clientes.
     */
    public static void generarTop3Instructores(List<Instructor> instructores) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Top3_Instructores.pdf"));
            doc.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            doc.add(new Paragraph("🏆 Top 3 Instructores con más clientes 🏆\n\n", tituloFont));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            String[] headers = {"Nombre", "Correo", "Cantidad de Clientes"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }

            instructores.sort((a, b) -> Integer.compare(b.getClientesCount(), a.getClientesCount()));

            int limite = Math.min(3, instructores.size());
            for (int i = 0; i < limite; i++) {
                Instructor ins = instructores.get(i);
                table.addCell(new Phrase(ins.getNombre(), normalFont));
                table.addCell(new Phrase(ins.getCorreo(), normalFont));
                table.addCell(new Phrase(String.valueOf(ins.getClientesCount()), normalFont));
            }

            doc.add(table);
            doc.add(new Paragraph("\nTotal de instructores registrados: " + instructores.size(), normalFont));

            System.out.println("✅ PDF generado correctamente: Top3_Instructores.pdf");

        } catch (FileNotFoundException | DocumentException e) {
            System.err.println("❌ Error al generar el PDF de instructores: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    /**
     * Genera un PDF con el top 3 de clientes más antiguos.
     */
    public static void generarTop3Antiguos(List<Cliente> clientes) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Top3_ClientesAntiguos.pdf"));
            doc.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            doc.add(new Paragraph("👴 Top 3 Clientes más antiguos 👵\n\n", tituloFont));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            String[] headers = {"Nombre", "Correo", "Fecha de Registro"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setGrayFill(0.9f);
                table.addCell(cell);
            }

            clientes.sort((a, b) -> a.getFechaRegistro().compareTo(b.getFechaRegistro()));

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            int limite = Math.min(3, clientes.size());
            for (int i = 0; i < limite; i++) {
                Cliente c = clientes.get(i);
                table.addCell(new Phrase(c.getNombre(), normalFont));
                table.addCell(new Phrase(c.getCorreo(), normalFont));
                table.addCell(new Phrase(c.getFechaRegistro().format(fmt), normalFont));
            }

            doc.add(table);
            doc.add(new Paragraph("\nTotal de clientes registrados: " + clientes.size(), normalFont));

            System.out.println("✅ PDF generado correctamente: Top3_ClientesAntiguos.pdf");

        } catch (FileNotFoundException | DocumentException e) {
            System.err.println("❌ Error al generar el PDF de clientes antiguos: " + e.getMessage());
        } finally {
            doc.close();
        }
    }

    static void generarReporte(List<Cliente> clientes, List<Instructor> instructores, String reporte_Nagomipdf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
