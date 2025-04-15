package com.juriscontrol.demo.dto.AnexoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoAnexoDTO {

    private Long id;
    private String nomeAnexo;
    private byte[] anexo;
    private Long processoId;
}