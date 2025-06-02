package com.ecommerce.backend.model;

public class CarritoItem {

    private Producto producto;
    private int cantidad;
    private double subtotal;

    // Constructor vacío
    public CarritoItem() {
    }

    // Constructor con parámetros (opcional)
    public CarritoItem(Producto producto, int cantidad, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getter y Setter para producto
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Getter y Setter para cantidad
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter para subtotal
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
