package com.juriscontrol.demo.dto.ClienteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarClienteDTO {
    
    private String nome;
    private String telefone;
    private String endereco;
    private String tipo;

}
