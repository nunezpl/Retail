package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.model.Empleado;
import com.ecommerce.backend.repository.EmpleadoRepository;
import com.ecommerce.backend.service.EmpleadoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado obtenerEmpleadoPorId(UUID id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado actualizarEmpleado(UUID id, Empleado empleado) {
        Empleado existente = obtenerEmpleadoPorId(id);
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setRol(empleado.getRol());
        return empleadoRepository.save(existente);
    }

    @Override
    public void eliminarEmpleado(UUID id) {
        empleadoRepository.deleteById(id);
    }
}
