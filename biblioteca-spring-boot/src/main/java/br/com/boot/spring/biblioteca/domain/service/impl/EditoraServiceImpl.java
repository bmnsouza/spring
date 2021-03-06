package br.com.boot.spring.biblioteca.domain.service.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.base.util.response.ResponseUtil;
import br.com.boot.spring.biblioteca.domain.entity.Editora;
import br.com.boot.spring.biblioteca.domain.entity.dto.EditoraDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.domain.entity.dto.EditoraDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.domain.repository.EditoraRepository;
import br.com.boot.spring.biblioteca.domain.service.EditoraService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EditoraServiceImpl implements EditoraService {

	private final EditoraRepository editoraRepository;

	private final ResponseUtil responseUtil;

	@Value("${spring.data.web.pageable.default-page-size}")
	private int pageSize;

	@Override
	public ResponseEntity<EntidadeResponse> buscar(Integer codigo, String nome, Integer pagina) {
		Slice<Editora> dados = editoraRepository.buscar(codigo, nome, PageRequest.of(pagina, pageSize)); 
		return responseUtil.responseSucesso(OK, dados);
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) throws ServiceException {
		
		if (editoraRepository.findById(cadastro.getCodigo()).isPresent()) {
			throw new ServiceException("Editora já cadastrada");
		}
		
		editoraRepository.save(new Editora(cadastro.getCodigo(), cadastro.getNome()));

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
