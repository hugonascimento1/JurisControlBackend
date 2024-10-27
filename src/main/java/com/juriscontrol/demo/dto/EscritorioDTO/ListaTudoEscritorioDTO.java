package com.juriscontrol.demo.dto.EscritorioDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoEscritorioDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
}
