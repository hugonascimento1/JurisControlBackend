package main.java.com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.DocumentosDTO.CriarDocumentoDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.model.Documentos;
import com.juriscontrol.demo.service.DocumentosService;

import main.java.com.juriscontrol.demo.dto.DocumentosDTO.ListaDocumentoDTO;
import main.java.com.juriscontrol.demo.dto.DocumentosDTO.ListaTodosDocumentosDTO;
import main.java.com.juriscontrol.demo.exception.DocumentoNotFoundException;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RestMapping("/documentos")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentosService;

	@Autowired
	private ProcessoService processosService;

	@PostMapping("/add")
	public ResponseEntity<Documentos> inserirDocumento(@ModelAttribute CriarDocumentoDTO docsDTO, @PathVariable Long id) {
		try {
			Documentos docs = documentoService.salvarDocumento(docsDTO, docsDTO.getAnexo());
			return new ResponseEntity<>(docs, HttpStatus.CREATED);
		} catch(IOException ioException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documentos> buscarDocumentoPorId(@PathVariable Long id, @RequestBody ListaDocumentoDTO docsDTO) {
		try {
			ListaDocumentoDTO documento = documentosService.buscarDocumento(id);
			return new ResponseEntity<>(documento, HttpStatus.OK);
		} catch(DocumentoNotFoundException docsException) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<ListaTodosDocumentosDTO>> buscarTodosDocumentos() {
		List<ListaTodosDocumentosDTO> documentos = documentosService.buscarTodosDocumentos();
		return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Documentos> atualizarDocumento(@RequestBody AtualizarDocumentosDTO dto, @PathVariable Long id) {
		try {
			Documento documentoAtualizado = documentosService.atualizarDocumento(dto, null, id);
			return new ResponseEntity<>(documentoAtualizado, HttpStatus.OK);
		} catch(DocumentoNotFoundException docsException) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
		try{
			documentosService.deletarDocumento(id);
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