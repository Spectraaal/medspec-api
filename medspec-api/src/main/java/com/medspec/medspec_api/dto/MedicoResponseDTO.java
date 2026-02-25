package com.medspec.medspec_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoResponseDTO {
    private Long id;
    private Long clinicaId;

    private String nombre;
    private String apellidos;

    private String numeroColegiado;
    private String especialidad;
    private String telefono;
}