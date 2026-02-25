package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoCreateDTO {

    @NotBlank
    @Size(max = 80)
    private String nombre;

    @NotBlank
    @Size(max = 120)
    private String apellidos;

    @NotBlank
    @Size(max = 30)
    private String numeroColegiado;

    @NotBlank
    @Size(max = 80)
    private String especialidad;

    @Size(min = 9, max = 15)
    private String telefono;
}