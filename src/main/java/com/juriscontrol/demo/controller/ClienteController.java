package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.ClienteDTO.AtualizarClienteDTO;
import com.juriscontrol.demo.dto.ClienteDTO.CriarClienteDTO;
import com.juriscontrol.demo.dto.ClienteDTO.ListaClienteDTO;
import com.juriscontrol.demo.exception.ClienteNotFoundException;
import com.juriscontrol.demo.model.Cliente;
import com.juriscontrol.demo.service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/add")
    public ResponseEntity<Cliente> criarCliente(@RequestBody CriarClienteDTO dto) {
        Cliente clienteCriado = clienteService.criarCliente(dto);
        return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDTO dto ) {
        try {
            Cliente clienteAtualizado  = clienteService.atualizarCliente(id, dto);
            return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListaClienteDTO>> buscarTodosClientes() {
        List<ListaClienteDTO> clientes = clienteService.buscarTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaClienteDTO> buscarClientePorId(@PathVariable Long id) {
        try {
            ListaClienteDTO listaClienteDTO = clienteService.buscarPorIdCliente(id);
            return new ResponseEntity<>(listaClienteDTO, HttpStatus.OK);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
