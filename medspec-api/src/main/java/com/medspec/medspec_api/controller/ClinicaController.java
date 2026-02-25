package com.medspec.medspec_api.controller;

import com.medspec.medspec_api.dto.ClinicaCreateDTO;
import com.medspec.medspec_api.dto.ClinicaResponseDTO;
import com.medspec.medspec_api.entity.Clinica;
import com.medspec.medspec_api.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicas")
public class ClinicaController {

    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @PostMapping
    public ClinicaResponseDTO crear(@Valid @RequestBody ClinicaCreateDTO dto) {
        Clinica clinica = Clinica.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .build();

        return toDTO(clinicaService.crear(clinica));
    }

    @GetMapping
    public List<ClinicaResponseDTO> obtenerTodas(@RequestParam(required = false) String nombre) {
        return clinicaService.buscar(nombre).stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ClinicaResponseDTO obtenerPorId(@PathVariable Long id) {
        return toDTO(clinicaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ClinicaResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ClinicaCreateDTO dto) {
        Clinica clinica = Clinica.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .build();

        return toDTO(clinicaService.actualizar(id, clinica));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clinicaService.eliminar(id);
    }

    private ClinicaResponseDTO toDTO(Clinica c) {
        return ClinicaResponseDTO.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .direccion(c.getDireccion())
                .telefono(c.getTelefono())
                .creadoEn(c.getCreadoEn())
                .build();
    }
}

