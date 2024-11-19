package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.DocumentoDTO.CriarDocumentoDTO;
// import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.model.Documento;
import com.juriscontrol.demo.service.DocumentoService;
import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
// import com.juriscontrol.demo.dto.DocumentoDTO.ListaTodosDocumentosDTO;
import com.juriscontrol.demo.exception.DocumentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.dto.DocumentoDTO.AtualizarDocumentoDTO;

import java.io.IOException;
// import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentoService;

	@PostMapping("/add")
	public ResponseEntity<Documento> criarDocumento(@ModelAttribute CriarDocumentoDTO docsDTO, @PathVariable Long id) throws IOException {
		try {
			Documento documento = documentoService.criarDocumento(docsDTO);
			return new ResponseEntity<>(documento, HttpStatus.CREATED);
		} catch(ProcessoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ListaDocumentoDTO> buscarDocumentoPorId(@PathVariable Long id) {
		try {
			ListaDocumentoDTO documentoDTO = documentoService.buscarDocumento(id);
			return ResponseEntity.ok(documentoDTO);
		} catch(DocumentoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<ListaDocumentoDTO>> buscarTodosDocumentos() {
		List<ListaDocumentoDTO> documentos = documentoService.buscarTodosDocumentos();
		return ResponseEntity.ok(documentos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizarDocumento(@PathVariable Long id, @RequestBody AtualizarDocumentoDTO dto) {
		try {
			Documento documentoAtualizado = documentoService.atualizarDocumento(id, dto);
			return new ResponseEntity<>(documentoAtualizado, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
		try{
			documentoService.deletarDocumento(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(DocumentoNotFoundException docsException) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//Optional no service
	/*@GetMapping("/{id}")
	public ResponseEntity<byte[]> baixarDocumento(@PathVariable Long id) {
		/*Optional<Documentos> opDocs = documentoService.buscarDocumento(id);		
		
		if(opDocs.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Documentos documento = opDocs.get();
		return ResponseEntity.ok()
			.contentType(MediaType.APPLICATION_OCTET_STREAM)
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.get().getitulo() + "\"")
			.body(documento.getAnexo());

		Documentos documento = documentosService.buscarDocumento(id);
		
		if (documento == null) {
			return ResponseEntity.notFound().build();
		}
	
		BaixarDocumentoDTO baixarDocumentoDTO = new BaixarDocumentoDTO();
		baixarDocumentoDTO.setId(documento.getId());
		baixarDocumentoDTO.setTitulo(documento.getTitulo());
		baixarDocumentoDTO.setAnexo(documento.getAnexo());
	
		return ResponseEntity.ok(baixarDocumentoDTO);
	}*/
}