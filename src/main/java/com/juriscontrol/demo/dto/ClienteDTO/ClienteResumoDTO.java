package com.juriscontrol.demo.dto.ClienteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteResumoDTO {
    
    private Long id;
    private String nome;
    private String cpf;

}
