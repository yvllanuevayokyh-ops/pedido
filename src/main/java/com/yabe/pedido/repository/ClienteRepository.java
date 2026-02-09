package com.yabe.pedido.repository;

import com.yabe.pedido.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByDocumentoContainingIgnoreCase(String docuento, Pageable pageable);
}