package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.CadastroCompletoAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.CriarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.ListaTudoAdministradorDTO;
import com.juriscontrol.demo.exception.AdministradorNotFoundException;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.service.AdministradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/completo")
    public ResponseEntity<String> cadastrarAdministradorCompleto(@RequestBody CadastroCompletoAdministradorDTO dto) {
        administradorService.cadastrarCompleto(dto);
        return ResponseEntity.ok("Administrador cadastrado com sucesso!");
    }

    @PostMapping
    public ResponseEntity<Administrador> criarAdministrador(@RequestBody CriarAdministradorDTO dto) {
        Administrador administrador = administradorService.criarAdministrador(dto);
        return ResponseEntity.ok(administrador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> atualizarAdministrador(@PathVariable Long id, @RequestBody AtualizarAdministradorDTO dto) throws AdministradorNotFoundException {
        Administrador administrador = administradorService.atualizarAdministrador(id, dto);
        return ResponseEntity.ok(administrador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTudoAdministradorDTO> buscarPorId(@PathVariable Long id) throws AdministradorNotFoundException {
        return ResponseEntity.ok(administradorService.buscarPorIdAdministrador(id));
    }

    @GetMapping
    public ResponseEntity<List<ListaTudoAdministradorDTO>> listarTodos() {
        return ResponseEntity.ok(administradorService.buscarTodosAdministradores());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable Long id) throws AdministradorNotFoundException {
        administradorService.deletarAdministrador(id);
        return ResponseEntity.noContent().build();
    }
}
