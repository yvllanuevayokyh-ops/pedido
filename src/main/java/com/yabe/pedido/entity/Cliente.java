package com.yabe.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social", length = 100,nullable = false)
    private String razonSocial;

    @Column(name = "documento", length = 15,nullable = true)
    private String documento;

    @Column(name = "direccion", length = 70,nullable = true)
    private String direccion;

    @Column(name = "telefono", length = 15,nullable = true)
    private String telefono;

}
