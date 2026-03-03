package com.medspec.medspec_api.service;

import com.medspec.medspec_api.entity.*;
import com.medspec.medspec_api.repository.CitaRepository;
import com.medspec.medspec_api.repository.ClinicaRepository;
import com.medspec.medspec_api.repository.MedicoRepository;
import com.medspec.medspec_api.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final ClinicaRepository clinicaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public CitaService(
            CitaRepository citaRepository,
            ClinicaRepository clinicaRepository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository
    ) {
        this.citaRepository = citaRepository;
        this.clinicaRepository = clinicaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Cita crear(Long clinicaId, Long pacienteId, Long medicoId, String inicioStr, String finStr, String motivo) {
        if (clinicaId == null || pacienteId == null || medicoId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "clinicaId, pacienteId y medicoId son obligatorios");
        }

        LocalDateTime inicio = parseFechaHora(inicioStr, "inicio");
        LocalDateTime fin = parseFechaHora(finStr, "fin");

        Cita cita = new Cita();
        cita.setInicio(inicio);
        cita.setFin(fin);
        cita.setMotivo(motivo);

        return crear(clinicaId, pacienteId, medicoId, cita);
    }

    public Cita crear(Long clinicaId, Long pacienteId, Long medicoId, Cita cita) {
        Clinica clinica = clinicaRepository.findById(clinicaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica no encontrada"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));

        if (cita.getInicio() == null || cita.getFin() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "inicio y fin son obligatorios");
        }

        if (!cita.getInicio().isBefore(cita.getFin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha/hora de inicio debe ser anterior a fin");
        }

        boolean solapa = citaRepository.existeSolapamiento(medicoId, null, cita.getInicio(), cita.getFin());
        if (solapa) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El médico ya tiene una cita en ese rango horario");
        }

        cita.setId(null);
        cita.setClinica(clinica);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setEstado(EstadoCita.SOLICITADA);
        cita.setCreadaEn(LocalDateTime.now()); // si ya usas @CreationTimestamp, puedes quitar esta línea

        return citaRepository.save(cita);
    }

    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));
    }

    public List<Cita> obtenerPorPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    public List<Cita> obtenerPorMedico(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId);
    }

    public Cita cambiarEstado(Long id, EstadoCita estado) {
        Cita cita = obtenerPorId(id);
        cita.setEstado(estado);
        return citaRepository.save(cita);
    }

    public void eliminar(Long id) {
        Cita cita = obtenerPorId(id);
        citaRepository.delete(cita);
    }

    private LocalDateTime parseFechaHora(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, campo + " es obligatorio");
        }

        List<DateTimeFormatter> formatos = List.of(
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,              // 2026-03-03T10:00:00
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"), // 2026-03-03 10:00:00
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")     // 2026-03-03 10:00
        );

        for (DateTimeFormatter f : formatos) {
            try {
                return LocalDateTime.parse(valor.trim(), f);
            } catch (DateTimeParseException ignored) {}
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Formato inválido para " + campo + ". Usa 'yyyy-MM-ddTHH:mm:ss' o 'yyyy-MM-dd HH:mm'"
        );
    }
}