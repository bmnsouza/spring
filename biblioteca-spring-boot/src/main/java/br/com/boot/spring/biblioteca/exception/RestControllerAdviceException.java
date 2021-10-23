package br.com.boot.spring.biblioteca.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.boot.spring.base.exception.ApiExceptionHandler;

/**
 * Herda da classe {@link ApiExceptionHandler} que possui os tratamentos de exceções para todos os projetos.
 */
@RestControllerAdvice
public class RestControllerAdviceException extends ApiExceptionHandler {}
