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
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String pass;
    private String rol;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

}
