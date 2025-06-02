package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Cliente;
import com.ecommerce.backend.service.ClienteService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/create-account")
    public String mostrarRegistro() {
        return "create_account";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; 
    }


    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{cedula}")
    public Cliente obtenerCliente(@PathVariable Integer cedula) {
        return clienteService.obtenerClientePorCedula(cedula);
    }

   @PostMapping("/registro")
    public String registrarRedireccionado(
            @RequestParam String nombre,
            @RequestParam String cedula,
            @RequestParam String correo,
            @RequestParam String contrasena,
            RedirectAttributes redirectAttributes) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.43.96.39:5000/api/Clientes";

        Map<String, Object> body = new HashMap<>();
        body.put("nombre", nombre);
        body.put("apellido", ""); 
        body.put("cedula", Integer.parseInt(cedula));
        body.put("correo", correo);
        body.put("telefono", ""); 
        body.put("password", contrasena); 

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/api/cliente/login";
            } else {
                redirectAttributes.addFlashAttribute("error", "Registro inválido");
                return "redirect:/api/cliente/create-account";
            }
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getStatusCode());
            return "redirect:/api/cliente/create-account";
        }
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
        public String loginRedireccionadoDesdeFormulario(
        @RequestParam String correo,
        @RequestParam String contrasena,
        RedirectAttributes redirectAttributes,
        Model model) {

    
    RestTemplate restTemplate = new RestTemplate();

    String url = "http://10.43.96.39:5000/api/Login/Cliente";

    Map<String, String> body = new HashMap<>();
    body.put("username", correo);
    body.put("password", contrasena);

    try {
        ResponseEntity<Map> response = restTemplate.postForEntity(url, body, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("nombre", correo);
            return "principal2";
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
            return "redirect:/api/cliente/login";
        }

    } catch (HttpClientErrorException.Unauthorized e) {
        redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
        return "redirect:/api/cliente/login";
    } catch (HttpClientErrorException.Forbidden e) {
        redirectAttributes.addFlashAttribute("error", "Acceso denegado");
        return "redirect:/api/cliente/login";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error de conexión con el servidor de autenticación");
        return "redirect:/api/cliente/login";
    }
}
}
