package com.medspec.medspec_api.repository;

import com.medspec.medspec_api.entity.InformeMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InformeMedicoRepository extends JpaRepository<InformeMedico, Long> {

    boolean existsByCitaId(Long citaId);

    List<InformeMedico> findByPacienteId(Long pacienteId);

    List<InformeMedico> findByMedicoId(Long medicoId);
}
