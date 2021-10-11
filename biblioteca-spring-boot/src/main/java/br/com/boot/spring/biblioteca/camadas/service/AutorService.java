package br.com.boot.spring.biblioteca.camadas.service;

import org.springframework.http.ResponseEntity;

import br.com.boot.spring.biblioteca.camadas.entity.dto.AutorDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.AutorDTO.Request.Cadastro;
import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;

public interface AutorService {

	ResponseEntity<EntidadeResponse> buscar(Integer codigo, String primeiroNome, String inicialMeioNome, String ultimoNome);
	
	ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) throws ServiceException;
	
	ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException;

	ResponseEntity<EntidadeResponse> excluir(Integer codigo) throws ServiceException;
	
}