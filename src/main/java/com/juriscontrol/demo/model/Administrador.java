package com.juriscontrol.demo.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    @NonNull
    @Column(unique = true)
    private String cnpj;

    @NonNull
    private String telefone;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
