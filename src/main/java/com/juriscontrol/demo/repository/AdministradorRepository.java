package com.juriscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
