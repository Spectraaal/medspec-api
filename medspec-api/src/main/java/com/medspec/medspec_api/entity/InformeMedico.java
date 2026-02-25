package com.medspec.medspec_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "informes_medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformeMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    private Cita cita;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @NotBlank
    @Size(min = 3, max = 120)
    @Column(nullable = false, length = 120)
    private String titulo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String resumen;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamiento;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime emitidoEn;

    @Column(length = 255)
    private String pdfUrl;
}