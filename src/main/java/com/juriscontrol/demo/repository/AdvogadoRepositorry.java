package com.juriscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Advogado;

public interface AdvogadoRepositorry extends JpaRepository<Advogado, Long> {
    
    List<Advogado> findByEscritorioId(Long escritorioId);
}
