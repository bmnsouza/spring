package br.com.boot.spring.base.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Response response;

	@Getter
	public class Response {

		private String msgTecnica;
	
		private String msgUsuario;
	
		private Object dados;

	}
	
}
