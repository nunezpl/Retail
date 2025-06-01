package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.DetalleCompra;
import com.ecommerce.backend.model.DetalleCompraId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, DetalleCompraId> {
}
