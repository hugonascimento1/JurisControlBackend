package com.juriscontrol.demo.model;

import com.juriscontrol.demo.model.enums.TipoUsuario;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private String email;

    @NonNull
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
}
