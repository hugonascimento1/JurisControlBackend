package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.MovimentoDTO.AtualizarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.CriarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.MovimentoDTO;
import com.juriscontrol.demo.exception.MovimentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.service.MovimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentoController {

    @Autowired
    private MovimentoService movimentoService;

    @PostMapping
    public ResponseEntity<MovimentoDTO> criarMovimentacao(@Valid @RequestBody CriarMovimentoDTO criarMovimentoDTO) {
        try {
            MovimentoDTO novoMovimento = movimentoService.criarMovimentacao(criarMovimentoDTO);
            return new ResponseEntity<>(novoMovimento, HttpStatus.CREATED);
        } catch (ProcessoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoDTO> buscarMovimentacaoPorId(@PathVariable Long id) {
        try {
            MovimentoDTO movimento = movimentoService.buscarMovimentacaoPorId(id);
            return ResponseEntity.ok(movimento);
        } catch (MovimentoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MovimentoDTO>> buscarTudoMovimentacao() {
        List<MovimentoDTO> movimentos = movimentoService.buscarTudoMovimentacao();
        return ResponseEntity.ok(movimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentoDTO> atualizarMovimentacao(@PathVariable Long id, @Valid @RequestBody AtualizarMovimentoDTO atualizarMovimentoDTO) {
        try {
            MovimentoDTO movimentoAtualizado = movimentoService.atualizarMovimentacao(id, atualizarMovimentoDTO);
            return ResponseEntity.ok(movimentoAtualizado);
        } catch (MovimentoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ProcessoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMovimentacao(@PathVariable Long id) {
        try {
            movimentoService.deletarMovimentacao(id);
            return ResponseEntity.noContent().build();
        } catch (MovimentoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}