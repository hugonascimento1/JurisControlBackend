package com.juriscontrol.demo.dto.DocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoDocumentoDTO {
    
    private Long id;
    private String nomeDocumento;
    private String tipoDocumento;
    private Long tamDocumento;
    private byte[] anexo;
}