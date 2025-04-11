package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarProcessoDTO {

    private String numeroProcesso;
    private String status;
    private String classeTipo;
    private String vara;
    private String assuntosTitulo;
    private Long advogadoAutorId;
    private Long advogadoReuId;
    private String autor;
    private String reu;
}