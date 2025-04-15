package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.AgendaTarefaDTO.AtualizarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.CriarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AgendaTarefaDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.AgendaTarefaNotFoundException;
import com.juriscontrol.demo.service.AgendaTarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/agenda-tarefas")
public class AgendaTarefaController {

    @Autowired
    private AgendaTarefaService agendaTarefaService;

    @GetMapping("/{id}")
    public ResponseEntity<AgendaTarefaDTO> verTarefaAgenda(@PathVariable Long id) {
        try {
            AgendaTarefaDTO tarefa = agendaTarefaService.verTarefaAgenda(id);
            return ResponseEntity.ok(tarefa);
        } catch (AgendaTarefaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<AgendaTarefaDTO> adicionarTarefaAgenda(@Valid @RequestBody CriarAgendaTarefaDTO createDTO) {
        try {
            AgendaTarefaDTO novaTarefa = agendaTarefaService.adicionarTarefaAgenda(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
        } catch (AdvogadoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaTarefaDTO> atualizarTarefaAgenda(@PathVariable Long id, @Valid @RequestBody AtualizarAgendaTarefaDTO updateDTO) {
        try {
            AgendaTarefaDTO tarefaAtualizada = agendaTarefaService.atualizarTarefaAgenda(id, updateDTO);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (AgendaTarefaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AdvogadoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefaAgenda(@PathVariable Long id) {
        try {
            agendaTarefaService.deletarTarefaAgenda(id);
            return ResponseEntity.noContent().build();
        } catch (AgendaTarefaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AgendaTarefaDTO>> buscarTodasTarefas() {
        List<AgendaTarefaDTO> tarefas = agendaTarefaService.buscarTodasTarefa();
        return ResponseEntity.ok(tarefas);
    }
}
