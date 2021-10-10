package br.com.boot.spring.base.model;

import lombok.Getter;

@Getter
public class TokenModel {

	private String access_token;

	private String token_type;

	private String expires_in;

}