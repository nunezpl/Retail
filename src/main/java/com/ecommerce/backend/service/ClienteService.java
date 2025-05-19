package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente obtenerClientePorCedula(Integer cedula);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Integer cedula, Cliente cliente);
    void eliminarCliente(Integer cedula);
}
