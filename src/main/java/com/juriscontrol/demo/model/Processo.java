package com.juriscontrol.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Processo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String numeroProcesso;

    @NonNull
    private String vara;

    @NonNull
    private String classeTipo;

    @NonNull
    private String assuntosTitulo;

    @NonNull
    private String status;

    @NonNull
    private String autor;

    @ManyToOne
    @JoinColumn(name = "advogado_autor_id")
    private Advogado advogadoAutor;

    @NonNull
    private String reu;

    @ManyToOne
    @JoinColumn(name = "advogado_reu_id")
    private Advogado advogadoReu;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "movimentos_id")
    private List<Movimentos> movimentos;

    @JsonIgnore
    @OneToMany(mappedBy = "processo")
    private List<Anexo> anexoDocumentos;

}