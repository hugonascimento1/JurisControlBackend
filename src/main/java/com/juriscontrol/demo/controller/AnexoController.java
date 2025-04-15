package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.AnexoDTO.CriarAnexoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.CriarVariosAnexosDTO;
import com.juriscontrol.demo.dto.AnexoDTO.ListaTudoAnexoDTO;
import com.juriscontrol.demo.exception.AnexoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Anexo;
import com.juriscontrol.demo.service.AnexoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
// import java.util.stream.Collectors;

@RestController
@RequestMapping("/anexos")
public class AnexoController {

    @Autowired
    private AnexoService anexoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Anexo> criarAnexo(@Valid @ModelAttribute CriarAnexoDTO anexoDTO) {
        try {
            Anexo anexoCriado = anexoService.criarAnexo(anexoDTO);
            return new ResponseEntity<>(anexoCriado, HttpStatus.CREATED);
        } catch (ProcessoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar o arquivo.");
        }
    }

    @PostMapping(value = "/varios", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Anexo>> criarVariosAnexos(@Valid @ModelAttribute CriarVariosAnexosDTO anexosDTO) {
        try {
            List<Anexo> anexosCriados = anexoService.criarVariosAnexos(anexosDTO);
            return new ResponseEntity<>(anexosCriados, HttpStatus.CREATED);
        } catch (ProcessoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar os arquivos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTudoAnexoDTO> buscarAnexoPorId(@PathVariable Long id) {
        try {
            ListaTudoAnexoDTO anexoDTO = anexoService.buscarAnexoPorId(id);
            return ResponseEntity.ok(anexoDTO);
        } catch (AnexoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anexo não encontrado.");
        }
    }

    @GetMapping("/processo/{processoId}")
    public ResponseEntity<List<ListaTudoAnexoDTO>> buscarTodosAnexosPorProcesso(@PathVariable Long processoId) {
        try {
            List<ListaTudoAnexoDTO> anexosDTO = anexoService.buscarTodosAnexosPorProcesso(processoId);
            return ResponseEntity.ok(anexosDTO);
        } catch (ProcessoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnexo(@PathVariable Long id) {
        try {
            anexoService.deletarAnexo(id);
            return ResponseEntity.noContent().build();
        } catch (AnexoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anexo não encontrado.");
        }
    }
}