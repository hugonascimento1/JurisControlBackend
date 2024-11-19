package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.service.RegistroDeInfoService;

import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.exception.RegistroDeInfoNotFoundException;
import com.juriscontrol.demo.dto.RegistroDeInfoDTO.CriarRegistroDeInfoDTO;
import com.juriscontrol.demo.dto.RegistroDeInfoDTO.ExibirRegistroDeInfoDTO;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.model.RegistroDeInfo;
import com.juriscontrol.demo.service.ProcessoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/registroDeInfo")
public class RegistroDeInfoController {

    @Autowired
    private RegistroDeInfoService  registroDeInfoService;

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    public ResponseEntity<?> criarRegistroDeInfo(@RequestBody CriarRegistroDeInfoDTO dto) {
        try {
            ListaTudoProcessoDTO processoDTO = processoService.buscarProcessoPorId(dto.getProcessoId());
            if (processoDTO == null) {
                throw new ProcessoNotFoundException("Processo não encontrado");
            }

            Processo processo = new Processo();
            processo.setId(processoDTO.getId());

            RegistroDeInfo novoRegistroDeInfo = registroDeInfoService.criarRegistroDeInfo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoRegistroDeInfo);

        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<ExibirRegistroDeInfoDTO>> buscarTodosRegistrosDeInfo() {
        List<ExibirRegistroDeInfoDTO> registrosDeInfo = registroDeInfoService.buscarTodosRegistrosDeInfo();
        return ResponseEntity.ok(registrosDeInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExibirRegistroDeInfoDTO> buscarRegistroDeInfoPorId(@PathVariable Long id) {
        try {
            ExibirRegistroDeInfoDTO registroDeInfoDTO = registroDeInfoService.buscarRegistroDeInfoPorId(id);
            return ResponseEntity.ok(registroDeInfoDTO);
        } catch (RegistroDeInfoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistroDeInfo(@PathVariable Long id) {
        try {
            registroDeInfoService.deletarRegistroDeInfo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RegistroDeInfoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
}
