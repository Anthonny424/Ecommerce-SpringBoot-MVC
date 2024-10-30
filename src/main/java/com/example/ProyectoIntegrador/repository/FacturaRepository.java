package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Factura;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    List<Factura> findAllByClienteId(Integer id);

    @Query(value = "SELECT sum(total) as total, MONTHNAME(fecha) as mes  FROM facturas WHERE YEAR(fecha) = :year GROUP BY fecha", nativeQuery = true)
    List<Tuple> GraficoDeGananciaPorMes(@Param("year") Integer year);

    @Query(value = "SELECT MONTHNAME(fecha) as mes, COUNT(*) as total FROM facturas WHERE YEAR(fecha) = :year GROUP BY MONTHNAME(fecha) ORDER BY MONTH(fecha)", nativeQuery = true)
    List<Tuple> Grafico3(@Param("year")Integer year);

    @Query(value = "SELECT MONTHNAME(fecha) as mes, sum(total) as total FROM facturas WHERE YEAR(fecha) = :year GROUP BY MONTHNAME(fecha) ORDER BY MONTH(fecha)", nativeQuery = true)
    List<Tuple> Grafico4(@Param("year") Integer year);

    @Query(value = "SELECT sum(total) as total FROM facturas", nativeQuery = true)
     Double TotalGanancia();

    @Query(value = "SELECT COUNT(id) as cantidad FROM clientes", nativeQuery = true)
    Integer TotalClientes();
    @Query(value = "SELECT COUNT(id) as facturas FROM facturas", nativeQuery = true)
    Integer TotalFacturas();
    @Query(value = "SELECT SUM(stock) as cantidad FROM productos", nativeQuery = true)
    Integer TotalStock();

    @Transactional
    @Modifying
    @Query(value = "UPDATE facturas SET estado = :estado WHERE facturas.id = :id" , nativeQuery = true)
    public void UpdateEstado(@Param("estado") String estado, @Param("id") Integer id);

    @Query(value = "SELECT * FROM facturas WHERE estado = 'pendiente' AND fecha <= DATE_SUB(NOW(), INTERVAL 14 DAY);", nativeQuery = true)
    List<Factura>ListarTodasFacturaPendientes();

    @Query(value = "SELECT MONTHNAME(fecha) AS mes, AVG(total) as promedio FROM facturas WHERE YEAR(fecha) = 2023 GROUP BY MONTHNAME(fecha) ORDER BY MONTH(fecha)", nativeQuery = true)
    List<Tuple> Grafico6();
}
