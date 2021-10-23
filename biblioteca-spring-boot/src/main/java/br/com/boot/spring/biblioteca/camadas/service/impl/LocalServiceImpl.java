package br.com.boot.spring.biblioteca.camadas.service.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.base.util.response.ResponseUtil;
import br.com.boot.spring.biblioteca.camadas.entity.Local;
import br.com.boot.spring.biblioteca.camadas.entity.dto.LocalDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.LocalDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.camadas.repository.LocalRepository;
import br.com.boot.spring.biblioteca.camadas.service.LocalService;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalRepository localRepository;

	@Autowired
	private ResponseUtil responseUtil;

	@Value("${spring.data.web.pageable.default-page-size}")
	private int pageSize;

	@Override
	public ResponseEntity<EntidadeResponse> buscar(Integer codigo, String nome, Integer pagina) {
		return localRepository.buscar(codigo, nome, PageRequest.of(pagina, pageSize))
				.filter(dados -> !dados.isEmpty())
				.map(dados -> responseUtil.responseSucesso(OK, dados))
				.orElse(responseUtil.responseSucesso(NOT_FOUND));
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) throws ServiceException {
		
		if (localRepository.findById(cadastro.getCodigo()).isPresent()) {
			throw new ServiceException("Local já cadastrado");
		}
		
		Local local = new Local();
		local.setCodigo(cadastro.getCodigo());
		local.setNome(cadastro.getNome());
		local = localRepository.save(local);

		return responseUtil.responseSucesso(CREATED);
	}

	@Override
	public ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException {
		
		Optional<Local> optional = localRepository.findById(atualizacao.getCodigo());
		optional.orElseThrow(() -> new ServiceException("Local não encontrado"));
		
		Local local = optional.get();
		local.setCodigo(atualizacao.getCodigo());
		local.setNome(atualizacao.getNome());
		localRepository.save(local);			
	
		return responseUtil.responseSucesso(OK);
	}

	@Override
	public ResponseEntity<EntidadeResponse> excluir(Integer codigo) throws ServiceException {
		
		Optional<Local> optional = localRepository.findById(codigo);
		optional.orElseThrow(() -> new ServiceException("Local não encontrado"));

		localRepository.deleteById(codigo);
		
		return responseUtil.responseSucesso(OK);
	}

}
