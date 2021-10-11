package br.com.boot.spring.biblioteca.camadas.service.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.base.util.response.ResponseUtil;
import br.com.boot.spring.biblioteca.camadas.entity.Editora;
import br.com.boot.spring.biblioteca.camadas.entity.dto.EditoraDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.EditoraDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.camadas.repository.EditoraRepository;
import br.com.boot.spring.biblioteca.camadas.service.EditoraService;

@Service
public class EditoraServiceImpl implements EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;

	@Autowired
	private ResponseUtil responseUtil;

	@Override
	public ResponseEntity<EntidadeResponse> buscar(Integer codigo, String nome) {
		List<Editora> dados = null;
		
		if (codigo != null) {
			dados = editoraRepository.getByCodigo(codigo);
		} else if (nome != null) {
			dados = editoraRepository.findByNomeContainingIgnoreCase(nome, Sort.by(Sort.Direction.ASC, "nome"));
		} else {
			dados = editoraRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		}
		
		return responseUtil.responseSucesso(OK, dados);
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> buscarNativeQuery(Integer codigo, String nome) {
		List<Editora> dados = editoraRepository.buscarNativeQuery(codigo, nome);
		return responseUtil.responseSucesso(OK, dados);
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) throws ServiceException {
		
		if (editoraRepository.findById(cadastro.getCodigo()).isPresent()) {
			throw new ServiceException("Editora já cadastrada");
		}
		
		Editora editora = new Editora();
		editora.setCodigo(cadastro.getCodigo());
		editora.setNome(cadastro.getNome());
		editora = editoraRepository.save(editora);

		return responseUtil.responseSucesso(CREATED);
	}

	@Override
	public ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException {
		
		Optional<Editora> optional = editoraRepository.findById(atualizacao.getCodigo());
		optional.orElseThrow(() -> new ServiceException("Editora não encontrada"));
		
		Editora editora = optional.get();
		editora.setCodigo(atualizacao.getCodigo());
		editora.setNome(atualizacao.getNome());
		editoraRepository.save(editora);			
	
		return responseUtil.responseSucesso(OK);
	}

	@Override
	public ResponseEntity<EntidadeResponse> excluir(Integer codigo) throws ServiceException {
		
		Optional<Editora> optional = editoraRepository.findById(codigo);
		optional.orElseThrow(() -> new ServiceException("Editora não encontrada"));

		editoraRepository.deleteById(codigo);
		
		return responseUtil.responseSucesso(OK);
	}

}
