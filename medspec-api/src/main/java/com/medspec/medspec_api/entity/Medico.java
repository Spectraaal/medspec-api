package com.medspec.medspec_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
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
    @Size(max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String numeroColegiado;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String especialidad;

    @Size(min = 9, max = 15)
    @Column(length = 15)
    private String telefono;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clinica_id", nullable = false)
    private Clinica clinica;
}