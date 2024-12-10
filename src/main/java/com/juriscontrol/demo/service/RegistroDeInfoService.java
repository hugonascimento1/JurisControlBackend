package com.juriscontrol.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.DocumentoDTO.ListaDocumentoDTO;
import com.juriscontrol.demo.dto.RegistroDeInfoDTO.CriarRegistroDeInfoDTO;
import com.juriscontrol.demo.dto.RegistroDeInfoDTO.ExibirRegistroDeInfoDTO;
import com.juriscontrol.demo.exception.RegistroDeInfoNotFoundException;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.model.RegistroDeInfo;
import com.juriscontrol.demo.repository.ProcessoRepository;
import com.juriscontrol.demo.repository.RegistroDeInfoRepository;

import com.juriscontrol.demo.exception.ProcessoNotFoundException;

@Service
public class RegistroDeInfoService {
    
    @Autowired
    RegistroDeInfoRepository registroDeInfoRepository;

    @Autowired
    ProcessoRepository processoRepository;

    public RegistroDeInfo criarRegistroDeInfo(CriarRegistroDeInfoDTO dto) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(dto.getProcessoId());

        if (opProcesso.isPresent()) {
            RegistroDeInfo registroDeInfo = new RegistroDeInfo();
            registroDeInfo.setProcesso(opProcesso.get());
            registroDeInfo.setData(dto.getData());
            registroDeInfo.setDescricao(dto.getDescricao());

            return registroDeInfoRepository.save(registroDeInfo);
        } else {
            throw new ProcessoNotFoundException("Processo não encontrado");
        }
    }

    public List<ExibirRegistroDeInfoDTO> buscarTodosRegistrosDeInfo() {
        return registroDeInfoRepository.findAll().stream()
            .map(registroDeInfo -> new ExibirRegistroDeInfoDTO(
                registroDeInfo.getData(),
                registroDeInfo.getDescricao(),
                registroDeInfo.getDocumentos() != null ? registroDeInfo.getDocumentos().stream()
                    .map(documento -> new ListaDocumentoDTO(
                        documento.getNomeDocumento(),
                        documento.getAnexo()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()
            ))
            .collect(Collectors.toList());
    }

    public ExibirRegistroDeInfoDTO buscarRegistroDeInfoPorId(Long id) throws RegistroDeInfoNotFoundException {
        Optional<RegistroDeInfo> opRegistroDeInfo = registroDeInfoRepository.findById(id);
        if (opRegistroDeInfo.isPresent()) {
            RegistroDeInfo registroDeInfo = opRegistroDeInfo.get();
            return new ExibirRegistroDeInfoDTO(
                registroDeInfo.getData(),
                registroDeInfo.getDescricao(),
                registroDeInfo.getDocumentos() != null ? registroDeInfo.getDocumentos().stream()
                    .map(documento -> new ListaDocumentoDTO(
                        documento.getNomeDocumento(),
                        documento.getAnexo()
                    ))
                    .collect(Collectors.toList()) : null
            );
        }
        throw new RegistroDeInfoNotFoundException("Registro de informação não encontrado.");
    }

    public void deletarRegistroDeInfo(Long id) throws RegistroDeInfoNotFoundException {
        Optional<RegistroDeInfo> opRegistroDeInfo = registroDeInfoRepository.findById(id);

        if (opRegistroDeInfo.isPresent()) {
            registroDeInfoRepository.delete(opRegistroDeInfo.get());
        } else {
            throw new RegistroDeInfoNotFoundException("Registro de Informação não encontrado");
        }
    }

}