package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Detalle;

import java.util.List;

public interface DetalleService {

    public void saveDetalle(Detalle detalle);
    public List<Detalle> BuscarSegunFactura(Integer id);
}
