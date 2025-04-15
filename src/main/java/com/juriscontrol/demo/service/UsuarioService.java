package com.juriscontrol.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.model.Usuario;
import com.juriscontrol.demo.model.enums.TipoUsuario;
import com.juriscontrol.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String senha, TipoUsuario tipo) {
        return usuarioRepository.findByEmailAndSenhaAndTipo(email, senha, tipo)
            .orElseThrow(() -> new RuntimeException("Credenciais inválidas ou tipo de usuário incorreto"));
    }

    public Usuario login(String email, String senha) {
        return usuarioRepository.findAll().stream()
            .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}

