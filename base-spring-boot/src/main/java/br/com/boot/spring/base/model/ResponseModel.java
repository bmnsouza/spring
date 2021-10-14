package br.com.boot.spring.base.model;

import lombok.Getter;

@Getter
public class ResponseModel {

	private Response response;

	@Getter
	public class Response {

		private String msgTecnica;
	
		private String msgUsuario;
	
		private Object dados;

	}
	
}
