package com.juriscontrol.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.DocumentoDTO.CriarDocumentoDTO;
import com.juriscontrol.demo.model.Documento;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.DocumentoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;
import com.juriscontrol.demo.dto.DocumentoDTO.AtualizarDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
// import com.juriscontrol.demo.dto.DocumentoDTO.ListaTodosDocumentosDTO;
import com.juriscontrol.demo.exception.DocumentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;

@Service
public class DocumentoService {
	
	@Autowired
	private DocumentoRepository documentoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

	public Documento criarDocumento(CriarDocumentoDTO documentoDTO) throws ProcessoNotFoundException, IOException {
        Optional<Processo> opProcesso = processoRepository.findById(documentoDTO.getProcessoId());

        if (opProcesso.isPresent()) {
            Documento documento = new Documento();
            documento.setProcesso(opProcesso.get());
            documento.setTitulo(documentoDTO.getTitulo());
            documento.setTipoDocumento(documentoDTO.getTipoDocumento());
            documento.setAnexo(documentoDTO.getAnexo().getBytes());    

		    return documentoRepository.save(documento);
        } else {
            throw new ProcessoNotFoundException("Processo não encontrado.");
        }
		
	}

	public ListaDocumentoDTO buscarDocumento(Long id) throws DocumentoNotFoundException {
		Optional<Documento> opDocumento = documentoRepository.findById(id);

        if(opDocumento.isEmpty()) {
            throw new DocumentoNotFoundException("Documento não encontrado.");
        }
        
        Documento documento = opDocumento.get();
        return new ListaDocumentoDTO(
            documento.getTitulo(),
            documento.getAnexo()
        );
	}

    public List<ListaDocumentoDTO> buscarTodosDocumentos() {
        return documentoRepository.findAll().stream().map(documento -> 
			new ListaDocumentoDTO(
				documento.getTitulo(),
                documento.getAnexo()
			))
			.collect(Collectors.toList());
    }

    public Documento atualizarDocumento(Long id, AtualizarDocumentoDTO dto) throws DocumentoNotFoundException, IOException {
        Optional<Documento> opDocumento = documentoRepository.findById(id);

        if(opDocumento.isEmpty()) {
            throw new DocumentoNotFoundException("Documento não encontrado.");
        }

        Documento documento = opDocumento.get();
        documento.setTitulo(dto.getTitulo());
		documento.setTipoDocumento(dto.getTipoDocumento());
		documento.setAnexo(dto.getAnexo().getBytes());

        return documentoRepository.save(documento);
    }

    public void deletarDocumento(Long id) throws DocumentoNotFoundException {
        Optional<Documento> opDocumento = documentoRepository.findById(id);

        if(opDocumento.isEmpty()) {
            throw new DocumentoNotFoundException("Documento não encontrado.");
        } else {
            documentoRepository.delete(opDocumento.get());
        }
    }
    
    //baixar documento
}