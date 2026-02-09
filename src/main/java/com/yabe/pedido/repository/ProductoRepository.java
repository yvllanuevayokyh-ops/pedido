package com.yabe.pedido.repository;

import com.yabe.pedido.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository <Producto,Long> {
    Page<Producto> findProductoByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
