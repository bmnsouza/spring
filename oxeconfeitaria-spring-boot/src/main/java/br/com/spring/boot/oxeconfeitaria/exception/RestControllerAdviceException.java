package br.com.spring.boot.oxeconfeitaria.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.spring.boot.projeto.base.exception.HandleException;

/**
 * Herda da classe {@link HandlerException} que possui os tratamentos de exceções para todos os projetos.
 */
@RestControllerAdvice
public class RestControllerAdviceException extends HandleException {}
