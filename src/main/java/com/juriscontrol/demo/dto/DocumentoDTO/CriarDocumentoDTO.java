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
public class CriarDocumentoDTO {
	
	private String titulo;
	private String tipoDocumento;
	private MultipartFile anexo;
	private Long processoId;
}