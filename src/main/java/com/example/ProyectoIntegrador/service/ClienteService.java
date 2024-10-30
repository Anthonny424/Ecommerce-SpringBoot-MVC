package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Cliente;
import com.example.ProyectoIntegrador.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ClienteService {
    public void saveCliente(Cliente cliente);
    public Integer BuscarClientePorDatos(String nombre, String apellido, String direccion, Integer telefono, Integer edad);


}
