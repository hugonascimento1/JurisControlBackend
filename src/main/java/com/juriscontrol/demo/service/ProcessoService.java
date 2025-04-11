package com.juriscontrol.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AdvogadoDTO.AdvogadoResumoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaAdvogadoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.AtualizarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.CriarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.ProcessoRepository;

@Service
public class ProcessoService {
    
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AdvogadoService advogadoService;

    public Processo criarProcesso(CriarProcessoDTO dto) throws AdvogadoNotFoundException {
        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.getNumeroProcesso());
        processo.setVara(dto.getVara());
        processo.setClasseTipo(dto.getClasseTipo());
        processo.setAssuntosTitulo(dto.getAssuntosTitulo());
        processo.setStatus(dto.getStatus());
        processo.setAutor(dto.getAutor());
        processo.setReu(dto.getReu());
        
        // Buscar advogados
        AdvogadoResumoDTO advogadoAutorDTO = advogadoService.buscarAdvogadoResumoPorId(dto.getAdvogadoAutorId());
        AdvogadoResumoDTO advogadoReuDTO = advogadoService.buscarAdvogadoResumoPorId(dto.getAdvogadoReuId());

        Advogado advogadoAutor = new Advogado();
        advogadoAutor.setId(advogadoAutorDTO.getId());
        advogadoAutor.setNome(advogadoAutorDTO.getNome());
        advogadoAutor.setRegistroOAB(advogadoAutorDTO.getRegistroOAB());

        Advogado advogadoReu = new Advogado();
        advogadoReu.setId(advogadoReuDTO.getId());
        advogadoReu.setNome(advogadoReuDTO.getNome());
        advogadoReu.setRegistroOAB(advogadoReuDTO.getRegistroOAB());

        processo.setAdvogadoAutor(advogadoAutor);
        processo.setAdvogadoReu(advogadoReu);
        
        // Nota: A lista de movimentos deve ser inicializada ou atribuída conforme necessário
        // A lista de anexos seria gerenciada por outro serviço

        return processoRepository.save(processo);
    }

    public Processo atualizarProcesso(Long id, AtualizarProcessoDTO dto) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(id);
        if (opProcesso.isPresent()) {
            Processo processo = opProcesso.get();
            processo.setVara(dto.getVara());
            processo.setClasseTipo(dto.getClasseTipo());
            processo.setAssuntosTitulo(dto.getAssuntosTitulo());
            processo.setStatus(dto.getStatus());
            processo.setAdvogadoAutorId(dto.getAdvogadoAutorId());
            processo.setAdvogadoReuId(dto.getAdvogadoReuId());
            
            // Atualizar advogados se necessário
            if (dto.getAdvogadoAutorId() != null) {
                try {
                    AdvogadoResumoDTO advogadoAutorDTO = advogadoService.buscarAdvogadoResumoPorId(dto.getAdvogadoAutorId());
                    Advogado advogadoAutor = new Advogado();
                    advogadoAutor.setId(advogadoAutorDTO.getId());
                    advogadoAutor.setNome(advogadoAutorDTO.getNome());
                    advogadoAutor.setRegistroOAB(advogadoAutorDTO.getRegistroOAB());
                    processo.setAdvogadoAutor(advogadoAutor);
                } catch (AdvogadoNotFoundException e) {
                    // Tratamento de erro
                }
            }
            
            if (dto.getAdvogadoReuId() != null) {
                try {
                    AdvogadoResumoDTO advogadoReuDTO = advogadoService.buscarAdvogadoResumoPorId(dto.getAdvogadoReuId());
                    Advogado advogadoReu = new Advogado();
                    advogadoReu.setId(advogadoReuDTO.getId());
                    advogadoReu.setNome(advogadoReuDTO.getNome());
                    advogadoReu.setRegistroOAB(advogadoReuDTO.getRegistroOAB());
                    processo.setAdvogadoReu(advogadoReu);
                } catch (AdvogadoNotFoundException e) {
                    // Tratamento de erro
                }
            }
            
            return processoRepository.save(processo);
        }
        throw new ProcessoNotFoundException("Processo não encontrado.");
    }

    public List<ListaTudoProcessoDTO> listarTodosProcessos() {
        List<Processo> processos = processoRepository.findAll();

        return processos.stream()
            .map(processo -> new ListaTudoProcessoDTO(
                processo.getId(),
                processo.getNumeroProcesso(),
                processo.getVara(),
                processo.getClasseTipo(),
                processo.getAssuntosTitulo(),
                processo.getStatus(),
                processo.getAutor(),
                processo.getAdvogadoAutor() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoAutor().getId(),
                    processo.getAdvogadoAutor().getNome(),
                    processo.getAdvogadoAutor().getRegistroOAB()
                ) : null,
                processo.getReu(),
                processo.getAdvogadoReu() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoReu().getId(),
                    processo.getAdvogadoReu().getNome(),
                    processo.getAdvogadoReu().getRegistroOAB()
                ) : null,
                // Mapear anexos se necessário
                processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos().stream()
                    .map(anexo -> new ListaAnexoDTO(
                        anexo.getId(),
                        anexo.getNome(),
                        anexo.getCaminho()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()
            ))
            .collect(Collectors.toList());
    }
    
    public ListaTudoProcessoDTO buscarProcessoPorId(Long id) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(id);

        if (opProcesso.isPresent()) {
            Processo processo = opProcesso.get();
            return new ListaTudoProcessoDTO(
                processo.getId(),
                processo.getNumeroProcesso(),
                processo.getVara(),
                processo.getClasseTipo(),
                processo.getAssuntosTitulo(),
                processo.getStatus(),
                processo.getAutor(),
                processo.getAdvogadoAutor() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoAutor().getId(),
                    processo.getAdvogadoAutor().getNome(),
                    processo.getAdvogadoAutor().getRegistroOAB()
                ) : null,
                processo.getReu(),
                processo.getAdvogadoReu() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoReu().getId(),
                    processo.getAdvogadoReu().getNome(),
                    processo.getAdvogadoReu().getRegistroOAB()
                ) : null,
                // Mapear anexos se necessário
                processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos().stream()
                    .map(anexo -> new ListaAnexoDTO(
                        anexo.getId(),
                        anexo.getNome(),
                        anexo.getCaminho()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()
            );
        }
        throw new ProcessoNotFoundException("Processo não encontrado");
    }
    public ListaTudoProcessoDTO buscarPorNumeroDeProcesso(String numeroProcesso) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findByNumeroProcesso(numeroProcesso);
        
        if (opProcesso.isPresent()) {
            Processo processo = opProcesso.get();
            return new ListaTudoProcessoDTO(
                processo.getId(),
                processo.getNumeroProcesso(),
                processo.getVara(),
                processo.getClasseTipo(),
                processo.getAssuntosTitulo(),
                processo.getStatus(),
                processo.getAutor(),
                processo.getAdvogadoAutor() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoAutor().getId(),
                    processo.getAdvogadoAutor().getNome(),
                    processo.getAdvogadoAutor().getRegistroOAB()
                ) : null,
                processo.getReu(),
                processo.getAdvogadoReu() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoReu().getId(),
                    processo.getAdvogadoReu().getNome(),
                    processo.getAdvogadoReu().getRegistroOAB()
                ) : null,
                // Mapear anexos si es necesario
                processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos().stream()
                    .map(anexo -> new ListaAnexoDTO(
                        anexo.getId(),
                        anexo.getNome(),
                        anexo.getCaminho()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()
            );
        }
        throw new ProcessoNotFoundException("Processo com número " + numeroProcesso + " não encontrado");
    }
    public void deletarProcesso(Long id) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(id);
        if (opProcesso.isPresent()) {
            processoRepository.delete(opProcesso.get());
        } else {
            throw new ProcessoNotFoundException("Processo não encontrado.");
        }
    }
}