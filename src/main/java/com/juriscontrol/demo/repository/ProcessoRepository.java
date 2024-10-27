package com.juriscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    
    List<Processo> findByAdvogadoAutorId(Long advogadoId);
    List<Processo> findByAdvogadoReuId(Long advogadoId);
}
