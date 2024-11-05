package main.java.com.juriscontrol.demo.dto.DocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getters
@Setters
public class AtualizarDocumentoDTO {
    private String titulo;
	private String tipoDoc;
	private Multipartfile anexo; //private byte[] anexo;
}