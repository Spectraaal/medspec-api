package com.medspec.medspec_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nombre;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String apellidos;

    @NotBlank
    @Size(min = 9, max = 15)
    @Column(nullable = false, length = 15)
    private String telefono;

    private LocalDate fechaNacimiento;

    @Column(unique = true, length = 20)
    private String documentoId; // DNI/NIE opcional
}