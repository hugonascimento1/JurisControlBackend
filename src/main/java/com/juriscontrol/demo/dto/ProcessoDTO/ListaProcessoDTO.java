package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaProcessoDTO {
    
    private String numeroProcesso;
    private String vara;
    private String assuntosTitulo;
    private String status;
    private String classeTipo;
    private Long advogadoAutorId;
    private Long adovgadoReuId;
    private String autor;
    private String reu;
}