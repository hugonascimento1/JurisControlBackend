package com.juriscontrol.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AdvogadoDTO.AdvogadoResumoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.AtualizarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.CriarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaTudoAdvogadoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ProcessoResumoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.repository.AdvogadoRepository;

@Service
public class AdvogadoService {
    
    @Autowired
    private AdvogadoRepository advogadoRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    public Advogado criarAdvogado(CriarAdvogadoDTO dto) {
        Advogado advogado = new Advogado();
        advogado.setNome(dto.getNome());
        advogado.setRegistroOAB(dto.getRegistroOAB());
        advogado.setSenha(dto.getToken());
        return advogadoRepository.save(advogado);
    }

    public Advogado atualizarAdvogado(Long id, AtualizarAdvogadoDTO dto) throws AdvogadoNotFoundException {
        Optional<Advogado> opAdvogado = advogadoRepository.findById(id);
        if (opAdvogado.isPresent()) {
            Advogado advogado = opAdvogado.get();
            advogado.setNome(dto.getNome());
            advogado.setRegistroOAB(dto.getRegistroOAB());
            advogado.setSenha(dto.getToken());
            return advogadoRepository.save(advogado);
        }
        throw new AdvogadoNotFoundException("Advogado não encontrado.");

    }

    public ListaTudoAdvogadoDTO buscarPorIdAdvogado(Long id) throws AdvogadoNotFoundException {
        Optional<Advogado> opAdvogado = advogadoRepository.findById(id);
        if (opAdvogado.isPresent()) {
            Advogado advogado = opAdvogado.get();

            List<ProcessoResumoDTO> processosComoAutor = advogado.getProcessosComoAutor().stream()
                .map(processo -> new ProcessoResumoDTO(processo.getNumeroProcesso(), processo.getDescricao()))
                .collect(Collectors.toList());

            List<ProcessoResumoDTO> processosComoReu = advogado.getProcessosComoReu().stream()
                .map(processo -> new ProcessoResumoDTO(processo.getNumeroProcesso(), processo.getDescricao()))
                .collect(Collectors.toList());
                
            return new ListaTudoAdvogadoDTO(
                advogado.getId(),
                advogado.getNome(),
                advogado.getRegistroOAB(),
                advogado.getSenha(),
                processosComoAutor,
                processosComoReu
            );

        }
        throw new AdvogadoNotFoundException("Advogado não encontrado");
    }

    public List<ListaTudoAdvogadoDTO> buscarTodosAdvogados() {
        return advogadoRepository.findAll().stream().map(advogado -> {
            List<ProcessoResumoDTO> processosComoAutor = advogado.getProcessosComoAutor().stream()
                .map(processo -> new ProcessoResumoDTO(processo.getNumeroProcesso(), processo.getDescricao()))
                .collect(Collectors.toList());

            List<ProcessoResumoDTO> processosComoReu = advogado.getProcessosComoReu().stream()
                .map(processo -> new ProcessoResumoDTO(processo.getNumeroProcesso(), processo.getDescricao()))
                .collect(Collectors.toList());    

                return new ListaTudoAdvogadoDTO(
                    advogado.getId(),
                    advogado.getNome(),
                    advogado.getRegistroOAB(),
                    advogado.getSenha(),
                    processosComoAutor,
                    processosComoReu
                );
        }).collect(Collectors.toList());
        
    }

    public AdvogadoResumoDTO buscarAdvogadoResumoPorId(Long id) throws AdvogadoNotFoundException {
        Optional<Advogado> opAdvogado = advogadoRepository.findById(id);
        if (opAdvogado.isPresent()) {
            Advogado advogado = opAdvogado.get();
            return new AdvogadoResumoDTO(
                advogado.getId(),
                advogado.getNome(),
                advogado.getRegistroOAB()
            );
        }
        throw new AdvogadoNotFoundException("Advogado não encontrado!");
    }

    public void deletarAdvogado(Long id) throws AdvogadoNotFoundException {
        Optional<Advogado> opAdvogado = advogadoRepository.findById(id);
        if (opAdvogado.isPresent()) {
            advogadoRepository.delete(opAdvogado.get());
        } else {
            throw new AdvogadoNotFoundException("Advogado não encontrado.");
        }
    }

}
