package com.medspec.medspec_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "clinicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 120)
    @Column(nullable = false, length = 120)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 180)
    @Column(nullable = false, length = 180)
    private String direccion;

    @Size(min = 9, max = 15)
    @Column(length = 15)
    private String telefono;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creadoEn;
}
