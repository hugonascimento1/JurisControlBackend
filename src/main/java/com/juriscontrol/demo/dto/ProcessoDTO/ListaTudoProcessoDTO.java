package com.juriscontrol.demo.dto.ProcessoDTO;

import java.time.LocalDateTime;
import java.util.List;

import com.juriscontrol.demo.dto.AdvogadoDTO.ListaAdvogadoDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.ListaAudienciaDTO;
import com.juriscontrol.demo.dto.ClienteDTO.CriarClienteDTO;
import com.juriscontrol.demo.dto.ClienteDTO.ListaClienteDTO;

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
    private String descricao;
    private String status;
    private String tipo;
    private LocalDateTime dataInicio;
    private ListaAdvogadoDTO advogadoAutor;
    private ListaAdvogadoDTO adovgadoReu;
    private ListaClienteDTO autor;
    private ListaClienteDTO reu;
    private List<ListaAudienciaDTO> audiencias;
}
