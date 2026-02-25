package com.medspec.medspec_api.service;

import com.medspec.medspec_api.entity.Clinica;
import com.medspec.medspec_api.entity.Medico;
import com.medspec.medspec_api.repository.ClinicaRepository;
import com.medspec.medspec_api.repository.MedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final ClinicaRepository clinicaRepository;

    public MedicoService(MedicoRepository medicoRepository, ClinicaRepository clinicaRepository) {
        this.medicoRepository = medicoRepository;
        this.clinicaRepository = clinicaRepository;
    }

    public Medico crear(Medico medico, Long clinicaId) {
        Clinica clinica = clinicaRepository.findById(clinicaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica no encontrada"));

        medico.setId(null);
        medico.setClinica(clinica);
        return medicoRepository.save(medico);
    }

    public List<Medico> obtenerTodos() {
        return medicoRepository.findAll();
    }

    public Medico obtenerPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));
    }

    public List<Medico> buscar(Long clinicaId, String especialidad) {

        boolean tieneClinica = clinicaId != null;
        boolean tieneEspecialidad = especialidad != null && !especialidad.trim().isEmpty();

        if (tieneClinica && tieneEspecialidad) {
            return medicoRepository.findByClinicaIdAndEspecialidadContainingIgnoreCase(clinicaId, especialidad.trim());
        }

        if (tieneClinica) {
            return medicoRepository.findByClinicaId(clinicaId);
        }

        if (tieneEspecialidad) {
            return medicoRepository.findByEspecialidadContainingIgnoreCase(especialidad.trim());
        }

        return medicoRepository.findAll();
    }

    public Medico actualizar(Long id, Medico medicoActualizado, Long clinicaId) {
        Medico medico = obtenerPorId(id);

        Clinica clinica = clinicaRepository.findById(clinicaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica no encontrada"));

        medico.setNombre(medicoActualizado.getNombre());
        medico.setApellidos(medicoActualizado.getApellidos());
        medico.setNumeroColegiado(medicoActualizado.getNumeroColegiado());
        medico.setEspecialidad(medicoActualizado.getEspecialidad());
        medico.setTelefono(medicoActualizado.getTelefono());
        medico.setClinica(clinica);

        return medicoRepository.save(medico);
    }

    public void eliminar(Long id) {
        Medico medico = obtenerPorId(id);
        medicoRepository.delete(medico);
    }
}