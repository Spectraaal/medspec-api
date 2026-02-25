package com.medspec.medspec_api.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicaResponseDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDateTime creadoEn;
}