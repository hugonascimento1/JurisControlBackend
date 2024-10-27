package com.juriscontrol.demo.model;

import java.util.List;

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
public class Advogado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private String registroOAB;

    @NonNull
    private String token;

    @ManyToOne
    @JoinColumn(name = "escritorio_id")
    private Escritorio escritorio;

    @OneToMany(mappedBy = "advogadoAutor")
    private List<Processo> processosComoAutor;

    @OneToMany(mappedBy = "advogadoReu") 
    private List<Processo> processosComoReu;

    public Advogado(Long id, String nome, String registroOAB) {
        this.id = id;
        this.nome = nome;
        this.registroOAB = registroOAB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroOAB() {
        return registroOAB;
    }

    public void setRegistroOAB(String registroOAB) {
        this.registroOAB = registroOAB;
    }

}
