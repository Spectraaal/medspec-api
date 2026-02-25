package com.medspec.medspec_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformeMedicoResponseDTO {
    private Long id;

    private Long citaId;
    private Long pacienteId;
    private Long medicoId;

    private String titulo;
    private String resumen;
    private String diagnostico;
    private String tratamiento;

    private LocalDateTime emitidoEn;
    private String pdfUrl;
}