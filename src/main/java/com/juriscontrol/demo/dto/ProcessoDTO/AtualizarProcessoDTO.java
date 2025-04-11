package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarProcessoDTO {

    private String vara;
    private String classeTipo;
    private String assuntosTitulo;
    private String status;
    private String tipo;
    private Long advogadoAutorId;
    private Long advogadoReuId;


    // getNumeroProcesso());
    //         processo.setVara(dto.getVara());
    //         processo.setClasseTipo(dto.getClasseTipo());
    //         processo.setAssuntosTitulo(dto.getAssuntosTitulo());
    //         processo.setStatus(dto.getStatus());
    //         processo.setAutor(dto.getAutor());
    //         processo.setReu(dto.getReu());
}