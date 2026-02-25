package com.medspec.medspec_api.service;

import com.medspec.medspec_api.entity.Paciente;
import com.medspec.medspec_api.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente crear(Paciente paciente) {
        paciente.setId(null);
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente obtenerPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
    }

    public List<Paciente> buscar(String nombre, String apellidos) {

        boolean tieneNombre = nombre != null && !nombre.trim().isEmpty();
        boolean tieneApellidos = apellidos != null && !apellidos.trim().isEmpty();

        if (tieneNombre && tieneApellidos) {
            return pacienteRepository.findByNombreContainingIgnoreCaseAndApellidosContainingIgnoreCase(
                    nombre.trim(), apellidos.trim()
            );
        }

        if (tieneNombre) {
            return pacienteRepository.findByNombreContainingIgnoreCase(nombre.trim());
        }

        if (tieneApellidos) {
            return pacienteRepository.findByApellidosContainingIgnoreCase(apellidos.trim());
        }

        return pacienteRepository.findAll();
    }


    public Paciente actualizar(Long id, Paciente pacienteActualizado) {
        Paciente paciente = obtenerPorId(id);

        paciente.setNombre(pacienteActualizado.getNombre());
        paciente.setApellidos(pacienteActualizado.getApellidos());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setDocumentoId(pacienteActualizado.getDocumentoId());

        return pacienteRepository.save(paciente);
    }

    public void eliminar(Long id) {
        Paciente paciente = obtenerPorId(id);
        pacienteRepository.delete(paciente);
    }
}