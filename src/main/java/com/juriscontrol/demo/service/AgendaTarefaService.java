package com.juriscontrol.demo.service;

import com.juriscontrol.demo.dto.AgendaTarefaDTO.AtualizarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.CriarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AgendaTarefaDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.AgendaTarefaNotFoundException;
import com.juriscontrol.demo.model.AgendaTarefa;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.repository.AgendaTarefaRepository;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaTarefaService {

    @Autowired
    private AgendaTarefaRepository agendaTarefaRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    public AgendaTarefaDTO verTarefaAgenda(Long id) throws AgendaTarefaNotFoundException {
        return agendaTarefaRepository.findById(id)
                .map(AgendaTarefaDTO::new)
                .orElseThrow(() -> new AgendaTarefaNotFoundException("Tarefa com ID " + id + " não encontrada."));
    }

    public AgendaTarefaDTO adicionarTarefaAgenda(CriarAgendaTarefaDTO dto) throws AdvogadoNotFoundException {
        AgendaTarefa novaTarefa = new AgendaTarefa();
        novaTarefa.setTitulo(dto.getTitulo());
        novaTarefa.setDescricao(dto.getDescricao());
        novaTarefa.setData(dto.getData());

        if (dto.getAdvogadoId() != null) {
            Advogado advogado = advogadoRepository.findById(dto.getAdvogadoId())
                    .orElseThrow(() -> new AdvogadoNotFoundException("Advogado com ID " + dto.getAdvogadoId() + " não encontrado."));
            novaTarefa.setAdvogado(advogado);
        }

        AgendaTarefa tarefaSalva = agendaTarefaRepository.save(novaTarefa);
        return new AgendaTarefaDTO(tarefaSalva);
    }

    public AgendaTarefaDTO atualizarTarefaAgenda(Long id, AtualizarAgendaTarefaDTO dto)
            throws AgendaTarefaNotFoundException, AdvogadoNotFoundException {
        Optional<AgendaTarefa> tarefaOptional = agendaTarefaRepository.findById(id);

        if (tarefaOptional.isPresent()) {
            AgendaTarefa tarefa = tarefaOptional.get();
            tarefa.setTitulo(dto.getTitulo());
            tarefa.setDescricao(dto.getDescricao());
            tarefa.setData(dto.getData());

            if (dto.getAdvogadoId() != null) {
                Advogado advogado = advogadoRepository.findById(dto.getAdvogadoId())
                        .orElseThrow(() -> new AdvogadoNotFoundException("Advogado com ID " + dto.getAdvogadoId() + " não encontrado."));
                tarefa.setAdvogado(advogado);
            } else {
                tarefa.setAdvogado(null);
            }

            AgendaTarefa tarefaAtualizada = agendaTarefaRepository.save(tarefa);
            return new AgendaTarefaDTO(tarefaAtualizada);
        } else {
            throw new AgendaTarefaNotFoundException("Tarefa com ID " + id + " não encontrada.");
        }
    }

    public void deletarTarefaAgenda(Long id) throws AgendaTarefaNotFoundException {
        if (agendaTarefaRepository.existsById(id)) {
            agendaTarefaRepository.deleteById(id);
        } else {
            throw new AgendaTarefaNotFoundException("Tarefa com ID " + id + " não encontrada.");
        }
    }

    public AgendaTarefaDTO buscarTarefaPorId(Long id) throws AgendaTarefaNotFoundException {
        return agendaTarefaRepository.findById(id)
                .map(AgendaTarefaDTO::new)
                .orElseThrow(() -> new AgendaTarefaNotFoundException("Tarefa com ID " + id + " não encontrada."));
    }

    public List<AgendaTarefaDTO> buscarTodasTarefa() {
        List<AgendaTarefa> tarefas = agendaTarefaRepository.findAll();
        return tarefas.stream()
                .map(AgendaTarefaDTO::new)
                .collect(Collectors.toList());
    }
}