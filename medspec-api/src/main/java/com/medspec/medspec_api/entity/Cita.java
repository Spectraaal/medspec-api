package com.medspec.medspec_api.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clinica_id", nullable = false)
    private Clinica clinica;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime inicio;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoCita estado;

    @Size(max = 200)
    @Column(length = 200)
    private String motivo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creadaEn;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private InformeMedico informeMedico; // puede ser null
}