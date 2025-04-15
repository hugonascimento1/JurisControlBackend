package com.juriscontrol.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.CadastroCompletoAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.CriarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.ListaTudoAdministradorDTO;
import com.juriscontrol.demo.exception.AdministradorNotFoundException;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.model.Usuario;
import com.juriscontrol.demo.model.enums.TipoUsuario;
import com.juriscontrol.demo.repository.AdministradorRepository;
import com.juriscontrol.demo.repository.UsuarioRepository;

@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;

     @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarCompleto(CadastroCompletoAdministradorDTO dto) {
        // Cria usuário primeiro
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(TipoUsuario.ADMINISTRADOR);
        usuarioRepository.save(usuario);

        // Cria administrador associado ao usuário
        Administrador administrador = new Administrador();
        administrador.setCnpj(dto.getCnpj());
        administrador.setTelefone(dto.getTelefone());
        administrador.setUsuario(usuario);

        administradorRepository.save(administrador);
    }

    public Administrador criarAdministrador(CriarAdministradorDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(dto.getSenha());
        return administradorRepository.save(administrador);
    }

    public Administrador atualizarAdministrador(Long id, AtualizarAdministradorDTO dto) throws AdministradorNotFoundException {
        Administrador administrador = administradorRepository.findById(id)
            .orElseThrow(() -> new AdministradorNotFoundException("Administrador não encontrado."));
        
        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(dto.getSenha());
        return administradorRepository.save(administrador);
    }

    public ListaTudoAdministradorDTO buscarPorIdAdministrador(Long id) throws AdministradorNotFoundException {
        Administrador administrador = administradorRepository.findById(id)
            .orElseThrow(() -> new AdministradorNotFoundException("Administrador não encontrado."));

        return new ListaTudoAdministradorDTO(
            administrador.getId(),
            administrador.getNome(),
            administrador.getEmail(),
            administrador.getSenha()
        );
    }

    public List<ListaTudoAdministradorDTO> buscarTodosAdministradores() {
        return administradorRepository.findAll().stream().map(administrador ->
            new ListaTudoAdministradorDTO(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getSenha()
            )).collect(Collectors.toList());
    }

    public void deletarAdministrador(Long id) throws AdministradorNotFoundException {
        Administrador administrador = administradorRepository.findById(id)
            .orElseThrow(() -> new AdministradorNotFoundException("Administrador não encontrado."));
        administradorRepository.delete(administrador);
    }
}
