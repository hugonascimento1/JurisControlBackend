package com.juriscontrol.demo.service;

import com.juriscontrol.demo.dto.ProcessoDTO.AtualizarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.CriarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    public Processo criarProcesso(CriarProcessoDTO dto) throws AdvogadoNotFoundException {
        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.getNumeroProcesso());
        processo.setVara(dto.getVara());
        processo.setClasseTipo(dto.getClasseTipo());
        processo.setAssuntosTitulo(dto.getAssuntosTitulo());
        processo.setStatus(dto.getStatus());
        processo.setNomeAutor(dto.getNomeAutor());
        processo.setNomeReu(dto.getNomeReu());
        processo.setAdvogadoAutor(dto.getAdvogadoAutor());
        processo.setAdvogadoReu(dto.getAdvogadoReu());

        if (dto.getAdvogadoId() != null) {
            Optional<Advogado> advogadoOptional = advogadoRepository.findById(dto.getAdvogadoId());
            if (advogadoOptional.isPresent()) {
                processo.setAdvogado(advogadoOptional.get()); // Seta a entidade Advogado
            } else {
                throw new AdvogadoNotFoundException("Advogado com ID " + dto.getAdvogadoId() + " não encontrado.");
            }
        }
        return processoRepository.save(processo);
    }

    public Processo atualizarProcesso(Long id, AtualizarProcessoDTO dto) throws ProcessoNotFoundException, AdvogadoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(id);
        if (opProcesso.isPresent()) {
            Processo processo = opProcesso.get();
            processo.setVara(dto.getVara());
            processo.setClasseTipo(dto.getClasseTipo());
            processo.setAssuntosTitulo(dto.getAssuntosTitulo());
            processo.setStatus(dto.getStatus());
            processo.setNomeAutor(dto.getNomeAutor());
            processo.setNomeReu(dto.getNomeReu());
            processo.setAdvogadoAutor(dto.getAdvogadoAutor());
            processo.setAdvogadoReu(dto.getAdvogadoReu());

            if (dto.getAdvogadoId() != null) {
                Optional<Advogado> advogadoOptional = advogadoRepository.findById(dto.getAdvogadoId());
                if (advogadoOptional.isPresent()) {
                    processo.setAdvogado(advogadoOptional.get()); // Atualiza a entidade Advogado
                } else {
                    throw new AdvogadoNotFoundException("Advogado com ID " + dto.getAdvogadoId() + " não encontrado.");
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
                        processo.getNomeAutor(),
                        processo.getAdvogadoAutor(),
                        processo.getNomeReu(),
                        processo.getAdvogadoReu(),
                        processo.getMovimentos() != null ? processo.getMovimentos() : Collections.emptyList(),
                        processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos() : Collections.emptyList(),
                        processo.getAdvogado() != null ? processo.getAdvogado().getId() : null)) // Pega o ID do Advogado
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
                    processo.getNomeAutor(),
                    processo.getAdvogadoAutor(),
                    processo.getNomeReu(),
                    processo.getAdvogadoReu(),
                    processo.getMovimentos() != null ? processo.getMovimentos() : Collections.emptyList(),
                    processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos() : Collections.emptyList(),
                    processo.getAdvogado() != null ? processo.getAdvogado().getId() : null); // Pega o ID do Advogado
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
                    processo.getNomeAutor(),
                    processo.getAdvogadoAutor(),
                    processo.getNomeReu(),
                    processo.getAdvogadoReu(),
                    processo.getMovimentos() != null ? processo.getMovimentos() : Collections.emptyList(),
                    processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos() : Collections.emptyList(),
                    processo.getAdvogado() != null ? processo.getAdvogado().getId() : null); // Pega o ID do Advogado
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