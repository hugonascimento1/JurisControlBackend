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
public class AtualizarAgendaTarefaDTO {
    
    @NonNull
    private String titulo;
    
    @NonNull
    private String descricao;
    
    @NonNull
    private LocalDateTime data;
    
    @NonNull
    private Long advogadoId;

//     public void updateEntity(AgendaTarefa entity) {
//         entity.setTitulo(this.titulo);
//         entity.setDescricao(this.descricao);
//         entity.setData(this.data); // Atualizando o campo 'data'
//         // O ID do advogado não deve ser atualizado diretamente aqui,
//         // pois a associação geralmente é gerenciada em outro contexto
//     }
}