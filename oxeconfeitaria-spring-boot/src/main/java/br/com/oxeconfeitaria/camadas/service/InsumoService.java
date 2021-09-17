package br.se.oxeconfeitaria.camadas.service;

import org.springframework.http.ResponseEntity;

import br.se.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Atualizacao;
import br.se.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Cadastro;
import br.se.oxeconfeitaria.exception.ServiceException;
import br.se.oxeconfeitaria.util.response.EntidadeResponse;

public interface InsumoService {

	ResponseEntity<EntidadeResponse> buscar(Integer idInsumo, String dsInsumo);
	
	ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro);
	
	ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException;

	ResponseEntity<EntidadeResponse> excluir(Integer idInsumo) throws ServiceException;
	
}