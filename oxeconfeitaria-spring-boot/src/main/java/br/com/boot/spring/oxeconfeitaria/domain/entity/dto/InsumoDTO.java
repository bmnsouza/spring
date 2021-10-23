package br.com.boot.spring.oxeconfeitaria.domain.entity.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

public enum InsumoDTO {;

	private interface IdInsumo {
		@NotNull @Min(1) @Max(999999999)
		Integer getIdInsumo();
	}
	
	private interface DsInsumo {
		@NotEmpty @Size(min = 5, max = 50)
		String getDsInsumo();
	}

	private interface NrQtdBase {
		@NotNull @Min(1) @Max(999999999)
		Integer getNrQtdBase();
	}

	private interface DsMedida {
		@NotEmpty @Size(min = 5, max = 20)
		String getDsMedida();
	}
	
	private interface VlPreco {
		@NotNull @DecimalMin("0.01") @DecimalMax("9999999999999.99") @Digits(integer = 13, fraction = 2)
		BigDecimal getVlPreco();
	}
	
	public enum Request {;

		@Data
		public static class Cadastro implements DsInsumo, NrQtdBase, DsMedida, VlPreco {
			private String dsInsumo;
			private Integer nrQtdBase;
			private String dsMedida;
			private BigDecimal vlPreco; 
		}

		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class Atualizacao extends Cadastro implements IdInsumo {
			private Integer idInsumo;
		}
	}

}
