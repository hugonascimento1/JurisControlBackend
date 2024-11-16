package com.juriscontrol.demo.dto.RegistroDeInfoDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarRegistroDeInfoDTO {
    
    private Long processoId;
    private LocalDate data;
    private String descricao;

}
