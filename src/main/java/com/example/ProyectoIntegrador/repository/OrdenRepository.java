package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
}
