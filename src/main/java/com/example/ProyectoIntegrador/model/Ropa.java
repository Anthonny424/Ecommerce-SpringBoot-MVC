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
@Table(name = "ropas")
public class Ropa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private String marca;
    private double precio;
    private String foto;
    private String talla;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productos_id")
    private Producto producto;

}
