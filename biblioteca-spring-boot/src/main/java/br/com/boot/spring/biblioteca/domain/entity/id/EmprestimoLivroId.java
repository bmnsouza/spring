package br.com.boot.spring.biblioteca.domain.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EmprestimoLivroId implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private Integer numEmprestimo;

	private String isbnLivro;

}