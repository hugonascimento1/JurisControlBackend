package com.juriscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.Advogado;

@Repository
public interface AdvogadoRepository extends JpaRepository<Advogado, Long> {

}
