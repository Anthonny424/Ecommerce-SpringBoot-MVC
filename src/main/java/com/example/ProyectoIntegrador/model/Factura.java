package com.example.ProyectoIntegrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha;
    private double total;
    private String estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;


}
