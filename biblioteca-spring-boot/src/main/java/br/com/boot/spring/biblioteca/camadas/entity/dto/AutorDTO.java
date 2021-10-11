package br.com.boot.spring.biblioteca.camadas.entity.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

public enum AutorDTO {;

	private interface Codigo {
		@NotNull @Min(1) @Max(999999999)
		Integer getCodigo();
	}
	
	private interface PrimeiroNome {
		@NotEmpty @Size(min = 3, max = 20)
		String getPrimeiroNome();
	}

	private interface InicialMeioNome {
		@Size(min = 1, max = 1)
		String getInicialMeioNome();
	}

	private interface UltimoNome {
		@NotEmpty @Size(min = 3, max = 20)
		String getUltimoNome();
	}

	public enum Request {;

		@Data
		public static class Cadastro implements Codigo, PrimeiroNome, InicialMeioNome, UltimoNome {
			private Integer codigo;
			private String primeiroNome;
			private String inicialMeioNome;
			private String ultimoNome;
		}

		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class Atualizacao extends Cadastro {}
	}

}
