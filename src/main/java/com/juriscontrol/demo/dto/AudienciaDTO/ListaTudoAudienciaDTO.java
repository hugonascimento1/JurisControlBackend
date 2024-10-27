package com.juriscontrol.demo.dto.AudienciaDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoAudienciaDTO {
    
    private Long id;
    private LocalDateTime dataHora;
    private String local;
    private String resultado;
}
