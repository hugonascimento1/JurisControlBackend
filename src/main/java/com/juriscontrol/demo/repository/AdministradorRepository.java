package com.juriscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
}
