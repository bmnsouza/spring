package br.com.boot.spring.biblioteca.camadas.entity;

import java.io.Serializable;

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
@Table(schema = "public", name = "editora")
public class Editora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;
	
	private String nome;
	
}
