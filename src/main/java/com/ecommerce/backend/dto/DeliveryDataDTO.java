package com.ecommerce.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DeliveryDataDTO {
    private UUID id;
    private String cedula;
    private String direccion;
    private LocalDate fechaCreacion;
    private LocalDate fechaEstimada;
    private String estado;
    private List<ProductoDeliveryDTO> productos;

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProductoDeliveryDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDeliveryDTO> productos) {
        this.productos = productos;
    }
}
