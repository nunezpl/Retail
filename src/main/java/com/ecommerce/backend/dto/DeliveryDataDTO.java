package com.ecommerce.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DeliveryDataDTO {
    private UUID id;
    private String cedula;
    private String direccion;
    private String fechaCreacion;
    private String fechaEstimada;
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(String fechaEstimada) {
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
