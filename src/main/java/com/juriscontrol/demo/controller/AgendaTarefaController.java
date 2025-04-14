package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.AgendaTarefaDTO.CriarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AtualizarAgendaTarefaDTO;
import com.juriscontrol.demo.service.AgendaTarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda-tarefas")
public class AgendaTarefaController {

    @Autowired
    private AgendaTarefaService agendaTarefaService;

    @GetMapping("/{id}")
    public ResponseEntity<AgendaTarefaDTO> verTarefaAgenda(@PathVariable Long id) {
        AgendaTarefaDTO tarefa = agendaTarefaService.verTarefaAgenda(id);
        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AgendaTarefaDTO> adicionarTarefaAgenda(@Valid @RequestBody CriarAgendaTarefaDTO createDTO) {
        AgendaTarefaDTO novaTarefa = agendaTarefaService.adicionarTarefaAgenda(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaTarefaDTO> atualizarTarefaAgenda(@PathVariable Long id, @Valid @RequestBody AtualizarAgendaTarefaDTO updateDTO) {
        AgendaTarefaDTO tarefaAtualizada = agendaTarefaService.atualizarTarefaAgenda(id, updateDTO);
        if (tarefaAtualizada != null) {
            return ResponseEntity.ok(tarefaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefaAgenda(@PathVariable Long id) {
        boolean removido = agendaTarefaService.deletarTarefaAgenda(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AgendaTarefaDTO> buscarTarefaPorId(@PathVariable Long id) {
        AgendaTarefaDTO tarefa = agendaTarefaService.buscarTarefaPorId(id);
        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AgendaTarefaDTO>> buscarTodasTarefa() {
        List<AgendaTarefaDTO> tarefas = agendaTarefaService.buscarTodasTarefa();
        return ResponseEntity.ok(tarefas);
    }
}