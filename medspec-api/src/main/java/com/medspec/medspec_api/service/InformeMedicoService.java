package com.medspec.medspec_api.service;

import com.medspec.medspec_api.entity.Cita;
import com.medspec.medspec_api.entity.InformeMedico;
import com.medspec.medspec_api.repository.CitaRepository;
import com.medspec.medspec_api.repository.InformeMedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class InformeMedicoService {

    private final InformeMedicoRepository informeMedicoRepository;
    private final CitaRepository citaRepository;

    public InformeMedicoService(InformeMedicoRepository informeMedicoRepository, CitaRepository citaRepository) {
        this.informeMedicoRepository = informeMedicoRepository;
        this.citaRepository = citaRepository;
    }

    public InformeMedico crear(Long citaId, InformeMedico informe) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        if (informeMedicoRepository.existsByCitaId(citaId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un informe para esta cita");
        }

        informe.setId(null);
        informe.setCita(cita);
        informe.setPaciente(cita.getPaciente());
        informe.setMedico(cita.getMedico());

        return informeMedicoRepository.save(informe);
    }

    public InformeMedico obtenerPorId(Long id) {
        return informeMedicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Informe médico no encontrado"));
    }

    public List<InformeMedico> obtenerPorPaciente(Long pacienteId) {
        return informeMedicoRepository.findByPacienteId(pacienteId);
    }

    public List<InformeMedico> obtenerPorMedico(Long medicoId) {
        return informeMedicoRepository.findByMedicoId(medicoId);
    }

    public InformeMedico actualizar(Long id, InformeMedico datos) {
        InformeMedico existente = obtenerPorId(id);

        // No permitimos cambiar relaciones: cita/paciente/médico se mantienen
        existente.setTitulo(datos.getTitulo());
        existente.setResumen(datos.getResumen());
        existente.setDiagnostico(datos.getDiagnostico());
        existente.setTratamiento(datos.getTratamiento());
        existente.setEmitidoEn(datos.getEmitidoEn());
        existente.setPdfUrl(datos.getPdfUrl());

        return informeMedicoRepository.save(existente);
    }

    public void eliminar(Long id) {
        InformeMedico informe = obtenerPorId(id);
        informeMedicoRepository.delete(informe);
    }
}