package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.juriscontrol.demo.model.AgendaTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AgendaTarefaDTO {
    
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private Long advogadoId;

    public AgendaTarefaDTO(AgendaTarefa agendaTarefa) {
        this.id = agendaTarefa.getId();
        this.titulo = agendaTarefa.getTitulo();
        this.descricao = agendaTarefa.getDescricao();
        this.data = agendaTarefa.getData();
        if (agendaTarefa.getAdvogado() != null) {
            this.advogadoId = agendaTarefa.getAdvogado().getId();
        }
    }
    
    public static List<AgendaTarefaDTO> fromEntityList(List<AgendaTarefa> entities) {
        return entities.stream()
                .map(AgendaTarefaDTO::new)
                .collect(Collectors.toList());
    }
}