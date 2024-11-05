package main.java.com.juriscontrol.demo.model;

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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getters
@Setters
public class Documento {
	
	@Id
	@GeneratedValue(strategy = Generationtype.IDENTITY)
	private Long id;

	private String titulo;

	private String tipoDocumento;
	
	@Lob
	private byte[] anexo;
	
	@ManyToOne
	@JoinColumn(name = "processos_id")
	private Processo processos;
}