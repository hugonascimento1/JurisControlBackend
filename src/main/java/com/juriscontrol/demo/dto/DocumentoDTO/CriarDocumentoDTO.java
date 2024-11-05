package main.java.com.juriscontrol.demo.dto.DocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getters
@Setters
public class CriarDocumentoDTO {
	private String titulo;
	private String tipoDoc;
	private Multipartfile anexo;
}