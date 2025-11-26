package com.mycompany.sairaproyec;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArchivoPersistencia implements Persistible {

    public ArchivoPersistencia() {
       
    }

    @Override
    public void guardarClientes(List<Cliente> clientes, String ruta) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Cliente c : clientes) {
                String tarjeta = c.getTarjeta() == null ? "null" : c.getTarjeta().getTipo();
                String instructor = c.getInstructor() == null ? "null" : c.getInstructor().getNombre();
                String rutina = c.getRutinaAsignada() == null ? "null" : c.getRutinaAsignada().getNombre();
                bw.write(escape(c.getNombre()) + "|" + escape(c.getCorreo()) + "|" + c.getFechaRegistro() + "|" + tarjeta + "|" + instructor + "|" + rutina);
                bw.newLine();
            }
        }
    }

    @Override
    public List<Cliente> cargarClientes(String ruta) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        File f = new File(ruta);
        if (!f.exists()) return clientes;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|", -1);
                if (parts.length < 6) continue;
                String nombre = unescape(parts[0]);
                String correo = unescape(parts[1]);
                LocalDate fecha = LocalDate.parse(parts[2]);
                Cliente c = new Cliente(nombre, correo, fecha); // usa constructor sobrecargado
                // tarjeta: solo tipo (reconstruyo mínima)
                String tarjetaTipo = parts[3];
                if (!"null".equals(tarjetaTipo)) {
                    switch (tarjetaTipo) {
                        case "Básica" -> c.setTarjeta(new BasicCard());
                        case "Media" -> c.setTarjeta(new MediumCard());
                        case "Premium" -> c.setTarjeta(new PremiumCard());
                    }
                }
                // instructor y rutina los seteará la app (relaciones entre objetos) — aquí dejamos nombre para que la app los reasigne después si hace sentido.
                clientes.add(c);
            }
        }
        return clientes;
    }

    @Override
    public void guardarInstructores(List<Instructor> instructores, String ruta) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Instructor i : instructores) {
                bw.write(escape(i.getNombre()));
                bw.newLine();
            }
        }
    }

    @Override
    public List<Instructor> cargarInstructores(String ruta) throws Exception {
        List<Instructor> instructores = new ArrayList<>();
        File f = new File(ruta);
        if (!f.exists()) return instructores;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = br.readLine()) != null) {
                instructores.add(new Instructor(unescape(line)));
            }
        }
        return instructores;
    }

    private String escape(String s) {
        return s.replace("|", "\\|");
    }

    private String unescape(String s) {
        return s.replace("\\|", "|");
    }
}