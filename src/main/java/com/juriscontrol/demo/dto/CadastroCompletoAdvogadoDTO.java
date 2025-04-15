package com.juriscontrol.demo.dto;

import com.juriscontrol.demo.model.enums.TipoUsuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroCompletoAdvogadoDTO {
    // dados do usuário
    private String email;
    private String senha;
    private TipoUsuario tipo = TipoUsuario.ADVOGADO;

    // dados do advogado
    private String registroOAB;
}
