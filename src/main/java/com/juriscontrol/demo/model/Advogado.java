package com.juriscontrol.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Advogado extends Usuario{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String registroOAB;

    @JsonIgnore
    @OneToMany(mappedBy = "advogadoAutor")
    private List<Processo> processosComoAutor;

    @JsonIgnore
    @OneToMany(mappedBy = "advogadoReu") 
    private List<Processo> processosComoReu;

}
