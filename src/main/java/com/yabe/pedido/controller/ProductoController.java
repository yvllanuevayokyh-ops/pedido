package com.yabe.pedido.controller;

import com.yabe.pedido.entity.Producto;
import com.yabe.pedido.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET: Obtener todos los productos con paginación
    @GetMapping
    public ResponseEntity<Page<Producto>> getAllProductos(
            @RequestParam(defaultValue = "0") int page) {
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Producto> productos = productoService.findAll(pageable);
        
        return ResponseEntity.ok(productos);
    }

    // GET: Buscar productos por nombre
    @GetMapping("/search")
    public ResponseEntity<Page<Producto>> searchProductos(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int page) {
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Producto> productos = productoService.search(texto, pageable);
        
        return ResponseEntity.ok(productos);
    }

    // GET: Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.findByID(id);
        return ResponseEntity.ok(producto);
    }

    // POST: Crear nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.create(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // PUT: Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {
        
        Producto productoActualizado = productoService.update(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // DELETE: Eliminar producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
