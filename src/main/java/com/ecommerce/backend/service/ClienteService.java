package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente obtenerClientePorCedula(Integer cedula);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Integer cedula, Cliente cliente);
    Optional<Cliente> loginCliente(String correo, String contrasena);
    void eliminarCliente(Integer cedula);
    Cliente registrarCliente(String nombre, String correo, String contrasena);
}
