package com.yabe.pedido.service.impl;

import com.yabe.pedido.entity.Cliente;
import com.yabe.pedido.repository.ClienteRepository;
import com.yabe.pedido.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> search(String texto, Pageable pageable) {
        return repository.findClienteByRazonSocialContainingIgnoreCase(texto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findByID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Cliente create(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteExistente = findByID(id);
        
        clienteExistente.setRazonSocial(cliente.getRazonSocial());
        clienteExistente.setDocumento(cliente.getDocumento());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setTelefono(cliente.getTelefono());
        
        return repository.save(clienteExistente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Cliente cliente = findByID(id);
        repository.deleteById(cliente.getId());
    }
}
