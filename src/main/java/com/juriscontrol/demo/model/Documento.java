package com.juriscontrol.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String nomeDocumento;

	@NonNull
	private String tipoDocumento;

	@NonNull
	private Long tamDocumento;
	
	@NonNull
	@Lob
	private byte[] anexo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "registroDeInfo_id")
	private RegistroDeInfo registroDeInfo;
}