package com.juriscontrol.demo.dto.DocumentoDTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarDocumentoDTO {
	
    private String nomeDocumento;
	private String tipoDocumento;
	private Long tamDocumento;
	private MultipartFile anexo;
}