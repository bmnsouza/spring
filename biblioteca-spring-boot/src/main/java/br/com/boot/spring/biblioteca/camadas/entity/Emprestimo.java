package br.com.boot.spring.biblioteca.camadas.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(schema = "public", name = "emprestimo")
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer numero;
	
	@Column(name = "mat_usuario")
	private Integer matUsuario;

	private LocalDateTime data;
	
}
