package main.java.com.juriscontrol.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.DocumentosDTO.CriarDocumentoDTO;
import com.juriscontrol.demo.model.Documento;
import com.juriscontrol.demo.repository.DocumentoRepository;

import main.java.com.juriscontrol.demo.dto.DocumentoDTO.AtualizarDocumentoDTO;
import main.java.com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
import main.java.com.juriscontrol.demo.dto.DocumentoDTO.ListaTodosDocumentosDTO;
import main.java.com.juriscontrol.demo.exception.DocumentoNotFoundException;

@Service
public class DocumentosService {
	
	@Autowired
	private DocumentoRepository documentoRepository;

	public Documento salvarDocumento(CriarDocumentoDTO documentoDTO) throws IOException {
		Documento documento = new Documento();
		documento.setTitulo(documentoDTO.getTitulo());
		documento.setTipoDoc(documentoDTO.getTipoDoc());
		documento.setAnexo(documentoDTO.getAnexo().getBytes());

		Processo processo = processosService.buscarProcessoPorId(documentoDTO.getProcessoId());
		docs.setProcessos(processo);

		return documentosRepository.save(documento);
	}

	public ListaDocumentoDTO buscarDocumento(Long id) throws DocumentoNotFoundException {
		Optional<Documento> opDocumento = documentosRepository.findById(id);

        if(opDocumento.isEmpty()) {
            throw new DocumentoNotFoundException("Documento não encontrado.");
        }
        
        Documento documento = opDocumento.get();
        return new ListaDocumentoDTO(
            documento.getTitulo(),
            documento.getAnexo()
        );
	}

    public List<ListaTodosDocumentosDTO> buscarTodosDocumentos() {
        return documentosRepository.findAll().stream().map(documento -> 
			new ListaTodosDocumentosDTO(
				documento.getTitulo(),
				documento.getAnexo(),
				documento.getProcesso() != null ? processo.getProcesso().getId() : null
			))
			.collect(Collectors.toList());
    }

    public Documento atualizarDocumento(AtualizarDocumentoDTO documentoDTO, Long id) throws DocumentoNotFoundException {
        Optional<Documento> opDocumento = documentosRepository.findById(id);

        if(opDocumento.isEmpty()) {
            throw new DocumentoNotFoundException("Documento não encontrado.");
        }

        Documento documento = opDocumento.get();
        documento.setTitulo(documentoDTO.getTitulo());
		documento.setTipoDoc(documentoDTO.getTipoDoc());
		documento.setAnexo(documentoDTO.getAnexo().getBytes());

        return documentosRepository.save(documento);
    }

    public void deletarDocumento(Long id) throws DocumentoNotFoundException {
        Optional<Documento> opDocumento = documentosRepository.findById(id);

        if(opDocumentos.isEmpty()) {
            throw new DocumentoNotFoundException("Documento não encontrado.");
        } else {
            documentosRepository.delete(opDocumento.get());
        }
    }
    
    //baixar documento
}