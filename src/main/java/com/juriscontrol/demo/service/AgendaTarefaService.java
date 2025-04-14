package com.juriscontrol.demo.service;

import com.juriscontrol.demo.dto.AgendaTarefaDTO.CriarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AtualizarAgendaTarefaDTO;
import com.juriscontrol.demo.model.AgendaTarefa;
import com.juriscontrol.demo.repository.AgendaTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaTarefaService {

    @Autowired
    private AgendaTarefaRepository agendaTarefaRepository;

    public AgendaTarefaDTO verTarefaAgenda(Long id) {
        return agendaTarefaRepository.findById(id)
                .map(AgendaTarefaDTO::new)
                .orElse(null);
    }

    public AgendaTarefaDTO adicionarTarefaAgenda(CriarAgendaTarefaDTO dto) {
        AgendaTarefa novaTarefa = dto.toEntity();
        AgendaTarefa tarefaSalva = agendaTarefaRepository.save(novaTarefa);
        return new AgendaTarefaDTO(tarefaSalva);
    }

    public AgendaTarefaDTO atualizarTarefaAgenda(Long id, AtualizarAgendaTarefaDTO dto) {
        Optional<AgendaTarefa> tarefaOptional = agendaTarefaRepository.findById(id);
        
        if (tarefaOptional.isPresent()) {
            AgendaTarefa tarefa = tarefaOptional.get();
            dto.updateEntity(tarefa);
            AgendaTarefa tarefaAtualizada = agendaTarefaRepository.save(tarefa);
            return new AgendaTarefaDTO(tarefaAtualizada);
        }
        
        return null;
    }

    public boolean deletarTarefaAgenda(Long id) {
        if (agendaTarefaRepository.existsById(id)) {
            agendaTarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AgendaTarefaDTO buscarTarefaPorId(Long id) {
        return agendaTarefaRepository.findById(id)
                .map(AgendaTarefaDTO::new)
                .orElse(null);
    }

    public List<AgendaTarefaDTO> buscarTodasTarefa() {
        List<AgendaTarefa> tarefas = agendaTarefaRepository.findAll();
        return AgendaTarefaDTO.fromEntityList(tarefas);
    }
}