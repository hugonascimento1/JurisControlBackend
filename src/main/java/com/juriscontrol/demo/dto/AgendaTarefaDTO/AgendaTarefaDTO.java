package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.juriscontrol.demo.model.AgendaTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaTarefaDTO {
    
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;

    public AgendaTarefaDTO(AgendaTarefa entity) {
        this.id = entity.getId();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
        this.dataInicio = entity.getDataInicio();
        this.dataFinal = entity.getDataFinal();
    }
    
    public static List<AgendaTarefaDTO> fromEntityList(List<AgendaTarefa> entities) {
        return entities.stream()
                .map(AgendaTarefaDTO::new)
                .collect(Collectors.toList());
    }
}