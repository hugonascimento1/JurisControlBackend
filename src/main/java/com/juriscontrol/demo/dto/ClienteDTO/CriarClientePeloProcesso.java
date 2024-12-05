package com.juriscontrol.demo.dto.ClienteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarClientePeloProcesso {
    
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String parte;

}
