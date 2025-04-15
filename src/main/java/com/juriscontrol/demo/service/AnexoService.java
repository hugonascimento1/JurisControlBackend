package com.juriscontrol.demo.service;

import com.juriscontrol.demo.dto.AnexoDTO.CriarAnexoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.CriarVariosAnexosDTO;
import com.juriscontrol.demo.dto.AnexoDTO.ListaTudoAnexoDTO;
import com.juriscontrol.demo.exception.AnexoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Anexo;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.AnexoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public Anexo criarAnexo(CriarAnexoDTO anexoDTO) throws ProcessoNotFoundException, IOException {
        Optional<Processo> opProcesso = processoRepository.findById(anexoDTO.getProcessoId());

        if (opProcesso.isEmpty()) {
            throw new ProcessoNotFoundException("Processo não encontrado.");
        }

        MultipartFile arquivo = anexoDTO.getAnexo();
        if (arquivo == null || arquivo.isEmpty()) {
            throw new IOException("O arquivo anexado está vazio.");
        }

        Anexo anexo = new Anexo();
        anexo.setProcesso(opProcesso.get());
        anexo.setNomeAnexo(anexoDTO.getNomeAnexo() != null ? anexoDTO.getNomeAnexo() : arquivo.getOriginalFilename());
        anexo.setAnexo(arquivo.getBytes());

        // Se você realmente precisa salvar tipo e tamanho, pode obtê-los aqui:
        // anexo.setTipoAnexo(arquivo.getContentType());
        // anexo.setTamAnexo(arquivo.getSize());

        return anexoRepository.save(anexo);
    }

    public List<Anexo> criarVariosAnexos(CriarVariosAnexosDTO anexosDTO) throws ProcessoNotFoundException, IOException {
        Optional<Processo> opProcesso = processoRepository.findById(anexosDTO.getProcessoId());

        if (opProcesso.isEmpty()) {
            throw new ProcessoNotFoundException("Processo não encontrado.");
        }

        List<Anexo> anexosSalvos = new ArrayList<>();
        for (MultipartFile arquivo : anexosDTO.getAnexos()) {
            if (arquivo != null && !arquivo.isEmpty()) {
                Anexo anexo = new Anexo();
                anexo.setProcesso(opProcesso.get());
                anexo.setNomeAnexo(anexosDTO.getNomeAnexoBase() != null ?
                        anexosDTO.getNomeAnexoBase() + "-" + arquivo.getOriginalFilename() :
                        arquivo.getOriginalFilename());
                anexo.setAnexo(arquivo.getBytes());

                // Se você realmente precisa salvar tipo e tamanho, pode obtê-los aqui:
                // anexo.setTipoAnexo(arquivo.getContentType());
                // anexo.setTamAnexo(arquivo.getSize());

                anexosSalvos.add(anexoRepository.save(anexo));
            }
        }
        return anexosSalvos;
    }

    public ListaTudoAnexoDTO buscarAnexoPorId(Long id) throws AnexoNotFoundException {
        Optional<Anexo> opAnexo = anexoRepository.findById(id);

        if (opAnexo.isEmpty()) {
            throw new AnexoNotFoundException(" fmfmfmfm");
        }

        Anexo anexo = opAnexo.get();
        return new ListaTudoAnexoDTO(
                anexo.getId(),
                anexo.getNomeAnexo(),
                anexo.getAnexo(),
                anexo.getProcesso() != null ? anexo.getProcesso().getId() : null // Incluindo o processoId
        );
    }

    public List<ListaTudoAnexoDTO> buscarTodosAnexosPorProcesso(Long processoId) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(processoId);
        if (opProcesso.isEmpty()) {
            throw new ProcessoNotFoundException("Processo não encontrado.");
        }
        List<Anexo> anexos = anexoRepository.findByProcesso(opProcesso.get());
        return anexos.stream()
                .map(anexo -> new ListaTudoAnexoDTO(
                        anexo.getId(),
                        anexo.getNomeAnexo(),
                        anexo.getAnexo(),
                        anexo.getProcesso().getId()
                ))
                .collect(Collectors.toList());
    }

    public void deletarAnexo(Long id) throws AnexoNotFoundException {
        Optional<Anexo> opAnexo = anexoRepository.findById(id);

        if (opAnexo.isEmpty()) {
            throw new AnexoNotFoundException(".");
        }

        anexoRepository.delete(opAnexo.get());
    }
}