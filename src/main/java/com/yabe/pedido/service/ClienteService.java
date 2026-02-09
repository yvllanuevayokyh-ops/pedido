package com.yabe.pedido.service;

import com.yabe.pedido.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    Page<Cliente> findAll(Pageable pageable);
    Page<Cliente> search(String texto, Pageable pageable);
    Cliente findById(Long id);
    Cliente create(Cliente producto);
    Cliente update(Long id, Cliente cliente);
    void deleteById(Long id);
}

