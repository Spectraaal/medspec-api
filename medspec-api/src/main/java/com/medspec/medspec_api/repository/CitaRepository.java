package com.medspec.medspec_api.repository;

import com.medspec.medspec_api.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByMedicoId(Long medicoId);

    @Query("""
        SELECT (COUNT(c) > 0)
        FROM Cita c
        WHERE c.medico.id = :medicoId
          AND c.estado <> 'CANCELADA'
          AND (:citaId IS NULL OR c.id <> :citaId)
          AND (c.inicio < :fin AND c.fin > :inicio)
    """)
    boolean existeSolapamiento(
            @Param("medicoId") Long medicoId,
            @Param("citaId") Long citaId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}