package com.juriscontrol.demo.dto.AdvogadoDTO;

import java.util.List;

import com.juriscontrol.demo.dto.ProcessoDTO.ProcessoResumoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoAdvogadoDTO {
    private Long id;
    private String nome;
    private String registroOAB;
    private String email;
    private String senha;
    private List<ProcessoResumoDTO> processosComoAutor;
    private List<ProcessoResumoDTO> processosComoReu;
    
}
