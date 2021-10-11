package br.com.boot.spring.biblioteca.camadas.entity.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

public enum EditoraDTO {;

	private interface Codigo {
		@NotNull @Min(1) @Max(999999999)
		Integer getCodigo();
	}
	
	private interface Nome {
		@NotEmpty @Size(min = 3, max = 50)
		String getNome();
	}

	public enum Request {;

		@Data
		public static class Cadastro implements Codigo, Nome {
			private Integer codigo;
			private String nome;
		}

		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class Atualizacao extends Cadastro {}
	}

}
