package com.juriscontrol.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.Advogado;

@Repository
public interface AdvogadoRepository extends JpaRepository<Advogado, Long> {
    
    List<Advogado> findByEscritorioId(Long escritorioId);
    Optional<Advogado> findByEmail(String email);
}
