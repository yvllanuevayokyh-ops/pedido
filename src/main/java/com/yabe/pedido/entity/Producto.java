package com.yabe.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Cambiado de long a Long
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "CODIGO")
    private String codigo;
    
    @Column(name = "precio_unitario", precision = 8, scale = 2, nullable = false)
    private BigDecimal precioUnitario;  // Cambiado de preciounitario a precioUnitario (camelCase)
}