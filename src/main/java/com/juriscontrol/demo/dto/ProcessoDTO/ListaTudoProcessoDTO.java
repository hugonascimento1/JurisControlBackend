package com.juriscontrol.demo.dto.ProcessoDTO;

// import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
import com.juriscontrol.demo.model.Advogado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoProcessoDTO {

    private Long id;
    private String numeroProcesso;
    private String vara;
    private String classeTipo;
    private String assuntosTitulo;
    private String status;
    private String autor;
    private Advogado advogadoAutor;
    private String reu;
    private Advogado advogadoReu;
    // private List<Movimentos> movimentos;
    // private List<ListaDocumentoDTO> documentos;
}