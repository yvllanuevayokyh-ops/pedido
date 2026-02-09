package com.yabe.pedido.controller;

import com.yabe.pedido.entity.Cliente;

import com.yabe.pedido.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET: Obtener todos los clientes con paginación
    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllClientes(
            @RequestParam(defaultValue = "0") int page) {
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Cliente> clientes = clienteService.findAll(pageable);
        
        return ResponseEntity.ok(clientes);
    }

    // GET: Buscar clientes por razón social
    @GetMapping("/search")
    public ResponseEntity<Page<Cliente>> searchClientes(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int page) {
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Cliente> clientes = clienteService.search(texto, pageable);
        
        return ResponseEntity.ok(clientes);
    }

    // GET: Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.findByID(id);
        return ResponseEntity.ok(cliente);
    }

    // POST: Crear nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // PUT: Actualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {
        
        Cliente clienteActualizado = clienteService.update(id, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    // DELETE: Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
