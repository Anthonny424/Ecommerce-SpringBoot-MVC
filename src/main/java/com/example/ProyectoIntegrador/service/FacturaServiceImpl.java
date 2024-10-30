package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Factura;
import com.example.ProyectoIntegrador.repository.FacturaRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository repository;
    @Override
    public void saveFactura(Factura factura) {
        repository.save(factura);
    }

    @Override
    public List<Factura> buscarFacturaSegunCliente(Integer id) {
        return repository.findAllByClienteId(id);
    }

    @Override
    public List<Tuple> GraficoUno(Integer year) {
        return repository.GraficoDeGananciaPorMes(year);
    }

    @Override
    public List<Tuple> Grafico3(Integer year) {
        return repository.Grafico3(year);
    }

    @Override
    public List<Tuple> Grafico4(Integer year) {
        return repository.Grafico4(year);
    }

    @Override
    public Double TotalGanancia() {
        return repository.TotalGanancia();
    }

    @Override
    public Integer TotalClientes() {
        return repository.TotalClientes();
    }

    @Override
    public Integer TotalFacturas() {
        return repository.TotalFacturas();
    }

    @Override
    public Integer TotalStock() {
        return repository.TotalStock();
    }

    @Override
    public Factura buscarFactura(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void UpdateEstado(String estado, Integer id) {
        repository.UpdateEstado(estado, id);
    }

    @Override
    public List<Factura> ListasTodasFacturasPendientes() {
        return repository.ListarTodasFacturaPendientes();
    }

    @Override
    public List<Tuple> Grafico6() {
        return repository.Grafico6();
    }

}
