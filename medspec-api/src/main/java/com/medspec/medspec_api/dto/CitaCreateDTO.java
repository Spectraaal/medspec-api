package com.medspec.medspec_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaCreateDTO {

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fin;

    @Size(max = 200)
    private String motivo;
}