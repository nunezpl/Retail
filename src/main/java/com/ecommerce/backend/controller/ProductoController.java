package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Producto;
import com.ecommerce.backend.service.ProductoService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    public String listarProductos(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.101.136.126:8081/producto/findAll";

        try {
            ResponseEntity<Producto[]> response = restTemplate.getForEntity(url, Producto[].class);
            List<Producto> productos = Arrays.asList(response.getBody());
            model.addAttribute("productos", productos);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudieron cargar los productos.");
        }
        return "principal";
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.101.136.126:8081/producto/findAll";

        try {
            ResponseEntity<Producto[]> response = restTemplate.getForEntity(url, Producto[].class);
            List<Producto> productos = Arrays.asList(response.getBody());
            model.addAttribute("productos", productos);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudieron cargar los productos.");
        }
        return "product_catalog"; 
    }

    @GetMapping("/find/{id}")
    public Producto buscarPorId(@PathVariable UUID id) {
        return productoService.obtenerProductoPorId(id);
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable UUID id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.101.136.126:8081/producto/find/" + id;

        try {
            ResponseEntity<Producto> response = restTemplate.getForEntity(url, Producto.class);
            Producto producto = response.getBody();
            model.addAttribute("producto", producto);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo cargar el producto con ID: " + id);
            return "redirect:/listar";
        }

        return "product_detail";
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
