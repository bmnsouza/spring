package br.com.spring.boot.oxeconfeitaria.camadas.service;

import org.springframework.http.ResponseEntity;

import br.com.spring.boot.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Atualizacao;
import br.com.spring.boot.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Cadastro;
import br.com.spring.boot.oxeconfeitaria.exception.ServiceException;
import br.com.spring.boot.oxeconfeitaria.util.response.EntidadeResponse;

public interface InsumoService {

	ResponseEntity<EntidadeResponse> buscar(Integer idInsumo, String dsInsumo);
	
	ResponseEntity<EntidadeResponse> buscarNativeQuery(Integer idInsumo, String dsInsumo);
	
	ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro);
	
	ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException;

	ResponseEntity<EntidadeResponse> excluir(Integer idInsumo) throws ServiceException;
	
}