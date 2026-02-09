package com.yabe.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "razon_social", length = 100, nullable = false)
    private String razonSocial;
    
    @Column(name = "documento", length = 15)
    private String documento;
    
    @Column(name = "direccion", length = 70)
    private String direccion;
    
    @Column(name = "telefono", length = 15)
    private String telefono;
}
