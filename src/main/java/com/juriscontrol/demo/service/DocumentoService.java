package com.juriscontrol.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.juriscontrol.demo.dto.DocumentoDTO.AtualizarDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.CriarDocumentoDTO;
//import com.juriscontrol.demo.dto.DocumentoDTO.CriarVariosDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.ListaTudoDocumentoDTO;
import com.juriscontrol.demo.dto.DocumentoDTO.ArquivoDTO.ArquivoDTO;
import com.juriscontrol.demo.exception.DocumentoNotFoundException;
import com.juriscontrol.demo.exception.RegistroDeInfoNotFoundException;
import com.juriscontrol.demo.model.Documento;
import com.juriscontrol.demo.model.RegistroDeInfo;
import com.juriscontrol.demo.repository.DocumentoRepository;
import com.juriscontrol.demo.repository.RegistroDeInfoRepository;

@Service
public class DocumentoService {
    
    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private RegistroDeInfoRepository registroRepository;

    @Autowired
    private ArquivoService arquivoService;

    
    public Documento criarDocumento(CriarDocumentoDTO documentoDTO) throws RegistroDeInfoNotFoundException, IOException {
        Optional<RegistroDeInfo> opRegistro = registroRepository.findById(documentoDTO.getRegistroId());

		if(opRegistro.isEmpty()) {
			throw new RegistroDeInfoNotFoundException("Processo não encontrado.");
		}
		
		MultipartFile anexo = documentoDTO.getAnexo();
        if (anexo == null || anexo.isEmpty()) {
            throw new IOException("O arquivo anexado está vazio.");
        }
        
        ArquivoDTO arquivoDTO = arquivoService.carregarArquivo(anexo);

        Documento documento = new Documento();
        documento.setRegistroDeInfo(opRegistro.get());
        
        String nomeDocumento = documentoDTO.getNomeDocumento();
        if (nomeDocumento == null || nomeDocumento.isEmpty()) {
            nomeDocumento = arquivoDTO.getNomeArquivo();
        }
        documento.setNomeDocumento(nomeDocumento);
        documento.setTipoDocumento(arquivoDTO.getTipoArquivo());
        documento.setTamDocumento(arquivoDTO.getTamArquivo());
        documento.setAnexo(anexo.getBytes());
		
		return documentoRepository.save(documento);
    }
    
    /*public List<Documento> criarVariosDocumentos(CriarVariosDocumentoDTO documentoDTO) throws ProcessoNotFoundException, IOException {
        Optional<Processo> opProcesso = registroRepository.findById(documentoDTO.getProcessoId());

		if(opProcesso.isEmpty()) {
			throw new ProcessoNotFoundException("Processo não encontrado.");
		}
        
        List<Documento> documentos = new ArrayList<>();
        for (MultipartFile anexo : documentoDTO.getAnexos()) {
            ArquivoDTO arquivoDTO = arquivoService.carregarArquivo(anexo); // Carrega o arquivo individualmente

            Documento documento = new Documento();
            documento.setProcesso(opProcesso.get()); 
            documento.setNomeDocumento(arquivoDTO.getNomeArquivo()); 
            documento.setTipoDocumento(arquivoDTO.getTipoArquivo()); 
            documento.setTamDocumento(arquivoDTO.getTamArquivo());   
            documento.setAnexo(anexo.getBytes()); 

            documentos.add(documentoRepository.save(documento));
        }

        return documentos;
    }*/

    public ListaDocumentoDTO buscarDocumentoPorId(Long id) throws DocumentoNotFoundException {
		Optional<Documento> opDocumento = documentoRepository.findById(id);

		if(opDocumento.isEmpty()) {
			throw new DocumentoNotFoundException("Documento nãoencontrado");
		}

		Documento documento = opDocumento.get();
		return new ListaDocumentoDTO(
			documento.getNomeDocumento(),
			documento.getAnexo()
		);
	}

    public List<ListaTudoDocumentoDTO> buscarTodosDocumentos() {
		return documentoRepository.findAll().stream().map(documento -> 
			new ListaTudoDocumentoDTO(
                documento.getId(),
				documento.getNomeDocumento(),
				documento.getTipoDocumento(),
				documento.getTamDocumento(),
				documento.getAnexo()
			)).collect(Collectors.toList());
	}

    public Documento atualizarDocumento(AtualizarDocumentoDTO documentoDTO, Long id) throws DocumentoNotFoundException, IOException {
		Optional<Documento> opDocumento = documentoRepository.findById(id);

		if(opDocumento.isEmpty()) {
			throw new DocumentoNotFoundException("Documento não encontrado.");
		}

		Documento documento = opDocumento.get();

		MultipartFile anexo = documentoDTO.getAnexo();
        if(documento.getNomeDocumento().isEmpty() || documento.getNomeDocumento() == null) {
            documento.setNomeDocumento(anexo.getOriginalFilename());
        } else {
            documento.setNomeDocumento(documentoDTO.getNomeDocumento());
        }
        documento.setTipoDocumento(anexo.getContentType());
        documento.setTamDocumento(anexo.getSize());
		documento.setAnexo(anexo.getBytes());

		return documentoRepository.save(documento);		
	}

    public void deletarDocumento(Long id) throws DocumentoNotFoundException {
		Optional<Documento> opDocumento = documentoRepository.findById(id);

        if(opDocumento.isEmpty()) {
                throw new DocumentoNotFoundException("Documento não encontrado.");
        }

        documentoRepository.delete(opDocumento.get());
	}

    /*
    public ListaTudoDocumentoDTO baixarDocumento() {
    
    }
    */
}