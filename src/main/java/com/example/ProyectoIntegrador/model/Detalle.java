package com.example.ProyectoIntegrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "detalles")
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private double total;
    private int idproducto;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "facturas_id")
    private Factura factura;

}
