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
public class ProductoServiceImpl implements ProductoService {
    
    private final ProductoRepository repository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> search(String texto, Pageable pageable) {
        return repository.findProductoByNombreContainingIgnoreCase(texto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findByID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Producto create(Producto producto) {
        return repository.save(producto);
    }

    @Override
    @Transactional
    public Producto update(Long id, Producto producto) {
        Producto productoExistente = findByID(id);
        
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setCodigo(producto.getCodigo());
        productoExistente.setPrecioUnitario(producto.getPrecioUnitario());  // CORREGIDO
        
        return repository.save(productoExistente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Producto producto = findByID(id);
        repository.deleteById(producto.getId());
    }
}