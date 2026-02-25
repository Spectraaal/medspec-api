package com.medspec.medspec_api.repository;

import com.medspec.medspec_api.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    List<Clinica> findByNombreContainingIgnoreCase(String nombre);
}
