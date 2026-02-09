package com.yabe.pedido.repository;

import com.yabe.pedido.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findClienteByRazonSocialContainingIgnoreCase(String razonSocial, Pageable pageable);
}
