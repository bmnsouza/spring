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
@Table(schema = "public", name = "livro")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;
	
	private String titulo;

	private String subTitulo;
	
	@Column(name = "num_paginas")
	private Integer numPaginas;
	
	private Integer edicao;

	private Integer volume;

	@Column(name = "cod_editora")
	private Integer codEditora;
	
	@Column(name = "cod_local")
	private Integer codLocal;
	
}
