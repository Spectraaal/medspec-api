package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicaResponseDTO {
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String direccion;
    @NotBlank
    private String telefono;
    @NotBlank
    private LocalDateTime creadoEn;
}