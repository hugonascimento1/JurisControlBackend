package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AudienciaDTO.AtualizarAudienciaDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.CriarAudienciaDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.ListaAudienciaDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.ListaTudoAudienciaDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.AudienciaNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Audiencia;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.service.AudienciaService;
import com.juriscontrol.demo.service.ProcessoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/audiencias")
public class AudienciaController {
    
    @Autowired
    private AudienciaService audienciaService;

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    public ResponseEntity<?> criarAudiencia(@RequestBody CriarAudienciaDTO dto) {
        try {
            ListaTudoProcessoDTO processoDTO = processoService.buscarProcessoPorId(dto.getProcessoId());
            if (processoDTO == null) {
                throw new ProcessoNotFoundException("Processo não encontrado");
            }

            // Converter ListaTudoProcessoDTO para Processo, se necessário
            Processo processo = new Processo();
            processo.setId(processoDTO.getId());
            // Outros campos podem ser configurados aqui

            // Criar a audiência usando o DTO
            Audiencia novaAudiencia = audienciaService.criarAudiencia(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAudiencia);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
}

    @PutMapping("/{id}")
    public ResponseEntity<Audiencia> atualizarAudiencia(@PathVariable Long id, @RequestBody AtualizarAudienciaDTO dto) {
        try {
            Audiencia audienciaAtualiizada = audienciaService.atualizarAudiencia(id, dto);
            return new ResponseEntity<>(audienciaAtualiizada, HttpStatus.OK);
        } catch (AudienciaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListaTudoAudienciaDTO>> buscarTodasAudiencias() {
        List<ListaTudoAudienciaDTO> audiencias = audienciaService.buscarTodasAudiencias();
        return ResponseEntity.ok(audiencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaTudoAudienciaDTO> buscarAudienciaPorId(@PathVariable Long id) {
        try {
            ListaTudoAudienciaDTO audienciaDTO = audienciaService.buscarAuudienciaPorId(id);
            return ResponseEntity.ok(audienciaDTO);
        } catch (AudienciaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAudiencia(@PathVariable Long id) {
        try {
            audienciaService.deletarAudiencia(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AudienciaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}
