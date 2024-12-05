package com.juriscontrol.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.DocumentoDTO.AtualizarDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.CriarDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.CriarVariosDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.ListaTudoDocumentoDTO;
import com.juriscontrol.demo.exception.DocumentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.exception.RegistroDeInfoNotFoundException;
import com.juriscontrol.demo.model.Documento;
import com.juriscontrol.demo.service.DocumentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/documento")
public class DocumentoController {
    
    @Autowired
    private DocumentoService documentoService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Documento> criarDocumento(@ModelAttribute CriarDocumentoDTO documentoDTO) {
        try {
            Documento documento = documentoService.criarDocumento(documentoDTO);
            return new ResponseEntity<>(documento, HttpStatus.CREATED);
        } catch (RegistroDeInfoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@PostMapping("/add/several")
    public ResponseEntity<List<Documento>> criarVariosDocumentos(@RequestBody CriarVariosDocumentoDTO documentoDTO) {
        try {
            List<Documento> documentos = documentoService.criarVariosDocumentos(documentoDTO);
            return new ResponseEntity<>(documentos, HttpStatus.CREATED);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
    

    @GetMapping("/{id}")
    public ResponseEntity<ListaDocumentoDTO> buscarDocumentoPorId(@PathVariable Long id) {
        try {
            ListaDocumentoDTO documento = documentoService.buscarDocumentoPorId(id);
            return ResponseEntity.ok(documento);
        } catch (DocumentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<ListaTudoDocumentoDTO>> buscarTodosDocumentos() {
        List<ListaTudoDocumentoDTO> documentos = documentoService.buscarTodosDocumentos();
        return ResponseEntity.ok(documentos);
    }
    
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Documento> atualizarDocumento(@ModelAttribute AtualizarDocumentoDTO documentoDTO, @PathVariable Long id) {
        try {
            Documento documentoNovo = documentoService.atualizarDocumento(documentoDTO, id);
            return new ResponseEntity<>(documentoNovo, HttpStatus.OK);
        } catch (DocumentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable Long id) {
        try {
            documentoService.deletarDocumento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}