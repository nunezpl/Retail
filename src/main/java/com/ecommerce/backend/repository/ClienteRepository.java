package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCorreoAndContrasena(String correo, String contrasena);
}
