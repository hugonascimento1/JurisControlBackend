package com.juriscontrol.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.CredenciaisDTO;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Usuario;
// import com.juriscontrol.demo.repository.AdministradorRepository;
// import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.UsuarioRepository;
import com.juriscontrol.demo.security.JwtUtil;

@Service
public class AuthService {

    // @Autowired
    // private AdministradorRepository administradorRepository;

    // @Autowired
    // private AdvogadoRepository advogadoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String authenticate(CredenciaisDTO credenciais) {
        String email = credenciais.getEmail();
        String senha = credenciais.getSenha();

        System.out.println("Tentando autenticar o email: " + email);

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            if (passwordEncoder.matches(senha, usuario.get().getSenha())) {
                if (usuario.get() instanceof Administrador) {
                    System.out.println("Login como Administrador");
                    return jwtUtil.gerarToken(usuario.get().getEmail());
                } else if (usuario.get() instanceof Advogado) {
                    System.out.println("Login como Advogado");
                    return jwtUtil.gerarToken(usuario.get().getEmail());
                }
            } else {
                throw new RuntimeException("Senha incorreta.");
            }
        }

        // Tentativa de login como Administrador
        // Optional<Administrador> administrador =
        // administradorRepository.findByEmail(email);
        // if (administrador.isPresent()) {
        // if (passwordEncoder.matches(senha, administrador.get().getSenha())) {
        // System.out.println("Login bem-sucedido como Administrador");
        // return jwtUtil.gerarToken(administrador.get().getEmail());
        // } else {
        // System.out.println("Senha incorreta para Administrador");
        // throw new RuntimeException("Credenciais inválidas.");
        // }
        // }

        // // Tentativa de login como Advogado
        // Optional<Advogado> advogado = advogadoRepository.findByEmail(email);
        // if (advogado.isPresent()) {
        // if (passwordEncoder.matches(senha, advogado.get().getSenha())) {
        // System.out.println("Login bem-sucedido como Advogado");
        // return jwtUtil.gerarToken(advogado.get().getEmail());
        // } else {
        // System.out.println("Senha incorreta para Advogado");
        // throw new RuntimeException("Credenciais inválidas.");
        // }
        // }

        // Nenhum usuário encontrado
        System.out.println("Usuário não encontrado");
        throw new RuntimeException("Credenciais inválidas.");
    }
}
