package br.com.boot.spring.biblioteca.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.com.boot.spring.biblioteca.domain.entity.id.LivroAutorId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(LivroAutorId.class)
@Table(schema = "public", name = "livro_autor")
public class LivroAutor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codAutor;

	@Id
	private String isbnLivro;
	
}
