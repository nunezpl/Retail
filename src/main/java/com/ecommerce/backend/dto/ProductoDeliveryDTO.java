package com.ecommerce.backend.dto;

import java.util.UUID;

public class ProductoDeliveryDTO {
    private UUID idProducto;
    private String nombreProducto;

    // Getters y Setters
    public UUID getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(UUID idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}