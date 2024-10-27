package com.juriscontrol.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.EscritorioDTO.AtualizarEscritorioDTO;
import com.juriscontrol.demo.dto.EscritorioDTO.CriarEscritorioDTO;
import com.juriscontrol.demo.dto.EscritorioDTO.ListaEscritorioDTO;
import com.juriscontrol.demo.exception.EscritorioNotFoundException;
import com.juriscontrol.demo.model.Escritorio;
import com.juriscontrol.demo.repository.EscritorioRepository;

@Service
public class EscritorioService {

    @Autowired
    private EscritorioRepository escritorioRepository;

    public Escritorio criarEscritorio(CriarEscritorioDTO dto) {
        Escritorio escritorio = new Escritorio();
        escritorio.setNome(dto.getNome());
        escritorio.setCnpj(dto.getCnpj());
        escritorio.setTelefone(dto.getTelefone());
        return escritorioRepository.save(escritorio);
    }

    public Escritorio atualizarEscritorio(Long id, AtualizarEscritorioDTO dto) throws EscritorioNotFoundException {
        Optional<Escritorio> opEscritorio = escritorioRepository.findById(id);
        if (opEscritorio.isPresent()) {
            Escritorio escritorio = opEscritorio.get();
            escritorio.setNome(dto.getNome());
            escritorio.setCnpj(dto.getCnpj());
            escritorio.setTelefone(dto.getTelefone());
            return escritorioRepository.save(escritorio);
        }
        throw new EscritorioNotFoundException("Escritório não encontrado.");
    }

    // public List<ListaEscritorioDTO> listarEscritorios() {

    // }

    public void deletarEscritorio(Long id) throws EscritorioNotFoundException {
        Optional<Escritorio> opEscritorio = escritorioRepository.findById(id);
        if (opEscritorio.isPresent()) {
            escritorioRepository.delete(opEscritorio.get());
        } else {
            throw new EscritorioNotFoundException("Escritorio não encontrado.");
        }
    }

}
