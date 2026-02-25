package com.medspec.medspec_api.service;

import com.medspec.medspec_api.entity.Clinica;
import com.medspec.medspec_api.repository.ClinicaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;

    public ClinicaService(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    public Clinica crear(Clinica clinica) {
        clinica.setId(null);
        return clinicaRepository.save(clinica);
    }

    public List<Clinica> obtenerTodas() {
        return clinicaRepository.findAll();
    }

    public Clinica obtenerPorId(Long id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica no encontrada"));
    }

    public List<Clinica> buscar(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return clinicaRepository.findAll();
        }
        return clinicaRepository.findByNombreContainingIgnoreCase(nombre.trim());
    }

    public Clinica actualizar(Long id, Clinica clinicaActualizada) {
        Clinica clinica = obtenerPorId(id);

        clinica.setNombre(clinicaActualizada.getNombre());
        clinica.setDireccion(clinicaActualizada.getDireccion());
        clinica.setTelefono(clinicaActualizada.getTelefono());

        return clinicaRepository.save(clinica);
    }

    public void eliminar(Long id) {
        Clinica clinica = obtenerPorId(id);
        clinicaRepository.delete(clinica);
    }
}