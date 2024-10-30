package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Producto;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT p FROM Producto p WHERE p.estado = 'active'")
    List<Producto>ListarTodoPorEstado();

    @Query(value = "SELECT p FROM Producto p WHERE p.estado = 'inactivo'")
    List<Producto>ListarTodoPorEstadoInactivo();

    @Query(value = "SELECT p FROM Producto p WHERE p.categoria = :categoria AND p.estado = 'active'")
    List<Producto>ListarTodoPorCategoria(@Param("categoria")String categoria);

    @Query(value = "SELECT sum(stock) as total, categoria as categoria FROM productos GROUP BY categoria", nativeQuery = true)
    List<Tuple> Grafico2();

    @Query(value = "SELECT sum(stock) as stock, r.nombre as nombre FROM productos as p INNER JOIN ropas as r ON p.id=r.productos_id GROUP BY r.nombre ORDER BY stock ASC LIMIT 10",nativeQuery = true)
    List<Tuple>StockGeneral();

    @Transactional
    @Modifying
    @Query(value = "UPDATE productos SET stock = :stock WHERE productos.id = :id" , nativeQuery = true)
    public void UpdateStock(@Param("stock") Integer stock, @Param("id") Integer id);

    List<Producto>findAllByRopaNombreContainingAndEstado(String nombre, String estado);

    @Query(value = "SELECT p FROM Producto p WHERE p.genero = :genero AND p.estado = 'active'")
    List<Producto>ListarTodoGeneroActivo(@Param("genero")String genero);
}
