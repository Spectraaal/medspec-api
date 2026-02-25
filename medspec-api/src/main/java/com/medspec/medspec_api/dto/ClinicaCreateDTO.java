package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicaCreateDTO {
    @NotBlank
    @Size(min = 3, max = 120)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 180)
    private String direccion;

    @Size(min = 9, max = 15)
    private String telefono;
}