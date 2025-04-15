package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.CredenciaisDTO;
import com.juriscontrol.demo.model.Usuario;
import com.juriscontrol.demo.model.enums.TipoUsuario;
import com.juriscontrol.demo.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuario));
    }

    @PostMapping("/login/administrador")
    public ResponseEntity<Usuario> loginAdministrador(@RequestBody CredenciaisDTO credenciais) {
        Usuario usuario = usuarioService.login(credenciais.getEmail(), credenciais.getSenha(), TipoUsuario.ADMINISTRADOR);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login/advogado")
    public ResponseEntity<Usuario> loginAdvogado(@RequestBody CredenciaisDTO credenciais) {
        Usuario usuario = usuarioService.login(credenciais.getEmail(), credenciais.getSenha(), TipoUsuario.ADVOGADO);
        return ResponseEntity.ok(usuario);
    }
}
