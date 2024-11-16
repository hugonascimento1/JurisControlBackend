package com.juriscontrol.demo.dto.ProcessoDTO;

import java.time.LocalDateTime;

import com.juriscontrol.demo.dto.ClienteDTO.CriarClienteDTO;

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
    private String descricao;
    private String status;
    private String tipo;
    private LocalDateTime dataInicio;
    private Long advogadoAutorId;
    private Long adovgadoReuId;
    private CriarClienteDTO clienteAutor;
    private CriarClienteDTO clienteReu;

}