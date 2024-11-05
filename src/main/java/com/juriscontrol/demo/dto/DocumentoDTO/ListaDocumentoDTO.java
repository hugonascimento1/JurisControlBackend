package main.java.com.juriscontrol.demo.dto.DocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getters
@Setters
public class ListaDocumentoDTO {
    
    private String titulo;
    private byte[] anexo;
}