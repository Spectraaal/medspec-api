package com.medspec.medspec_api.controller;

import com.medspec.medspec_api.dto.InformeMedicoCreateDTO;
import com.medspec.medspec_api.dto.InformeMedicoResponseDTO;
import com.medspec.medspec_api.entity.InformeMedico;
import com.medspec.medspec_api.service.InformeMedicoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/informes")
public class InformeMedicoController {

    private final InformeMedicoService informeMedicoService;

    public InformeMedicoController(InformeMedicoService informeMedicoService) {
        this.informeMedicoService = informeMedicoService;
    }

    @PostMapping
    public InformeMedicoResponseDTO crear(@RequestParam Long citaId, @Valid @RequestBody InformeMedicoCreateDTO dto) {
        InformeMedico informe = InformeMedico.builder()
                .titulo(dto.getTitulo())
                .resumen(dto.getResumen())
                .diagnostico(dto.getDiagnostico())
                .tratamiento(dto.getTratamiento())
                .emitidoEn(dto.getEmitidoEn())
                .pdfUrl(dto.getPdfUrl())
                .build();

        return toDTO(informeMedicoService.crear(citaId, informe));
    }

    @GetMapping("/{id}")
    public InformeMedicoResponseDTO obtenerPorId(@PathVariable Long id) {
        return toDTO(informeMedicoService.obtenerPorId(id));
    }

    @GetMapping("/por-paciente/{pacienteId}")
    public List<InformeMedicoResponseDTO> obtenerPorPaciente(@PathVariable Long pacienteId) {
        return informeMedicoService.obtenerPorPaciente(pacienteId).stream().map(this::toDTO).toList();
    }

    @GetMapping("/por-medico/{medicoId}")
    public List<InformeMedicoResponseDTO> obtenerPorMedico(@PathVariable Long medicoId) {
        return informeMedicoService.obtenerPorMedico(medicoId).stream().map(this::toDTO).toList();
    }

    @PutMapping("/{id}")
    public InformeMedicoResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody InformeMedicoCreateDTO dto) {

        InformeMedico datos = InformeMedico.builder()
                .titulo(dto.getTitulo())
                .resumen(dto.getResumen())
                .diagnostico(dto.getDiagnostico())
                .tratamiento(dto.getTratamiento())
                .emitidoEn(dto.getEmitidoEn())
                .pdfUrl(dto.getPdfUrl())
                .build();

        return toDTO(informeMedicoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        informeMedicoService.eliminar(id);
    }

    private InformeMedicoResponseDTO toDTO(InformeMedico i) {
        return InformeMedicoResponseDTO.builder()
                .id(i.getId())
                .citaId(i.getCita() != null ? i.getCita().getId() : null)
                .pacienteId(i.getPaciente() != null ? i.getPaciente().getId() : null)
                .medicoId(i.getMedico() != null ? i.getMedico().getId() : null)
                .titulo(i.getTitulo())
                .resumen(i.getResumen())
                .diagnostico(i.getDiagnostico())
                .tratamiento(i.getTratamiento())
                .emitidoEn(i.getEmitidoEn())
                .pdfUrl(i.getPdfUrl())
                .build();
    }
}