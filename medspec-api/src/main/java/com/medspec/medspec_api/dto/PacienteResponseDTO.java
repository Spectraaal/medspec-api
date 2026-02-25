package com.medspec.medspec_api.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteResponseDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String documentoId;
}
