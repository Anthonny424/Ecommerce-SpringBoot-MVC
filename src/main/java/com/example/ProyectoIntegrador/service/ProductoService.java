package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Producto;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductoService {
    public List<Producto> ListarProducto();
    public Optional<Producto> ObtenerProducto(int id);
    public List<Producto> ListarSegunCategoria(String categoria);
    public List<Producto> ListarSegunGenero(String genero);
    public List<Tuple> Grafico2();
    public List<Tuple> StockGeneral();
    public void saveProducto(Producto producto);
    public void updateStock(int stock, int id);
    public List<Producto> BuscarPorNombre(String text, String estado);
    public List<Producto> ListarProductoActivo();
    public List<Producto> ListarProductoInactivo();
}
