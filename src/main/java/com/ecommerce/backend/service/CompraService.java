package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Compra;

import java.util.List;
import java.util.UUID;

public interface CompraService {
    List<Compra> listarCompras();
    Compra obtenerCompraPorId(UUID id);
    Compra registrarCompra(Compra compra);
}
