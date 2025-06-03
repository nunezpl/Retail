package com.ecommerce.backend.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/delivery")
public class IntegracionDeliveryController {

    @PostMapping("/enviarPedido")
    public ResponseEntity<String> enviarPedido() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.43.103.229:8080/api/integracion_delivery/pedido";

        // Crear producto
        Map<String, Object> producto = new HashMap<>();
        producto.put("idProducto", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        producto.put("nombreProducto", "Mouse Gamer");

        // Crear pedido
        Map<String, Object> pedido = new HashMap<>();
        pedido.put("id", UUID.randomUUID().toString());
        pedido.put("cedula", "12345678");
        pedido.put("direccion", "Calle 123");
        pedido.put("fechaCreacion", "2025-06-02T21:00:00");
        pedido.put("fechaEstimada", "2025-06-05T21:00:00");
        pedido.put("estado", "pendiente");
        pedido.put("productos", Collections.singletonList(producto));

        // Crear headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(pedido, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar pedido: " + e.getMessage());
        }
    }
}

