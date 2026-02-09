package com.yabe.pedido.service.impl;

import com.yabe.pedido.entity.Cliente;
import com.yabe.pedido.entity.Producto;
import com.yabe.pedido.repository.ClienteRepository;
import com.yabe.pedido.repository.ProductoRepository;
import com.yabe.pedido.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicesImlp implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return repository.findAll(pageable);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable);
        }
        return repository.findByDocumentoContainingIgnoreCase(texto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Cliente create(Cliente producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    @Override
    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Cliente updated = findById(id);
        updated.setDireccion(cliente.getDireccion());
        updated.setDocumento(cliente.getDocumento());
        updated.setTelefono(cliente.getTelefono());
        updated.setRazonSocial(cliente.getRazonSocial());
        return repository.save(updated);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}