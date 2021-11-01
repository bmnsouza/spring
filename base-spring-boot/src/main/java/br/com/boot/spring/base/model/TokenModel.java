package br.com.boot.spring.base.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class TokenModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String access_token;

	private String token_type;

	private String expires_in;

}