package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Detalle;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

    List<Detalle> findAllByFacturaId(Integer id);



}
