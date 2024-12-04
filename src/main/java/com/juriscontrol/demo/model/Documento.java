package com.juriscontrol.demo.model;

// import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
// import lombok.NonNull;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Documento /*implements Serializable //Usada para registrar que os objetos intanciados pode ser transformados em uma sequência de bytes*/ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String titulo;

	@NonNull
	private String tipoDocumento;

	@NonNull
	private Long tamanhoDoc;
	
	@Lob
	private byte[] anexo;
	
	@ManyToOne
	@JoinColumn(name = "processos_id")
	private Processo processo;
}