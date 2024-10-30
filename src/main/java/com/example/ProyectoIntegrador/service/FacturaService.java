package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Factura;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Map;

public interface FacturaService {
    public void saveFactura(Factura factura);

    public List<Factura> buscarFacturaSegunCliente(Integer id);

    public List<Tuple> GraficoUno(Integer year);

    public List<Tuple>Grafico3(Integer year);
    public List<Tuple>Grafico4(Integer year);
    public Double TotalGanancia();
    public Integer TotalClientes();
    public Integer TotalFacturas();
    public Integer TotalStock();
   public Factura buscarFactura(int id);
   public void UpdateEstado(String estado, Integer id);
   public List<Factura> ListasTodasFacturasPendientes();
   public List<Tuple>Grafico6();
}
