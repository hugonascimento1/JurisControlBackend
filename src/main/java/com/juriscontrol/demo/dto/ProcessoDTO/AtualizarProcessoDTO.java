package com.juriscontrol.demo.dto.ProcessoDTO;

import java.util.List;

import com.juriscontrol.demo.model.Anexo;
import com.juriscontrol.demo.model.Movimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarProcessoDTO {

    private Long id;
    private String vara;
    private String classeTipo;
    private String assuntosTitulo;
    private String status;
    private String nomeAutor;
    private String advogadoAutor;
    private String nomeReu;
    private String advogadoReu;
    private List<Movimento> movimentos;
    private List<Anexo> anexoDocumentos;
    private Long advogadoId;

    // getNumeroProcesso());
    //         processo.setVara(dto.getVara());
    //         processo.setClasseTipo(dto.getClasseTipo());
    //         processo.setAssuntosTitulo(dto.getAssuntosTitulo());
    //         processo.setStatus(dto.getStatus());
    //         processo.setAutor(dto.getAutor());
    //         processo.setReu(dto.getReu());
}