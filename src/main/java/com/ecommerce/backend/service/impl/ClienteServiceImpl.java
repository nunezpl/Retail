package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.model.Cliente;
import com.ecommerce.backend.repository.ClienteRepository;
import com.ecommerce.backend.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClientePorCedula(Integer cedula) {
        return clienteRepository.findById(cedula)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Integer cedula, Cliente cliente) {
        Cliente existente = obtenerClientePorCedula(cedula);
        existente.setNombre(cliente.getNombre());
        existente.setApellido(cliente.getApellido());
        existente.setCorreo(cliente.getCorreo());
        existente.setTelefono(cliente.getTelefono());
        existente.setContrasena(cliente.getContrasena());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminarCliente(Integer cedula) {
        clienteRepository.deleteById(cedula);
    }
    @Override
    public Optional<Cliente> loginCliente(String correo, String contrasena) {
    return clienteRepository.findByCorreoAndContrasena(correo, contrasena);
}

    @Override
    public Cliente registrarCliente(String nombre, String correo, String contrasena) {
        if (clienteRepository.findByCorreoAndContrasena(correo, contrasena).isPresent()) {
            throw new RuntimeException("Correo ya registrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setCedula((int)(Math.random() * 900000000 + 100000000));
        cliente.setNombre(nombre);
        cliente.setApellido("");
        cliente.setCorreo(correo);
        cliente.setTelefono(null);
        cliente.setContrasena(contrasena);

        return clienteRepository.save(cliente);
    }
}
