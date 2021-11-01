package br.com.boot.spring.base.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class RedisModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nmProjeto;
	
	private String nmParametro;	
	
	private String vlParametro;

}
