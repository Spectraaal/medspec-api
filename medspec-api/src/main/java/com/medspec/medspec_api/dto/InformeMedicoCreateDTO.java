package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformeMedicoCreateDTO {

    @NotBlank
    @Size(min = 3, max = 120)
    private String titulo;

    @NotBlank
    private String resumen;

    private String diagnostico;
    private String tratamiento;

    @NotNull
    private LocalDateTime emitidoEn;

    private String pdfUrl;
}