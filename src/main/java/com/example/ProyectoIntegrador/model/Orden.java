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
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String foto;
    private int cantidad;
    private double total;
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "ordenes_productos",
            joinColumns = @JoinColumn(name = "ordenes_id"),
            inverseJoinColumns = @JoinColumn(name = "productos_id"))
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;
}
