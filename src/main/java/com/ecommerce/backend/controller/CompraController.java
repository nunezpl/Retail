package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Compra;
import com.ecommerce.backend.service.CompraService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public List<Compra> listar() {
        return compraService.listarCompras();
    }

    @GetMapping("/{id}")
    public Compra obtener(@PathVariable UUID id) {
        return compraService.obtenerCompraPorId(id);
    }

    @PostMapping
    public Compra registrar(@RequestBody Compra compra) {
        return compraService.registrarCompra(compra);
    }
}
