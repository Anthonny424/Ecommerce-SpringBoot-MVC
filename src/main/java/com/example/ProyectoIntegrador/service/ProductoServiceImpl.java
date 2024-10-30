package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Producto;
import com.example.ProyectoIntegrador.repository.ProductoRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> ListarProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> ObtenerProducto(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> ListarSegunCategoria(String categoria) {
        return productoRepository.ListarTodoPorCategoria(categoria);
    }

    @Override
    public List<Producto> ListarSegunGenero(String genero) {
        return productoRepository.ListarTodoGeneroActivo(genero);
    }

    @Override
    public List<Tuple> Grafico2() {
        return productoRepository.Grafico2();
    }

    @Override
    public List<Tuple> StockGeneral() {
        return productoRepository.StockGeneral();
    }

    @Override
    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void updateStock(int stock, int id) {
        productoRepository.UpdateStock(stock, id);
    }

    @Override
    public List<Producto> BuscarPorNombre(String text, String estado) {
        return productoRepository.findAllByRopaNombreContainingAndEstado(text, estado);
    }

    @Override
    public List<Producto> ListarProductoActivo() {
        return productoRepository.ListarTodoPorEstado();
    }

    @Override
    public List<Producto> ListarProductoInactivo() {
        return productoRepository.ListarTodoPorEstadoInactivo();
    }
}
