package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.ProcessoDTO.AtualizarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.CriarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping("/add")
    public ResponseEntity<Processo> criarProcesso(@RequestBody CriarProcessoDTO dto) {
        try {
            Processo processoCriado = processoService.criarProcesso(dto);
            return new ResponseEntity<>(processoCriado, HttpStatus.CREATED);
        } catch (AdvogadoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o advogado não for encontrado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processo> atualizarProcesso(@PathVariable Long id, @RequestBody AtualizarProcessoDTO dto) {
        try {
            Processo processoAtualizado = processoService.atualizarProcesso(id, dto);
            return new ResponseEntity<>(processoAtualizado, HttpStatus.OK);
        } catch (ProcessoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o processo não for encontrado
        } catch (AdvogadoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o advogado não for encontrado durante a atualização
        }
    }

    @GetMapping("/numero/{numeroProcesso}")
    public ResponseEntity<ListaTudoProcessoDTO> buscarPorNumeroDeProcesso(@PathVariable String numeroProcesso) {
        try {
            ListaTudoProcessoDTO processo = processoService.buscarPorNumeroDeProcesso(numeroProcesso);
            return ResponseEntity.ok(processo);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o processo não for encontrado
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListaTudoProcessoDTO>> listarTodosProcessos() {
        List<ListaTudoProcessoDTO> processos = processoService.listarTodosProcessos();
        return ResponseEntity.ok(processos); // Retorna 200 com a lista de processos (pode ser vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTudoProcessoDTO> buscarProcessoPorId(@PathVariable Long id) {
        try {
            ListaTudoProcessoDTO processo = processoService.buscarProcessoPorId(id);
            return ResponseEntity.ok(processo);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o processo não for encontrado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Long id) {
        try {
            processoService.deletarProcesso(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 em caso de sucesso na deleção
        } catch (ProcessoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o processo não for encontrado
        }
    }
}