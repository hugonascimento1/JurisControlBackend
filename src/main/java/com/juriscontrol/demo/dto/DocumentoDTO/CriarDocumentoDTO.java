package com.juriscontrol.demo.dto.DocumentoDTO;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarDocumentoDTO /*implements Serializable //Usada para registrar que os objetos intanciados pode ser transformados em uma sequência de bytes*/ {

	private String titulo;
	private String tipoDocumento;
	private Long tamanhoDoc;
	private MultipartFile anexo;
	private Long processoId;
}