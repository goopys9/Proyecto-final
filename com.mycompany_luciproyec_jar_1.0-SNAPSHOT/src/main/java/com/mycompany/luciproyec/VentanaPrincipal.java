package com.mycompany.luciproyec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Instructor> instructores = new ArrayList<>();

    private JTextField txtId, txtNombre, txtCorreo, txtPeso, txtAltura, txtEdad;
    private JComboBox<String> cbTarjeta;
    private JTextArea txtSalida;

    public VentanaPrincipal() {
        setTitle("🏋‍♀ Nagomi Pilates");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior (Título)
        JLabel titulo = new JLabel("NAGOMI PILATES - SISTEMA DE REGISTRO", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel izquierdo: formulario + botones (usar BoxLayout vertical)
        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(new BoxLayout(panelIzq, BoxLayout.Y_AXIS));
        panelIzq.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelIzq, BorderLayout.WEST);

        // Formulario
        JPanel form = new JPanel(new GridLayout(7, 2, 6, 6));
        form.setMaximumSize(new Dimension(360, 220));
        form.add(new JLabel("ID:"));
        txtId = new JTextField(); form.add(txtId);

        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(); form.add(txtNombre);

        form.add(new JLabel("Correo:"));
        txtCorreo = new JTextField(); form.add(txtCorreo);

        form.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField(); form.add(txtPeso);

        form.add(new JLabel("Altura (m):"));
        txtAltura = new JTextField(); form.add(txtAltura);

        form.add(new JLabel("Edad:"));
        txtEdad = new JTextField(); form.add(txtEdad);

        form.add(new JLabel("Tarjeta:"));
        cbTarjeta = new JComboBox<>(new String[]{"Básica", "Premium"});
        form.add(cbTarjeta);

        panelIzq.add(form);
        panelIzq.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botones principales
        JPanel panelBtns = new JPanel(new GridLayout(3, 1, 6, 6));
        panelBtns.setMaximumSize(new Dimension(360, 140));

        JButton btnRegistrarCliente = new JButton("Registrar Cliente");
        JButton btnRegistrarInstructor = new JButton("Registrar Instructor");
        JButton btnMostrarClientes = new JButton("Mostrar Clientes");

        panelBtns.add(btnRegistrarCliente);
        panelBtns.add(btnRegistrarInstructor);
        panelBtns.add(btnMostrarClientes);

        panelIzq.add(panelBtns);

        // Panel derecho: área de salida (texto)
        txtSalida = new JTextArea();
        txtSalida.setEditable(false);
        txtSalida.setLineWrap(true);
        txtSalida.setWrapStyleWord(true);
        txtSalida.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(txtSalida);
        scroll.setPreferredSize(new Dimension(420, 380));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior: botones PDF
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        JButton btnPDFClientes = new JButton("Generar PDF Clientes");
        JButton btnPDFTopInstr = new JButton("Top 3 Instructores (PDF)");
        JButton btnPDFTopAntiguos = new JButton("Top 3 Antiguos (PDF)");
        JButton btnCerrar = new JButton("Cerrar Ventana");

        panelInferior.add(btnPDFClientes);
        panelInferior.add(btnPDFTopInstr);
        panelInferior.add(btnPDFTopAntiguos);
        panelInferior.add(btnCerrar);

        add(panelInferior, BorderLayout.SOUTH);

        // Acciones
        btnRegistrarCliente.addActionListener(e -> registrarCliente());
        btnRegistrarInstructor.addActionListener(e -> registrarInstructor());
        btnMostrarClientes.addActionListener(e -> mostrarClientes());

        btnPDFClientes.addActionListener(e -> {
            PDFGenerator.generarClientesPDF(clientes);
            txtSalida.append("✅ Intento de generar PDF de clientes (revisa consola o carpeta del proyecto).\n");
        });
        btnPDFTopInstr.addActionListener(e -> {
            PDFGenerator.generarTop3Instructores(instructores);
            txtSalida.append("✅ Intento de generar PDF Top 3 Instructores.\n");
        });
        btnPDFTopAntiguos.addActionListener(e -> {
            PDFGenerator.generarTop3Antiguos(clientes);
            txtSalida.append("✅ Intento de generar PDF Top 3 Clientes Antiguos.\n");
        });

        btnCerrar.addActionListener(e -> dispose());

        // Mejora visual
        getRootPane().setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    private void registrarCliente() {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (id.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID y Nombre son obligatorios.");
                return;
            }

            double peso = 0;
            double altura = 0;
            int edad = 0;
            try {
                if (!txtPeso.getText().trim().isEmpty()) peso = Double.parseDouble(txtPeso.getText().trim());
                if (!txtAltura.getText().trim().isEmpty()) altura = Double.parseDouble(txtAltura.getText().trim());
                if (!txtEdad.getText().trim().isEmpty()) edad = Integer.parseInt(txtEdad.getText().trim());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Verifica los valores numéricos (peso, altura, edad).");
                return;
            }

            Cliente nuevo = new Cliente(id, nombre, correo, peso, altura, edad) {
                @Override
                public void mostrarRol() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            };

            Object seleccionado = cbTarjeta.getSelectedItem();
            String tipo = seleccionado != null ? seleccionado.toString() : "Básica";
            if ("Premium".equalsIgnoreCase(tipo)) {
                nuevo.comprarTarjeta(new PremiumCard("Premium"));
            } else {
                nuevo.comprarTarjeta(new BasicCard("Básica"));
            }

            clientes.add(nuevo);
            txtSalida.append("✅ Cliente registrado: " + nombre + " (ID: " + id + ")\n");

            // Limpiar campos básicos
            txtId.setText("");
            txtNombre.setText("");
            txtCorreo.setText("");
            txtPeso.setText("");
            txtAltura.setText("");
            txtEdad.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + ex.getMessage());
        }
    }

    private void registrarInstructor() {
        try {
            String id = JOptionPane.showInputDialog(this, "ID del instructor:");
            if (id == null || id.trim().isEmpty()) return;
            String nombre = JOptionPane.showInputDialog(this, "Nombre del instructor:");
            if (nombre == null) return;
            String correo = JOptionPane.showInputDialog(this, "Correo del instructor:");

            Instructor ins = new Instructor(id.trim(), nombre.trim(), correo != null ? correo.trim() : "");
            instructores.add(ins);
            txtSalida.append("✅ Instructor registrado: " + nombre + " (ID: " + id + ")\n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar instructor: " + ex.getMessage());
        }
    }

    private void mostrarClientes() {
        if (clientes.isEmpty()) {
            txtSalida.append("⚠ No hay clientes registrados.\n");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- CLIENTES REGISTRADOS ---\n");
        for (Cliente c : clientes) {
            sb.append("ID: ").append(c.getId())
              .append(" | Nombre: ").append(c.getNombre())
              .append(" | Tarjeta: ").append(c.getTarjeta() != null ? c.getTarjeta().getNivel() : "Ninguna")
              .append(" | Fecha: ").append(c.getFechaRegistro())
              .append("\n");
        }
        txtSalida.append(sb.toString());
    }

    // Método main opcional para pruebas directas
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
}