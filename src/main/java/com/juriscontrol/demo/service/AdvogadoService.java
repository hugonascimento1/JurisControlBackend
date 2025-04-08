package com.juriscontrol.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AdvogadoDTO.AdvogadoResumoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.AtualizarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.CriarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaTudoAdvogadoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ProcessoResumoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.repository.AdvogadoRepository;

@Service
public class AdvogadoService {
    
    @Autowired
    private AdvogadoRepository advogadoRepository;

    
    //public Login

    //public cadastro

    //adicionarUsuario

    //deletarUsuario

    //atualizarUsuario

    //verUsuario

}
