package com.medspec.medspec_api.controller;

import com.medspec.medspec_api.dto.CitaCreateDTO;
import com.medspec.medspec_api.dto.CitaResponseDTO;
import com.medspec.medspec_api.entity.Cita;
import com.medspec.medspec_api.entity.EstadoCita;
import com.medspec.medspec_api.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public CitaResponseDTO crear(@Valid @RequestBody CitaCreateDTO dto) {
        return toDTO(
                citaService.crear(
                        dto.getClinicaId(),
                        dto.getPacienteId(),
                        dto.getMedicoId(),
                        dto.getInicio(),
                        dto.getFin(),
                        dto.getMotivo()
                )
        );
    }

    @GetMapping
    public List<CitaResponseDTO> obtenerTodas() {
        return citaService.obtenerTodas().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public CitaResponseDTO obtenerPorId(@PathVariable Long id) {
        return toDTO(citaService.obtenerPorId(id));
    }

    @GetMapping("/por-paciente/{pacienteId}")
    public List<CitaResponseDTO> obtenerPorPaciente(@PathVariable Long pacienteId) {
        return citaService.obtenerPorPaciente(pacienteId).stream().map(this::toDTO).toList();
    }

    @GetMapping("/por-medico/{medicoId}")
    public List<CitaResponseDTO> obtenerPorMedico(@PathVariable Long medicoId) {
        return citaService.obtenerPorMedico(medicoId).stream().map(this::toDTO).toList();
    }

    @PutMapping("/{id}/estado")
    public CitaResponseDTO cambiarEstado(@PathVariable Long id, @RequestParam EstadoCita estado) {
        return toDTO(citaService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
    }

    private CitaResponseDTO toDTO(Cita c) {
        return CitaResponseDTO.builder()
                .id(c.getId())
                .clinicaId(c.getClinica().getId())
                .pacienteId(c.getPaciente().getId())
                .medicoId(c.getMedico().getId())
                .inicio(c.getInicio())
                .fin(c.getFin())
                .estado(c.getEstado())
                .motivo(c.getMotivo())
                .build();
    }

}
