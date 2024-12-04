package com.juriscontrol.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.juriscontrol.demo.dto.CredenciaisDTO;
import com.juriscontrol.demo.service.AuthService;


import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para fazer login e retornar o token
    // @PostMapping("/login")
    // public ResponseEntity<?> autenticar(@RequestBody CredenciaisDTO credenciais) {
    //     String email = credenciais.getEmail();
    //     String senha = credenciais.getSenha();

    //     try {
    //         // Chama o serviço de autenticação e gera o token
    //         String token = authService.authenticate(email, senha);
    //         return ResponseEntity.ok(Map.of("token", token));
    //     } catch (RuntimeException e) {
    //         // Caso as credenciais sejam inválidas
    //         return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
    //     }
    // }

    @PostMapping("/login") 
    public ResponseEntity<?> autenticar(@RequestBody CredenciaisDTO credenciais) { 
        System.out.println("Credenciais recebidas: Email - "+ credenciais.getEmail() + ", Senha - "+ credenciais.getSenha());
        // String email = credenciais.getEmail(); 
        // String senha = credenciais.getSenha(); 
        
        if (credenciais.getEmail() == null || credenciais.getSenha() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email e senha são obrigatórios"));
        }
        
        try { 
            // Chama o serviço de autenticação e gera o token 
            String token = authService.authenticate(credenciais); 
            return ResponseEntity.ok(Map.of("token", token)); 
        } catch (RuntimeException e) { 
            // Caso as credenciais sejam inválidas 
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage())); 
        } 
    }
}

