package com.juriscontrol.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    
    Processo criarProcesso(Processo processo);
    Optional<Processo> findByNumeroProcesso(String numeroProcesso); 
}