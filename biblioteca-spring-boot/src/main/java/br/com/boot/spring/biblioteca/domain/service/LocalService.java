package br.com.boot.spring.biblioteca.domain.service;

import org.springframework.http.ResponseEntity;

import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.biblioteca.domain.entity.dto.LocalDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.domain.entity.dto.LocalDTO.Request.Cadastro;

public interface LocalService {

	ResponseEntity<EntidadeResponse> buscar(Integer codigo, String nome, Integer pagina);
	
	ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) throws ServiceException;
	
	ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException;

	ResponseEntity<EntidadeResponse> excluir(Integer codigo) throws ServiceException;
	
}