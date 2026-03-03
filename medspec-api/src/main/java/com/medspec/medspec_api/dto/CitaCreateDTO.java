package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaCreateDTO {
    @NotNull private Long clinicaId;
    @NotNull private Long pacienteId;
    @NotNull private Long medicoId;

    @NotBlank
    private String inicio;
    @NotBlank private String fin;

    @Size(max = 200)
    private String motivo;
}