package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpleadoRepository extends JpaRepository<Empleado, UUID> {
}
