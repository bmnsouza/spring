package br.com.boot.spring.biblioteca.camadas.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "public", name = "local")
public class Local implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;
	
	private String nome;
	
}
