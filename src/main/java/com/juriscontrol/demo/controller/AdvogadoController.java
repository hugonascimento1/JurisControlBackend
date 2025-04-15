package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.CadastroCompletoAdvogadoDTO;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.service.AdvogadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advogados")
public class AdvogadoController {

    @Autowired
    private AdvogadoService advogadoService;

    @PostMapping("/completo")
    public ResponseEntity<String> cadastrarAdvogadoCompleto(@RequestBody CadastroCompletoAdvogadoDTO dto) {
        advogadoService.cadastrarCompleto(dto);
        return ResponseEntity.ok("Advogado cadastrado com sucesso!");
    }

    @PostMapping
    public ResponseEntity<Advogado> criarAdvogado(@RequestBody Advogado advogado) {
        return ResponseEntity.ok(advogadoService.criarAdvogado(advogado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Advogado> atualizarAdvogado(@PathVariable Long id, @RequestBody Advogado advogado) {
        return ResponseEntity.ok(advogadoService.atualizarAdvogado(id, advogado));
    }

    @GetMapping
    public ResponseEntity<List<Advogado>> listarAdvogados() {
        return ResponseEntity.ok(advogadoService.listarAdvogados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advogado> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(advogadoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        advogadoService.deletarAdvogado(id);
        return ResponseEntity.noContent().build();
    }
}
