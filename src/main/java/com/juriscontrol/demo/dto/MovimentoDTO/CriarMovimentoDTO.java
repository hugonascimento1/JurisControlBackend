package com.juriscontrol.demo.dto.MovimentoDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarMovimentoDTO {

    private String nomeMovimento;
    private String tipo;
    private LocalDateTime data;
    private Long processoId;
}