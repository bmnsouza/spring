package br.com.boot.spring.biblioteca.camadas.service;

import org.springframework.http.ResponseEntity;

import br.com.boot.spring.biblioteca.camadas.entity.dto.EditoraDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.EditoraDTO.Request.Cadastro;
import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;

public interface EditoraService {

	ResponseEntity<EntidadeResponse> buscar(Integer codigo, String nome);
	
	ResponseEntity<EntidadeResponse> buscarNativeQuery(Integer codigo, String nome);
	
	ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro);
	
	ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException;

	ResponseEntity<EntidadeResponse> excluir(Integer codigo) throws ServiceException;
	
}