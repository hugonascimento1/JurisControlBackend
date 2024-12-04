package com.juriscontrol.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.CredenciaisDTO;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Cliente;
import com.juriscontrol.demo.repository.AdministradorRepository;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.ClienteRepository;
import com.juriscontrol.demo.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // public String authenticate(String email, String senha) {
    //     System.out.println("Tentando autenticar o email: " + email);
        
    //     Optional<Administrador> administrador = administradorRepository.findByEmail(email);
    //     if (administrador.isPresent()) {
    //         System.out.println("Administrador encontrado: " + administrador.get().getEmail());
    //         if (passwordEncoder.matches(senha, administrador.get().getSenha())) {
    //             System.out.println("Senha correta para Administrador");
    //             return jwtUtil.gerarToken(administrador.get().getEmail());
    //         } else {
    //             System.out.println("Senha incorreta para Administrador");
    //         }
    //     } else {
    //         System.out.println("Administrador não encontrado");
    //     }
    
    //     Optional<Cliente> cliente = clienteRepository.findByEmail(email);
    //     if (cliente.isPresent()) {
    //         System.out.println("Cliente encontrado: " + cliente.get().getEmail());
    //         if (passwordEncoder.matches(senha, cliente.get().getSenha())) {
    //             System.out.println("Senha correta para Cliente");
    //             return jwtUtil.gerarToken(cliente.get().getEmail());
    //         } else {
    //             System.out.println("Senha incorreta para Cliente");
    //         }
    //     } else {
    //         System.out.println("Cliente não encontrado");
    //     }
    
    //     Optional<Advogado> advogado = advogadoRepository.findByEmail(email);
    //     if (advogado.isPresent()) {
    //         System.out.println("Advogado encontrado: " + advogado.get().getEmail());
    //         if (passwordEncoder.matches(senha, advogado.get().getSenha())) {
    //             System.out.println("Senha correta para Advogado");
    //             return jwtUtil.gerarToken(advogado.get().getEmail());
    //         } else {
    //             System.out.println("Senha incorreta para Advogado");
    //         }
    //     } else {
    //         System.out.println("Advogado não encontrado");
    //     }
    
    //     throw new RuntimeException("Credenciais inválidas");
    // }

    // testando sem a verificação de senha codificada
    public String authenticate(CredenciaisDTO credenciais) {
        String email = credenciais.getEmail();
        String senha = credenciais.getSenha();

        System.out.println("Tentanto autenticar o email: "+ email);

        Optional<Administrador> administrador = administradorRepository.findByEmail(email);
        if (administrador.isPresent()) {
            System.out.println("Administrador encontrado: "+ administrador.get().getEmail());
            if (senha.equals(administrador.get().getSenha())) {
                System.out.println("Senha correta para administrador");
                return jwtUtil.gerarToken(administrador.get().getEmail());
            } else {
                System.out.println("Senha incorreta para administrador");
            } 
        } else {
            System.out.println("Administrador não encontrado");
        }

        Optional<Cliente> cliente = clienteRepository.findByEmail(email); 
        if (cliente.isPresent()) { 
            System.out.println("Cliente encontrado: " + cliente.get().getEmail()); 
            if (senha.equals(cliente.get().getSenha())) { 
                System.out.println("Senha correta para Cliente"); 
                return jwtUtil.gerarToken(cliente.get().getEmail()); 
            } else { 
                System.out.println("Senha incorreta para Cliente"); 
            } 
        } else { 
            System.out.println("Cliente não encontrado"); 
        } 
        
        Optional<Advogado> advogado = advogadoRepository.findByEmail(email); 
        if (advogado.isPresent()) { 
            System.out.println("Advogado encontrado: " + advogado.get().getEmail()); 
            if (senha.equals(advogado.get().getSenha())) { 
                System.out.println("Senha correta para Advogado"); 
                return jwtUtil.gerarToken(advogado.get().getEmail()); 
            } else { 
                System.out.println("Senha incorreta para Advogado"); 
            } 
        } else { 
            System.out.println("Advogado não encontrado"); 
        }

        throw new RuntimeException("Credenciais inválidas.");
    }


    
}