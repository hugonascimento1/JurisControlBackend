package com.juriscontrol.demo.service;

import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.CadastroCompletoAdvogadoDTO;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Usuario;
import com.juriscontrol.demo.model.enums.TipoUsuario;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.UsuarioRepository;

@Service
public class AdvogadoService {

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarCompleto(CadastroCompletoAdvogadoDTO dto) {
        // Cria usuário primeiro
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(TipoUsuario.ADVOGADO);
        usuarioRepository.save(usuario);

        // Cria advogado associado ao usuário
        Advogado advogado = new Advogado();
        advogado.setRegistroOAB(dto.getRegistroOAB());
        advogado.setUsuario(usuario);

        advogadoRepository.save(advogado);
    }

    public List<Advogado> listarAdvogados() {
        return advogadoRepository.findAll();
    }

    public Advogado buscarPorId(Long id) {
        return advogadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));
    }

    public Advogado criarAdvogado(Advogado advogado) {
        return advogadoRepository.save(advogado);
    }

    public Advogado atualizarAdvogado(Long id, Advogado dadosAtualizados) {
        Advogado advogado = buscarPorId(id);
        advogado.setNome(dadosAtualizados.getNome());
        advogado.setEmail(dadosAtualizados.getEmail());
        advogado.setSenha(dadosAtualizados.getSenha());
        return advogadoRepository.save(advogado);
    }

    public void deletarAdvogado(Long id) {
        advogadoRepository.deleteById(id);
    }
}
