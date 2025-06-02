package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.CarritoItem;
import com.ecommerce.backend.model.Cliente;
import com.ecommerce.backend.model.Producto;
import com.ecommerce.backend.service.ClienteService;
import com.ecommerce.backend.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final ProductoService productoService;

    @Autowired
    public CarritoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();

        double total = carrito.stream().mapToDouble(CarritoItem::getSubtotal).sum();

        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        return "shopping_cart";
    }

    @PostMapping("/agregar")
public String agregarAlCarrito(@RequestParam("idProducto") UUID idProducto, HttpSession session) {
    Producto producto = productoService.buscarPorId(idProducto);
    if (producto == null) {
       return "redirect:/api/producto/catalogo"; // usa redirect si es un endpoint
    }

    List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
    if (carrito == null) {
        carrito = new ArrayList<>();
    }

    boolean encontrado = false;
    for (CarritoItem item : carrito) {
        if (item.getProducto().getId().equals(idProducto)) {
            item.setCantidad(item.getCantidad() + 1);
            item.setSubtotal(item.getCantidad() * item.getProducto().getPrecioVenta());
            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        CarritoItem nuevo = new CarritoItem();
        nuevo.setProducto(producto);
        nuevo.setCantidad(1);
        nuevo.setSubtotal(producto.getPrecioVenta());
        carrito.add(nuevo);
    }

    session.setAttribute("carrito", carrito);
    return "redirect:/carrito";  // o donde quieras mostrar el resumen
}

    
}
