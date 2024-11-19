package com.juriscontrol.demo.dto.DocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTodosDocumentosDTO {
    
    private Long id;
    private String titulo;
	private String tipoDocumento;
	private byte[] anexo;
}