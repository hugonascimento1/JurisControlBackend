package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarProcessoDTO {
    
    private String descricao;
    private String status;
    private String tipo;
    private Long advogadoAutorId;
    private Long adovgadoReuId;

}
