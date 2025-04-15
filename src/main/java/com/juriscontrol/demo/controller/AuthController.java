package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.CredenciaisDTO;
import com.juriscontrol.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody CredenciaisDTO credenciais) {
        System.out.println("Credenciais recebidas: Email - " + credenciais.getEmail());

        if (credenciais.getEmail() == null || credenciais.getSenha() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email e senha são obrigatórios"));
        }

        try {
            String token = authService.authenticate(credenciais);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
}
