package br.com.spring.boot.oxeconfeitaria.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String parameter, String message) {
		super(new StringBuilder(parameter).append(": ").append(message).toString());
	}

}