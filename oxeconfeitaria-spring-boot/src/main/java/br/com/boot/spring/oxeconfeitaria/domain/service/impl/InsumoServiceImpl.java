package br.com.boot.spring.oxeconfeitaria.domain.service.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.base.util.response.ResponseUtil;
import br.com.boot.spring.oxeconfeitaria.domain.entity.Insumo;
import br.com.boot.spring.oxeconfeitaria.domain.entity.dto.InsumoDTO.Request.Atualizacao;
import br.com.boot.spring.oxeconfeitaria.domain.entity.dto.InsumoDTO.Request.Cadastro;
import br.com.boot.spring.oxeconfeitaria.domain.repository.InsumoRepository;
import br.com.boot.spring.oxeconfeitaria.domain.service.InsumoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsumoServiceImpl implements InsumoService {

	private final InsumoRepository insumoRepository;

	private final ResponseUtil responseUtil;

	@Override
	public ResponseEntity<EntidadeResponse> buscar(Integer idInsumo, String dsInsumo) {
		Optional<List<Insumo>> optional = Optional.empty();

		if (idInsumo != null) {
			optional = insumoRepository.getByIdInsumo(idInsumo);
		} else if (dsInsumo != null) {
			optional = insumoRepository.findByDsInsumoContainingIgnoreCase(dsInsumo, Sort.by(Sort.Direction.ASC, "dsInsumo"));
		} else {
			optional = Optional.ofNullable(insumoRepository.findAll(Sort.by(Sort.Direction.ASC, "dsInsumo")));
		}
		
		return optional.filter(dados -> !dados.isEmpty())
				.map(dados -> responseUtil.responseSucesso(OK, dados))
				.orElse(responseUtil.responseSucesso(NOT_FOUND));
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> buscarNativeQuery(Integer idInsumo, String dsInsumo) {
		return insumoRepository.buscarNativeQuery(idInsumo, dsInsumo)
				.filter(dados -> !dados.isEmpty())
				.map(dados -> responseUtil.responseSucesso(OK, dados))
				.orElse(responseUtil.responseSucesso(NOT_FOUND));
	}
	
	@Override
	public ResponseEntity<EntidadeResponse> cadastrar(Cadastro cadastro) {
		Insumo insumo = new Insumo();
		insumo.setDsInsumo(cadastro.getDsInsumo());
		insumo.setNrQtdBase(cadastro.getNrQtdBase());
		insumo.setDsMedida(cadastro.getDsMedida());
		insumo.setVlPreco(cadastro.getVlPreco());
		insumo.setDtCadastro(LocalDate.now());
		
		insumo = insumoRepository.save(insumo);

		return responseUtil.responseSucesso(CREATED, Map.of("idInsumo", insumo.getIdInsumo()));
	}

	@Override
	public ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException {
		// Verifica se o Insumo existe
		Optional<Insumo> optional = insumoRepository.findById(atualizacao.getIdInsumo());
		optional.orElseThrow(() -> new ServiceException("Insumo n??o encontrado"));
		
		Insumo insumo = optional.get();
		insumo.setDsInsumo(atualizacao.getDsInsumo());
		insumo.setNrQtdBase(atualizacao.getNrQtdBase());
		insumo.setDsMedida(atualizacao.getDsMedida());
		insumo.setVlPreco(atualizacao.getVlPreco());
		insumo.setDtAtualizacao(LocalDate.now());
		
		insumoRepository.save(insumo);			
	
		return responseUtil.responseSucesso(OK);
	}

	@Override
	public ResponseEntity<EntidadeResponse> excluir(Integer idInsumo) throws ServiceException {
		// Verifica se o Insumo existe
		Optional<Insumo> optional = insumoRepository.findById(idInsumo);
		optional.orElseThrow(() -> new ServiceException("Insumo n??o encontrado"));

		insumoRepository.deleteById(idInsumo);
		
		return responseUtil.responseSucesso(OK);
	}

}
