package br.com.boot.spring.biblioteca.domain.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LivroAutorId implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private Integer codAutor;

	private String isbnLivro;

}