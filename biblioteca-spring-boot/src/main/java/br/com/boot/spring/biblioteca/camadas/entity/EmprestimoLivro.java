package br.com.boot.spring.biblioteca.camadas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.com.boot.spring.biblioteca.camadas.entity.id.EmprestimoLivroId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(EmprestimoLivroId.class)
@Table(schema = "public", name = "emprestimo_livro")
public class EmprestimoLivro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer numEmprestimo;

	@Id
	private String isbnLivro;
	
	@Column(name = "data_prev_dev")
	private LocalDate dataPrevDev;

	@Column(name = "data_dev")
	private LocalDate dataDev;

	@Column(name = "valor_multa")
	private BigDecimal valorMulta;
	
}
