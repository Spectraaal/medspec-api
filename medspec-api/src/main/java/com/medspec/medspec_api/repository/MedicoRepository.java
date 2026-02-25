package com.medspec.medspec_api.repository;

import com.medspec.medspec_api.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByClinicaId(Long clinicaId);

    List<Medico> findByEspecialidadContainingIgnoreCase(String especialidad);

    List<Medico> findByClinicaIdAndEspecialidadContainingIgnoreCase(Long clinicaId, String especialidad);
}