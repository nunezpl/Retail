package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Producto;
import com.ecommerce.backend.service.ProductoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listarProductos();
    }

    @GetMapping("/find/{id}")
    public Producto buscarPorId(@PathVariable UUID id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping("/add")
    public Producto crear(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/update/{id}")
    public Producto actualizar(@PathVariable UUID id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }
}
