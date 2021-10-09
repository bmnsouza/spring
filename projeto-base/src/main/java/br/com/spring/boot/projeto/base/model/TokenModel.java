package br.com.spring.boot.projeto.base.model;

import lombok.Getter;

@Getter
public class TokenModel {

	private String access_token;

	private String token_type;

	private String expires_in;

}