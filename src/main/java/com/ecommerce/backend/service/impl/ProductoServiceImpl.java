package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.model.Producto;
import com.ecommerce.backend.repository.ProductoRepository;
import com.ecommerce.backend.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(UUID id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(UUID id, Producto producto) {
        Producto existente = obtenerProductoPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setPrecioCompra(producto.getPrecioCompra());
        existente.setPrecioVenta(producto.getPrecioVenta());
        existente.setExistencias(producto.getExistencias());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminarProducto(UUID id) {
        if (!productoRepository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
            }
            productoRepository.deleteById(id);
    }
    @Override
    public Producto buscarPorId(UUID id) {
        String url = "http://10.43.103.229:8080/producto/find/" + id;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Producto.class);
    }
}
