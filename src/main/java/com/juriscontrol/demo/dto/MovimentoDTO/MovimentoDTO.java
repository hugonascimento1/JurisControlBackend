package com.juriscontrol.demo.dto.MovimentoDTO;

import java.time.LocalDateTime;

import com.juriscontrol.demo.model.Movimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentoDTO {
    
    private Long id;
    private String nomeMovimento;
    private String tipo;
    private LocalDateTime data;
    private Long processoId; 

    public MovimentoDTO(Movimento entity) {
        this.id = entity.getId();
        this.nomeMovimento = entity.getNomeMovimento();
        this.tipo = entity.getTipo();
        this.data = entity.getData();
        if (entity.getProcesso() != null) {
            this.processoId = entity.getProcesso().getId();
        }
    }
}