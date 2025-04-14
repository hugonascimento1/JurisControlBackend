package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDateTime;

import com.juriscontrol.demo.model.AgendaTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarAgendaTarefaDTO {
    
    @NonNull
    private String titulo;
    
    @NonNull
    private String descricao;
    
    @NonNull
    private LocalDateTime dataInicio;
    
    @NonNull
    private LocalDateTime dataFinal;

    public void updateEntity(AgendaTarefa entity) {
        entity.setTitulo(this.titulo);
        entity.setDescricao(this.descricao);
        entity.setDataInicio(this.dataInicio);
        entity.setDataFinal(this.dataFinal);
    }
}