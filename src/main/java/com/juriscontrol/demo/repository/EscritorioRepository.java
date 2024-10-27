package com.juriscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Escritorio;

public interface EscritorioRepository extends JpaRepository<Escritorio, Long> {
    
}
