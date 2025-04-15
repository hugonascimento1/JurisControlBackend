package com.juriscontrol.demo.service;

import com.juriscontrol.demo.dto.MovimentoDTO.AtualizarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.CriarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.MovimentoDTO;
import com.juriscontrol.demo.exception.MovimentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Movimento;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.MovimentoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public MovimentoDTO criarMovimentacao(CriarMovimentoDTO dto) throws ProcessoNotFoundException {
        Movimento novoMovimento = new Movimento();
        novoMovimento.setNomeMovimento(dto.getNomeMovimento());
        novoMovimento.setTipo(dto.getTipo());
        novoMovimento.setData(dto.getData());

        if (dto.getProcessoId() != null) {
            Processo processo = processoRepository.findById(dto.getProcessoId())
                    .orElseThrow(() -> new ProcessoNotFoundException("Processo com ID " + dto.getProcessoId() + " não encontrado."));
            novoMovimento.setProcesso(processo);
        }

        Movimento movimentoSalvo = movimentoRepository.save(novoMovimento);
        return new MovimentoDTO(movimentoSalvo);
    }

    public MovimentoDTO buscarMovimentacaoPorId(Long id) throws MovimentoNotFoundException {
        return movimentoRepository.findById(id)
                .map(MovimentoDTO::new)
                .orElseThrow(() -> new MovimentoNotFoundException("Movimento com ID " + id + " não encontrado."));
    }

    public List<MovimentoDTO> buscarTudoMovimentacao() {
        List<Movimento> movimentos = movimentoRepository.findAll();
        return movimentos.stream()
                .map(MovimentoDTO::new)
                .collect(Collectors.toList());
    }

    public MovimentoDTO atualizarMovimentacao(Long id, AtualizarMovimentoDTO dto) throws MovimentoNotFoundException, ProcessoNotFoundException {
        Optional<Movimento> movimentoOptional = movimentoRepository.findById(id);

        if (movimentoOptional.isPresent()) {
            Movimento movimento = movimentoOptional.get();
            movimento.setNomeMovimento(dto.getNomeMovimento());
            movimento.setTipo(dto.getTipo());
            movimento.setData(dto.getData());

            if (dto.getProcessoId() != null) {
                Processo processo = processoRepository.findById(dto.getProcessoId())
                        .orElseThrow(() -> new ProcessoNotFoundException("Processo com ID " + dto.getProcessoId() + " não encontrado."));
                movimento.setProcesso(processo);
            } else {
                movimento.setProcesso(null); // Permite desassociar o movimento de um processo, se necessário
            }

            Movimento movimentoAtualizado = movimentoRepository.save(movimento);
            return new MovimentoDTO(movimentoAtualizado);
        } else {
            throw new MovimentoNotFoundException("Movimento com ID " + id + " não encontrado.");
        }
    }

    public void deletarMovimentacao(Long id) throws MovimentoNotFoundException {
        if (movimentoRepository.existsById(id)) {
            movimentoRepository.deleteById(id);
        } else {
            throw new MovimentoNotFoundException("Movimento com ID " + id + " não encontrado.");
        }
    }
}