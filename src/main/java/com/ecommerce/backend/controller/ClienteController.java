package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Cliente;
import com.ecommerce.backend.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{cedula}")
    public Cliente obtenerCliente(@PathVariable Integer cedula) {
        return clienteService.obtenerClientePorCedula(cedula);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @PutMapping("/{cedula}")
    public Cliente actualizarCliente(@PathVariable Integer cedula, @RequestBody Cliente cliente) {
        return clienteService.actualizarCliente(cedula, cliente);
    }

    @DeleteMapping("/{cedula}")
    public void eliminarCliente(@PathVariable Integer cedula) {
        clienteService.eliminarCliente(cedula);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String contrasena) {
        Optional<Cliente> cliente = clienteService.listarClientes().stream()
                .filter(c -> c.getCorreo().equals(correo) && c.getContrasena().equals(contrasena))
                .findFirst();

        if (cliente.isPresent()) {
            // Aquí podrías redirigir o mostrar una página de éxito
            return ResponseEntity.ok("Bienvenido, " + cliente.get().getNombre());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña inválidos");
        }
    }

}
