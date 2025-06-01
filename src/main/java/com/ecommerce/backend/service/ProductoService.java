package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Producto;

import java.util.List;
import java.util.UUID;

public interface ProductoService {
    List<Producto> listarProductos();
    Producto obtenerProductoPorId(UUID id);
    Producto guardarProducto(Producto producto);
    Producto actualizarProducto(UUID id, Producto producto);
    void eliminarProducto(UUID id);
}
