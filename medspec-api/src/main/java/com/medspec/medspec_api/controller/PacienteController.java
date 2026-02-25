package com.medspec.medspec_api.controller;

import com.medspec.medspec_api.dto.PacienteCreateDTO;
import com.medspec.medspec_api.dto.PacienteResponseDTO;
import com.medspec.medspec_api.entity.Paciente;
import com.medspec.medspec_api.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public PacienteResponseDTO crear(@Valid @RequestBody PacienteCreateDTO dto) {
        Paciente paciente = Paciente.builder()
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .telefono(dto.getTelefono())
                .fechaNacimiento(dto.getFechaNacimiento())
                .documentoId(dto.getDocumentoId())
                .build();

        return toDTO(pacienteService.crear(paciente));
    }

    @GetMapping
    public List<PacienteResponseDTO> obtenerTodos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidos
    ) {
        return pacienteService.buscar(nombre, apellidos).stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public PacienteResponseDTO obtenerPorId(@PathVariable Long id) {
        return toDTO(pacienteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public PacienteResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody PacienteCreateDTO dto) {
        Paciente paciente = Paciente.builder()
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .telefono(dto.getTelefono())
                .fechaNacimiento(dto.getFechaNacimiento())
                .documentoId(dto.getDocumentoId())
                .build();

        return toDTO(pacienteService.actualizar(id, paciente));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
    }

    private PacienteResponseDTO toDTO(Paciente p) {
        return PacienteResponseDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .telefono(p.getTelefono())
                .fechaNacimiento(p.getFechaNacimiento())
                .documentoId(p.getDocumentoId())
                .build();
    }
}