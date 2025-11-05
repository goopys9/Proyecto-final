package com.mycompany.luciaproyecto;

import java.util.List;
import java.util.Random;

public class NotificationService {
    private static final String[] MENSAJES = {
        "¡Sigue así! Un paso a la vez, vas avanzando.",
        "Hoy es un buen día para entrenar y cuidarte.",
        "Pequeños esfuerzos, grandes resultados. ¡Ánimo!",
        "La constancia vence al talento. ¡No te detengas!",
        "Tu bienestar es prioridad. Cuida tu cuerpo y mente."
    };

    public static void enviarMotivacionales(List<Cliente> clientes) {
        Random r = new Random();
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay clientes registrados para enviar mensajes.");
            return;
        }
        System.out.println("Enviando mensajes motivacionales...");
        for (Cliente c : clientes) {
            String msg = MENSAJES[r.nextInt(MENSAJES.length)];
            System.out.println("-> Para " + c.getNombre() + ": " + msg);
        }
    }
}