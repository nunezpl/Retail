package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Empleado;

import java.util.List;
import java.util.UUID;

public interface EmpleadoService {
    List<Empleado> listarEmpleados();
    Empleado obtenerEmpleadoPorId(UUID id);
    Empleado guardarEmpleado(Empleado empleado);
    Empleado actualizarEmpleado(UUID id, Empleado empleado);
    void eliminarEmpleado(UUID id);
}
