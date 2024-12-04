package com.juriscontrol.demo.service;

import java.util.List;
// import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.CriarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.ListaTudoAdministradorDTO;
// import com.juriscontrol.demo.dto.AdministradorDTO.ListaAdministradorDTO;
import com.juriscontrol.demo.exception.AdministradorNotFoundException;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.repository.AdministradorRepository;

@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    public Administrador criarAdministrador(CriarAdministradorDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(dto.getSenha());
        return administradorRepository.save(administrador);
    }

    public Administrador atualizarAdministrador(Long id, AtualizarAdministradorDTO dto) throws AdministradorNotFoundException {
        Optional<Administrador> opAdministrador = administradorRepository.findById(id);
        if (opAdministrador.isPresent()) {
            Administrador administrador = opAdministrador.get();
            administrador.setNome(dto.getNome());
            administrador.setEmail(dto.getEmail());
            administrador.setSenha(dto.getSenha());
            administrador.setToken(dto.getToken());
            return administradorRepository.save(administrador);
        }
        throw new AdministradorNotFoundException("Administrador não encontrado.");
    }

    public ListaTudoAdministradorDTO buscarPorIdAdministrador(Long id) throws AdministradorNotFoundException {
        Optional<Administrador> opAdministrador = administradorRepository.findById(id);
        if (opAdministrador.isPresent()) {
            Administrador administrador = opAdministrador.get();
            return new ListaTudoAdministradorDTO(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getSenha(),
                administrador.getToken(),
                administrador.getEscritorio() != null ? administrador.getEscritorio().getId() : null
            );
        }
        throw new AdministradorNotFoundException("Administrador não encontrado");
    }

    public List<ListaTudoAdministradorDTO> buscarTodosAdministradores() {
        return administradorRepository.findAll().stream().map(administrador ->
            new ListaTudoAdministradorDTO(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getSenha(),
                administrador.getToken(),
                administrador.getEscritorio() != null ? administrador.getEscritorio().getId() : null // Adicionando escritorioId
            ))
            .collect(Collectors.toList());
    }

    public void deletarAdministrador(Long id) throws AdministradorNotFoundException {
        Optional<Administrador> opAdministrador = administradorRepository.findById(id);
        if (opAdministrador.isPresent()) {
            administradorRepository.delete(opAdministrador.get());
        }
    }

}
