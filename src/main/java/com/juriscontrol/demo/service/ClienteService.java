package com.juriscontrol.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.ClienteDTO.AtualizarClienteDTO;
import com.juriscontrol.demo.dto.ClienteDTO.ClienteResumoDTO;
import com.juriscontrol.demo.dto.ClienteDTO.CriarClienteDTO;
import com.juriscontrol.demo.dto.ClienteDTO.ListaClienteDTO;
import com.juriscontrol.demo.exception.ClienteNotFoundException;
import com.juriscontrol.demo.model.Cliente;
import com.juriscontrol.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente criarCliente(CriarClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setParte(dto.getParte());
        
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, AtualizarClienteDTO dto) throws ClienteNotFoundException {
        Optional<Cliente> opCliente = clienteRepository.findById(id);
        if (opCliente.isPresent()) {
            Cliente cliente = opCliente.get();
            cliente.setNome(dto.getNome());
            cliente.setCpf(dto.getCpf());
            cliente.setTelefone(dto.getTelefone());
            cliente.setEndereco(dto.getEndereco());
            cliente.setEmail(dto.getEmail());
            cliente.setSenha(dto.getSenha());
            cliente.setParte(dto.getParte());

             // private String nome;
    // private String cpf;
    // private String telefone;
    // private String endereco;
    // private String email;
    // private String senha;
    // private String parte;

            return clienteRepository.save(cliente);
        }
        throw new ClienteNotFoundException("cliente não encontrado.");
    }

    public ListaClienteDTO buscarPorIdCliente(Long id) throws ClienteNotFoundException {
        Optional<Cliente> opCliente = clienteRepository.findById(id);
        if (opCliente.isPresent()) {
            Cliente cliente = opCliente.get();
            return new ListaClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getParte()
            );    
        }
        throw new ClienteNotFoundException("Cliente não encontrado");
    }

    public List<ListaClienteDTO> buscarTodosClientes() {
        return clienteRepository.findAll().stream().map(cliente -> new ListaClienteDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getTelefone(),
            cliente.getParte(),
            cliente.getCpf(),
            cliente.getEndereco()

            // private Long id;
    // private String nome;
    // private String telefone;
    // private String tipo;
    // private String cpf;
    // private String endereco;
        ))
        .collect(Collectors.toList());
    }

    public ClienteResumoDTO buscarClienteResumoPorId(Long id) throws ClienteNotFoundException {
        Optional<Cliente> opCliente = clienteRepository.findById(id);
        if (opCliente.isPresent()) {
            Cliente cliente = opCliente.get();
            return new ClienteResumoDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf()
            );
        }
        throw new ClienteNotFoundException("Cliente não encontrado!");
    }

    public void deletarCliente(Long id) throws ClienteNotFoundException {
        Optional<Cliente> opCliente = clienteRepository.findById(id);
        if (opCliente.isPresent()) {
            clienteRepository.delete(opCliente.get());
        } else {
            throw new ClienteNotFoundException("Cliente não encontrado");
        }
    }
}
