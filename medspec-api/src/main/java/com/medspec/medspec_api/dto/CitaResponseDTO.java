package com.medspec.medspec_api.dto;

import com.medspec.medspec_api.entity.EstadoCita;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaResponseDTO {

    private Long id;

    private Long clinicaId;
    private Long pacienteId;
    private Long medicoId;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    private EstadoCita estado;

    private String motivo;
}