package com.juriscontrol.demo.dto.AnexoDTO;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarVariosAnexosDTO {

    private String nomeAnexoBase; 
    private List<MultipartFile> anexos;
    private Long processoId;

   
}