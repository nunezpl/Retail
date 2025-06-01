package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Empleado;
import com.ecommerce.backend.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{id}")
    public Empleado obtener(@PathVariable UUID id) {
        return empleadoService.obtenerEmpleadoPorId(id);
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable UUID id, @RequestBody Empleado empleado) {
        return empleadoService.actualizarEmpleado(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        empleadoService.eliminarEmpleado(id);
    }
}
