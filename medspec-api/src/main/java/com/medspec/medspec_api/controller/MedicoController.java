package com.medspec.medspec_api.controller;

import com.medspec.medspec_api.dto.MedicoCreateDTO;
import com.medspec.medspec_api.dto.MedicoResponseDTO;
import com.medspec.medspec_api.entity.Medico;
import com.medspec.medspec_api.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public MedicoResponseDTO crear(@RequestParam Long clinicaId, @Valid @RequestBody MedicoCreateDTO dto) {
        Medico medico = Medico.builder()
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .numeroColegiado(dto.getNumeroColegiado())
                .especialidad(dto.getEspecialidad())
                .telefono(dto.getTelefono())
                .build();

        return toDTO(medicoService.crear(medico, clinicaId));
    }

    @GetMapping
    public List<MedicoResponseDTO> obtenerTodos(
            @RequestParam(required = false) Long clinicaId,
            @RequestParam(required = false) String especialidad
    ) {
        return medicoService.buscar(clinicaId, especialidad).stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public MedicoResponseDTO obtenerPorId(@PathVariable Long id) {
        return toDTO(medicoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public MedicoResponseDTO actualizar(
            @PathVariable Long id,
            @RequestParam Long clinicaId,
            @Valid @RequestBody MedicoCreateDTO dto
    ) {
        Medico medico = Medico.builder()
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .numeroColegiado(dto.getNumeroColegiado())
                .especialidad(dto.getEspecialidad())
                .telefono(dto.getTelefono())
                .build();

        return toDTO(medicoService.actualizar(id, medico, clinicaId));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        medicoService.eliminar(id);
    }

    private MedicoResponseDTO toDTO(Medico m) {
        return MedicoResponseDTO.builder()
                .id(m.getId())
                .clinicaId(m.getClinica() != null ? m.getClinica().getId() : null)
                .nombre(m.getNombre())
                .apellidos(m.getApellidos())
                .numeroColegiado(m.getNumeroColegiado())
                .especialidad(m.getEspecialidad())
                .telefono(m.getTelefono())
                .build();
    }
}