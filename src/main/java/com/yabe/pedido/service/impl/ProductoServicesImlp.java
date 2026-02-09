package com.yabe.pedido.service.impl;

import com.yabe.pedido.entity.Producto;
import com.yabe.pedido.repository.ProductoRepository;
import com.yabe.pedido.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicesImlp implements ProductoService {

    @Autowired
    private ProductoRepository repository;


    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCase(texto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Producto create(Producto producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        Producto updated = findById(id);
        updated.setNombre(producto.getNombre());
        updated.setCodigo(producto.getCodigo());
        updated.setPrecioUnitario(producto.getPrecioUnitario());
        return repository.save(updated);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
