package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.model.Compra;
import com.ecommerce.backend.repository.CompraRepository;
import com.ecommerce.backend.service.CompraService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Compra obtenerCompraPorId(UUID id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }

    @Override
    public Compra registrarCompra(Compra compra) {
        compra.setFecha(LocalDateTime.now());

        // TODO: Lógica de integración con Banco ABC (registrar pago)
        // Aquí se haría un POST al servicio REST del banco para procesar el pago
        // Ejemplo (comentado):
        // bankClient.realizarPago(pagoRequest);

        // TODO: Lógica de integración con Delivery ABC (crear envío)
        // Después de guardar la compra, se enviaría el payload al servicio de Delivery
        // deliveryClient.registrarEnvio(compra);

        return compraRepository.save(compra);
    }
}
