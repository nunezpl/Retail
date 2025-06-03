package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Compra;
import com.ecommerce.backend.service.CompraService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
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

    public void datosCliente (Model model, HttpSession session){
        Object cedula = session.getAttribute("cedula");
        System.out.println("Cedula en sesión al entrar al catalogo: " + cedula);
        String urlCliente = "http://10.43.96.39:5000/api/Clientes/cedula/" + cedula;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> responseCliente = restTemplate.getForEntity(urlCliente, Map.class);
        if (responseCliente.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> cliente = responseCliente.getBody();
            String nombre = (String) cliente.get("nombre");
            model.addAttribute("nombre", nombre);
        } else {
            model.addAttribute("nombre", "Usuario");
        }
    }
}
