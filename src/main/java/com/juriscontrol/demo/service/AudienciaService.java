package com.juriscontrol.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AudienciaDTO.AtualizarAudienciaDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.CriarAudienciaDTO;
// import com.juriscontrol.demo.dto.AudienciaDTO.ListaAudienciaDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.ListaTudoAudienciaDTO;
import com.juriscontrol.demo.exception.AudienciaNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Audiencia;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.AudienciaRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;
// import com.juriscontrol.demo.exception.ProcessoNotFoundException;;

@Service
public class AudienciaService {
    
    @Autowired
    AudienciaRepository audienciaRepository;

    @Autowired
    ProcessoRepository processoRepository;

    public Audiencia criarAudiencia(CriarAudienciaDTO dto) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(dto.getProcessoId());
    
    // Verifica se o processo existe
        if (opProcesso.isPresent()) {
            Audiencia audiencia = new Audiencia();
            audiencia.setProcesso(opProcesso.get());  // Usa o processo encontrado
            audiencia.setDataHora(dto.getDataHora());
            audiencia.setLocal(dto.getLocal());

            return audienciaRepository.save(audiencia);
        } else {
            throw new ProcessoNotFoundException("Processo não encontrado.");
        }

    }

    public Audiencia atualizarAudiencia(Long id, AtualizarAudienciaDTO dto) throws AudienciaNotFoundException {
        Optional<Audiencia> opAudiencia = audienciaRepository.findById(id);
        if (opAudiencia.isPresent()) {
            Audiencia audiencia = opAudiencia.get();
            audiencia.setDataHora(dto.getDataHora());
            audiencia.setLocal(dto.getLocal());
            audiencia.setResultado(dto.getResultado());
            return audienciaRepository.save(audiencia);
        }
        throw new AudienciaNotFoundException("Audiência não encontrada.");
    }

    public List<ListaTudoAudienciaDTO> buscarTodasAudiencias() {
        return audienciaRepository.findAll().stream()
            .map(audiencia -> new ListaTudoAudienciaDTO(
                audiencia.getId(),
                audiencia.getDataHora(),
                audiencia.getLocal(),
                audiencia.getResultado()
            ))
            .collect(Collectors.toList());
    }

    public ListaTudoAudienciaDTO buscarAuudienciaPorId(Long id) throws AudienciaNotFoundException {
        Optional<Audiencia> opAudiencia = audienciaRepository.findById(id);
        if (opAudiencia.isPresent()) {
            Audiencia audiencia = opAudiencia.get();
            return new ListaTudoAudienciaDTO(
                audiencia.getId(),
                audiencia.getDataHora(),
                audiencia.getLocal(),
                audiencia.getResultado()
            );
        }
        throw new AudienciaNotFoundException("Audiência não encontrada.");
    }

    public void deletarAudiencia(Long id) throws AudienciaNotFoundException {
        Optional<Audiencia> opAudiencia = audienciaRepository.findById(id);
        if (opAudiencia.isPresent()) {
            audienciaRepository.delete(opAudiencia.get());
        } else {
            throw new AudienciaNotFoundException("Audiencia não encontrada.");
        }
    }

}
