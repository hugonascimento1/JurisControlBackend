package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.CriarAdministradorDTO;
// import com.juriscontrol.demo.dto.AdministradorDTO.ListaAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.ListaTudoAdministradorDTO;
import com.juriscontrol.demo.exception.AdministradorNotFoundException;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.service.AdministradorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("")
    public ResponseEntity<Administrador> criarAdministrador(@RequestBody CriarAdministradorDTO dto) {
        Administrador administradorCriado = administradorService.criarAdministrador(dto);
        return new ResponseEntity<>(administradorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> atualizarAdministrador(@PathVariable Long id, @RequestBody AtualizarAdministradorDTO dto) {
        try {
            Administrador administradorAtualizado = administradorService.atualizarAdministrador(id, dto);
            return new ResponseEntity<>(administradorAtualizado, HttpStatus.OK);
        } catch (AdministradorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTudoAdministradorDTO> buscarPorIdAdministrador(@PathVariable Long id) {
        try {
            ListaTudoAdministradorDTO administradorDTO = administradorService.buscarPorIdAdministrador(id);
            return new ResponseEntity<>(administradorDTO, HttpStatus.OK);
        } catch (AdministradorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListaTudoAdministradorDTO>> buscarTodosAdministradores() {
        List<ListaTudoAdministradorDTO> administradores = administradorService.buscarTodosAdministradores();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable Long id) {
        try {
            administradorService.deletarAdministrador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AdministradorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
