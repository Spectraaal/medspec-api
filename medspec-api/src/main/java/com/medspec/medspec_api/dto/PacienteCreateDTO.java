package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteCreateDTO {

    @NotBlank
    @Size(max = 80)
    private String nombre;

    @NotBlank
    @Size(max = 120)
    private String apellidos;

    @NotBlank
    @Size(min = 9, max = 15)
    private String telefono;

    private LocalDate fechaNacimiento;

    @Size(max = 20)
    private String documentoId;
}