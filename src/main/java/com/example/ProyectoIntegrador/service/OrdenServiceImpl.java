package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Orden;
import com.example.ProyectoIntegrador.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    OrdenRepository repository;
    @Override
    public void saveOrden(Orden orden){
        repository.save(orden);
    }
}
