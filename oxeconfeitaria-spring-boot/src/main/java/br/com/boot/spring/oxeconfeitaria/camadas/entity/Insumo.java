package br.com.boot.spring.oxeconfeitaria.camadas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "public", name = "insumo")
public class Insumo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInsumo;
	
	private String dsInsumo;
	
	private Integer nrQtdBase;
	
	private String dsMedida;
	
	private BigDecimal vlPreco;
	
	private LocalDate dtCadastro;

	private LocalDate dtAtualizacao;

}
