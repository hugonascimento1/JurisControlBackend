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
    private String descricao;

    @NonNull
    private String status;

    @NonNull
    private String tipo;

    @NonNull
    private LocalDateTime dataInicio;

    @ManyToOne
    @JoinColumn(name = "cliente_autor_id")
    private Cliente clienteAutor;

    @ManyToOne
    @JoinColumn(name = "cliente_reu_id")
    private Cliente clienteReu;

    @ManyToOne
    @JoinColumn(name = "advogado_autor_id")
    private Advogado advogadoAutor;

    @ManyToOne
    @JoinColumn(name = "advogado_reu_id")
    private Advogado advogadoReu;

    @JsonIgnore
    @OneToMany(mappedBy = "processo")
    private List<Audiencia> audiencias;

    @JsonIgnore
    @OneToMany(mappedBy = "processo")
    private List<RegistroDeInfo> registrosDeInfo;

}