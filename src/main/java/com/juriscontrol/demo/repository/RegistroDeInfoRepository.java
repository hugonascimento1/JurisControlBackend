package com.juriscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.RegistroDeInfo;

public interface RegistroDeInfoRepository extends JpaRepository<RegistroDeInfo, Long> {
    
    List<RegistroDeInfoRepository> findByProcessoId(Long processoId);
}
