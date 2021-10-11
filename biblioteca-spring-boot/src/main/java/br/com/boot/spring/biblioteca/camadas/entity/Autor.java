package br.com.boot.spring.biblioteca.camadas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "autor")
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;
	
	@Column(name = "primeiro_nome")
	private String primeiroNome;

	@Column(name = "inicial_meio_nome")
	private String inicialMeioNome;
	
	@Column(name = "ultimo_nome")
	private String ultimoNome;
	
}
