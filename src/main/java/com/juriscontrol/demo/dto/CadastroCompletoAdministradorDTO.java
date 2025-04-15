package com.juriscontrol.demo.dto;

import com.juriscontrol.demo.model.enums.TipoUsuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroCompletoAdministradorDTO {
    private String email;
    private String senha;
    private TipoUsuario tipo = TipoUsuario.ADMINISTRADOR;

    // dados do administrador
    private String cnpj;
    private String telefone;
}