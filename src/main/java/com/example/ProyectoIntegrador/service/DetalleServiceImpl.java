package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Detalle;
import com.example.ProyectoIntegrador.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleServiceImpl implements DetalleService{

    @Autowired
    private DetalleRepository repository;
    @Override
    public void saveDetalle(Detalle detalle) {
        repository.save(detalle);
    }

    @Override
    public List<Detalle> BuscarSegunFactura(Integer id) {
        return repository.findAllByFacturaId(id);
    }
}
