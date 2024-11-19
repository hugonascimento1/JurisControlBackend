package com.juriscontrol.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AdvogadoDTO.AtualizarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.CriarAdvogadoDTO;
// import com.juriscontrol.demo.dto.AdvogadoDTO.ListaAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaTudoAdvogadoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.service.AdvogadoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;



@RestController
@RequestMapping("/advogados")
public class AdvogadoController {
    
    @Autowired
    private AdvogadoService advogadoService;

    @PostMapping("")
    public ResponseEntity<Advogado> criarAdvogado(@RequestBody CriarAdvogadoDTO dto) {
        Advogado advogadoCriado = advogadoService.criarAdvogado(dto);
        return new ResponseEntity<>(advogadoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Advogado> atualizarAdvogado(@PathVariable Long id, @RequestBody AtualizarAdvogadoDTO dto) {
        try {
            Advogado advogadoAtualizado = advogadoService.atualizarAdvogado(id, dto);
            return new ResponseEntity<>(advogadoAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTudoAdvogadoDTO> buscarPorIdAdvogado(@PathVariable Long id) {
        try {
            ListaTudoAdvogadoDTO advogadoDTO = advogadoService.buscarPorIdAdvogado(id);
            return new ResponseEntity<>(advogadoDTO, HttpStatus.OK);
        } catch (AdvogadoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListaTudoAdvogadoDTO>> buscarTodosAdvogados() {
        List<ListaTudoAdvogadoDTO> advogados = advogadoService.buscarTodosAdvogados();
        return new ResponseEntity<>(advogados, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdvogado(@PathVariable Long id) {
        try {
            advogadoService.deletarAdvogado(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AdvogadoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
