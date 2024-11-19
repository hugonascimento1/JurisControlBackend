package com.juriscontrol.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AdvogadoDTO.AdvogadoResumoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaTudoAdvogadoDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.ListaAudienciaDTO;
import com.juriscontrol.demo.dto.AudienciaDTO.ListaTudoAudienciaDTO;
import com.juriscontrol.demo.dto.ClienteDTO.ClienteResumoDTO;
import com.juriscontrol.demo.dto.ClienteDTO.CriarClienteDTO;
import com.juriscontrol.demo.dto.ClienteDTO.ListaClienteDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.AtualizarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.CriarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.dto.RegistroDeInfoDTO.ExibirRegistroDeInfoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.ClienteNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Cliente;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.ProcessoRepository;

@Service
public class ProcessoService {
    
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AdvogadoService advogadoService;

    @Autowired
    private ClienteService clienteService;

    public Processo criarProcesso(CriarProcessoDTO dto) throws AdvogadoNotFoundException, ClienteNotFoundException {
        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.getNumeroProcesso());
        processo.setDescricao(dto.getDescricao());
        processo.setStatus(dto.getStatus());
        processo.setTipo(dto.getTipo());
        processo.setDataInicio(dto.getDataInicio());
        
        AdvogadoResumoDTO advogadoAutorDTO = advogadoService.buscarAdvogadoResumoPorId(dto.getAdvogadoAutorId());
        AdvogadoResumoDTO advogadoReuDTO = advogadoService.buscarAdvogadoResumoPorId(dto.getAdovgadoReuId());
        
        // ClienteResumoDTO clienteAutorDTO = clienteService.buscarClienteResumoPorId(dto.getAutorId());
        // ClienteResumoDTO clienteReuDTO = clienteService.buscarClienteResumoPorId(dto.getReuId());

        Advogado advogadoAutor = new Advogado();
        advogadoAutor.setId(advogadoAutorDTO.getId());
        advogadoAutor.setNome(advogadoAutorDTO.getNome());
        advogadoAutorDTO.setRegistroOAB(advogadoAutorDTO.getRegistroOAB());

        Advogado advogadoReu = new Advogado();
        advogadoReu.setId(advogadoReuDTO.getId());
        advogadoReu.setNome(advogadoReuDTO.getNome());
        advogadoReu.setRegistroOAB(advogadoReuDTO.getRegistroOAB());

        Cliente clienteAutor = new Cliente();
        clienteAutor.setNome(dto.getClienteAutor().getNome());
        clienteAutor.setCpf(dto.getClienteAutor().getCpf());
        clienteAutor.setTelefone(dto.getClienteAutor().getTelefone());
        clienteAutor.setEndereco(dto.getClienteAutor().getEndereco());

        clienteService.salvar(clienteAutor);

        Cliente clienteReu = new Cliente();
        clienteReu.setNome(dto.getClienteReu().getNome());
        clienteReu.setCpf(dto.getClienteReu().getCpf());
        clienteReu.setTelefone(dto.getClienteReu().getTelefone());
        clienteReu.setEndereco(dto.getClienteReu().getEndereco());

        clienteService.salvar(clienteReu);

        processo.setAdvogadoAutor(advogadoAutor);
        processo.setAdvogadoReu(advogadoReu);
        processo.setClienteAutor(clienteAutor);
        processo.setClienteReu(clienteReu);

        return processoRepository.save(processo);
    }

    public Processo atualizarProcesso(Long id, AtualizarProcessoDTO dto) throws ProcessoNotFoundException {
        Optional<Processo> opProcesso = processoRepository.findById(id);
        if (opProcesso.isPresent()) {
            Processo processo = opProcesso.get();
            processo.setDescricao(dto.getDescricao());
            processo.setStatus(dto.getStatus());
            processo.setTipo(dto.getTipo());
            // processo.getAdvogadoAutor(dto.getAdvogadoAutorId());
            // processo.getAdovgadoReu(dto.getAdovgadoReuId());
            // processo.getDocumento(dto.getAnexo());
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
                processo.getDescricao(),
                processo.getStatus(),
                processo.getTipo(),
                processo.getDataInicio(),
                processo.getAdvogadoAutor() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoAutor().getId(),
                    processo.getAdvogadoAutor().getNome(),
                    processo.getAdvogadoAutor().getRegistroOAB()
                ) : null,
                processo.getAdvogadoReu() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoReu().getId(),
                    processo.getAdvogadoReu().getNome(),
                    processo.getAdvogadoReu().getRegistroOAB()
                ) : null,
                processo.getClienteAutor() != null ? new ListaClienteDTO(
                    processo.getClienteAutor().getId(),
                    processo.getClienteAutor().getNome(),
                    processo.getClienteAutor().getTelefone(),
                    processo.getClienteAutor().getTipo(),
                    processo.getClienteAutor().getCpf(),
                    processo.getClienteAutor().getEndereco()
                ) : null,
                processo.getClienteReu() != null ? new ListaClienteDTO(
                    processo.getClienteReu().getId(),
                    processo.getClienteReu().getNome(),
                    processo.getClienteReu().getTelefone(),
                    processo.getClienteReu().getTipo(),
                    processo.getClienteReu().getCpf(),
                    processo.getClienteReu().getEndereco()
                ) : null,
                processo.getAudiencias() != null ? processo.getAudiencias().stream()
                    .map(audiencia -> new ListaAudienciaDTO(
                        audiencia.getId(),
                        audiencia.getDataHora(),
                        audiencia.getLocal()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList(), // Retorna lista vazia se nula
                processo.getRegistrosDeInfo() != null ? processo.getRegistrosDeInfo().stream()
                    .map(registroDeInfo -> new ExibirRegistroDeInfoDTO(
                        registroDeInfo.getData(),
                        registroDeInfo.getDescricao()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()/*,   
                processo.getDocumentos() != null ? processo.getDocumentos().stream()
                    .map(documento -> new ListaDocumentoDTO(
                        documento.getTitulo(),
                        documentoo.getAnexo()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()*/
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
                processo.getDescricao(),
                processo.getStatus(),
                processo.getTipo(),
                processo.getDataInicio(),
                processo.getAdvogadoAutor() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoAutor().getId(),
                    processo.getAdvogadoAutor().getNome(),
                    processo.getAdvogadoAutor().getRegistroOAB()
                ) : null,
                processo.getAdvogadoReu() != null ? new ListaAdvogadoDTO(
                    processo.getAdvogadoReu().getId(),
                    processo.getAdvogadoReu().getNome(),
                    processo.getAdvogadoReu().getRegistroOAB()
                ) : null,
                processo.getClienteAutor() != null ? new ListaClienteDTO(
                    processo.getClienteAutor().getId(),
                    processo.getClienteAutor().getNome(),
                    processo.getClienteAutor().getTelefone(),
                    processo.getClienteAutor().getTipo(),
                    processo.getClienteAutor().getCpf(),
                    processo.getClienteAutor().getEndereco()
                ) : null,
                processo.getClienteReu() != null ? new ListaClienteDTO(
                    processo.getClienteReu().getId(),
                    processo.getClienteReu().getNome(),
                    processo.getClienteReu().getTelefone(),
                    processo.getClienteReu().getTipo(),
                    processo.getClienteReu().getCpf(),
                    processo.getClienteReu().getEndereco()
                ) : null,
                processo.getAudiencias() != null ? processo.getAudiencias().stream()
                    .map(audiencia -> new ListaAudienciaDTO(
                        audiencia.getId(),
                        audiencia.getDataHora(),
                        audiencia.getLocal()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList(), // Retorna lista vazia se nula
                processo.getRegistrosDeInfo() != null ? processo.getRegistrosDeInfo().stream()
                    .map(registroDeInfo -> new ExibirRegistroDeInfoDTO(
                        registroDeInfo.getData(),
                        registroDeInfo.getDescricao()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()/*,   
                processo.getDocumentos() != null ? processo.getDocumentos().stream()
                    .map(documento -> new ListaDocumentoDTO(
                        documento.getTitulo(),
                        documentoo.getAnexo()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()*/    
            );
        }
        throw new ProcessoNotFoundException("Processo não encontrado");
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