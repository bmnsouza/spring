package br.com.boot.spring.biblioteca.camadas.service.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.base.util.response.ResponseUtil;
import br.com.boot.spring.biblioteca.camadas.entity.Autor;
import br.com.boot.spring.biblioteca.camadas.entity.dto.AutorDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.AutorDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.camadas.repository.AutorRepository;
import br.com.boot.spring.biblioteca.camadas.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private ResponseUtil responseUtil;

	@Value("${spring.data.web.pageable.default-page-size}")
	private int pageSize;

	@Override
	public ResponseEntity<EntidadeResponse> buscar(Integer codigo, String primeiroNome, String inicialMeioNome, String ultimoNome, Integer pagina) {
		Slice<Autor> dados = autorRepository.buscar(codigo, primeiroNome, inicialMeioNome, ultimoNome, PageRequest.of(pagina, pageSize));
		return responseUtil.responseSucesso(OK, dados);
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) throws ServiceException {
		
		if (autorRepository.findById(cadastro.getCodigo()).isPresent()) {
			throw new ServiceException("Autor já cadastrado");
		}
		
		autorRepository.save(new Autor(cadastro.getCodigo(), cadastro.getPrimeiroNome(), cadastro.getInicialMeioNome(), cadastro.getUltimoNome()));

		return responseUtil.responseSucesso(CREATED);
	}

	@Override
	public ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException {
		
		Optional<Autor> optional = autorRepository.findById(atualizacao.getCodigo());
		optional.orElseThrow(() -> new ServiceException("Autor não encontrado"));
		
		Autor autor = optional.get();
		autor.setCodigo(atualizacao.getCodigo());
		autor.setPrimeiroNome(atualizacao.getPrimeiroNome());
		autor.setInicialMeioNome(atualizacao.getInicialMeioNome());
		autor.setUltimoNome(atualizacao.getUltimoNome());
		
		autorRepository.save(autor);			
	
		return responseUtil.responseSucesso(OK);
	}

	@Override
	public ResponseEntity<EntidadeResponse> excluir(Integer codigo) throws ServiceException {
		
		Optional<Autor> optional = autorRepository.findById(codigo);
		optional.orElseThrow(() -> new ServiceException("Autor não encontrado"));

		autorRepository.deleteById(codigo);
		
		return responseUtil.responseSucesso(OK);
	}

}
