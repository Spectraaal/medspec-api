package com.medspec.medspec_api.repository;

import com.medspec.medspec_api.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);

    List<Paciente> findByApellidosContainingIgnoreCase(String apellidos);

    List<Paciente> findByNombreContainingIgnoreCaseAndApellidosContainingIgnoreCase(String nombre, String apellidos);
}