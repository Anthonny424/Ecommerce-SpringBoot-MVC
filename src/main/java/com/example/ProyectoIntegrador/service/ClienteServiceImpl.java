package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Cliente;
import com.example.ProyectoIntegrador.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public void saveCliente(Cliente cliente) {
         clienteRepository.save(cliente);
    }

    @Override
    public Integer BuscarClientePorDatos(String nombre, String apellido, String direccion, Integer telefono, Integer edad) {
        return clienteRepository.BuscarUsuarioRegistrado(nombre, apellido, direccion, telefono, edad);
    }

}
