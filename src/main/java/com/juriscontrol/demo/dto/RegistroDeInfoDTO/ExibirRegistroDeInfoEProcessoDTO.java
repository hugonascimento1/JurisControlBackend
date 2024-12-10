package com.juriscontrol.demo.dto.RegistroDeInfoDTO;

import java.time.LocalDate;
import java.util.List;

import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExibirRegistroDeInfoEProcessoDTO {
    
    private Long id;
    private LocalDate data;
    private String descricao;
    private Long processoId;
    private List<ListaDocumentoDTO> documentos;
}
