package com.example.ProyectoIntegrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int stock;
    private String genero;
    private String categoria;
    private String estado;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Ropa ropa;
}
