package com.juriscontrol.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("ADVOGADO")
public class Advogado extends Usuario {

    @NonNull
    @Column(unique = true)
    private String registroOAB;

    @JsonIgnore
    @OneToMany(mappedBy = "advogado")
    private List<Processo> processos;

    @JsonIgnore
    @OneToMany(mappedBy = "advogado")
    private List<AgendaTarefa> tarefas;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
