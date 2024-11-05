package main.java.com.juriscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juriscontrol.demo.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}