package br.com.boot.spring.base.util.response;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeResponse {

	private Response response;

	@Data
	public class Response {

		private String msgTecnica = new String();

		private String msgUsuario = new String();

		private Object dados = new HashMap<>();
	}

	@Data
	public class Paginacao {

		private int pagina;

		private int elementos;

		private boolean conteudo;

		private boolean anterior;

		private boolean proxima;

		private boolean primeira;

		private boolean ultima;
	}

}
