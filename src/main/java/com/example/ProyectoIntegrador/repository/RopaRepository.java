package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Ropa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RopaRepository extends JpaRepository<Ropa, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE ropas INNER JOIN productos as p ON productos_id=p.id SET p.estado = 'inactivo' WHERE p.id = :id" , nativeQuery = true)
    public void DeleteRopa(@Param("id") Integer id);
}
