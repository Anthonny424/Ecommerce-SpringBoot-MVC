package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT id FROM clientes WHERE nombre like :nombre AND apellido like :apellido AND direccion like :direccion AND telefono = :telefono\n" +
            " AND edad = :edad" , nativeQuery = true)
    public Integer BuscarUsuarioRegistrado(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("direccion") String direccion, @Param("telefono") Integer telefono, @Param("edad") Integer edad);



}
