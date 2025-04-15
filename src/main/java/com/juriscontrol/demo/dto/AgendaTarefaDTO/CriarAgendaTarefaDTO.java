package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDateTime;

// import com.juriscontrol.demo.model.AgendaTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarAgendaTarefaDTO {
    
    @NonNull
    private String titulo;
    
    @NonNull
    private String descricao;
    
    @NonNull
    private LocalDateTime data;
    
    @NonNull
    private Long advogadoId;
    
    // public AgendaTarefa toEntity() {
    //     AgendaTarefa entity = new AgendaTarefa();
    //     entity.setTitulo(this.titulo);
    //     entity.setDescricao(this.descricao);
    //     entity.setData(this.data); // Setando o campo 'data'
    //     // A associação com o advogado será feita no serviço
    //     return entity;
    // }
}