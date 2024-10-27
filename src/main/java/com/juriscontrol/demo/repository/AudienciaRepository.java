package com.juriscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Audiencia;

public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {
    
    List<Audiencia> findByProcessoId(Long processoId);
}
