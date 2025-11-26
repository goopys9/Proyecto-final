package com.mycompany.sairaproyec;

import java.util.List;

public interface Persistible {
    void guardarClientes(List<Cliente> clientes, String ruta) throws Exception;
    List<Cliente> cargarClientes(String ruta) throws Exception;

    void guardarInstructores(List<Instructor> instructores, String ruta) throws Exception;
    List<Instructor> cargarInstructores(String ruta) throws Exception;
}