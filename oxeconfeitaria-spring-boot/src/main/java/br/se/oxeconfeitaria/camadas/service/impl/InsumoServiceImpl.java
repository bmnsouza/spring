package br.se.oxeconfeitaria.camadas.service.impl;

import static br.se.oxeconfeitaria.util.response.ResponseUtil.MENSAGEM_OK;
import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.se.oxeconfeitaria.camadas.entity.Insumo;
import br.se.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Atualizacao;
import br.se.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Cadastro;
import br.se.oxeconfeitaria.camadas.repository.InsumoRepository;
import br.se.oxeconfeitaria.camadas.service.InsumoService;
import br.se.oxeconfeitaria.exception.ServiceException;
import br.se.oxeconfeitaria.util.response.EntidadeResponse;
import br.se.oxeconfeitaria.util.response.ResponseUtil;

@Service
public class InsumoServiceImpl implements InsumoService {

	@Autowired
	private InsumoRepository insumoRepository;

	@Autowired
	private ResponseUtil responseUtil;

	@Override
	public ResponseEntity<EntidadeResponse> buscar(Integer idInsumo, String dsInsumo) {
		List<Insumo> dados = null;
		
		if (idInsumo != null) {
			dados = insumoRepository.getByIdInsumo(idInsumo);
		} else if (dsInsumo != null) {
			dados = insumoRepository.findByDsInsumoContainingIgnoreCase(dsInsumo, Sort.by(Sort.Direction.ASC, "dsInsumo"));
		} else {
			dados = insumoRepository.findAll(Sort.by(Sort.Direction.ASC, "dsInsumo"));
		}
		
		return responseUtil.responseSucesso(OK, MENSAGEM_OK, dados);
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

		Map<String, Integer> dados = new HashMap<>();
		dados.put("idInsumo", insumo.getIdInsumo());
		
		return responseUtil.responseSucesso(OK, MENSAGEM_OK, dados);
	}

	@Override
	public ResponseEntity<EntidadeResponse> atualizar(Atualizacao atualizacao) throws ServiceException {
		
		// Verifica se o Insumo existe
		Optional<Insumo> optional = insumoRepository.findById(atualizacao.getIdInsumo());
		optional.orElseThrow(() -> new ServiceException("idInsumo", "Insumo não encontrado"));
		
		Insumo insumo = optional.get();
		insumo.setDsInsumo(atualizacao.getDsInsumo());
		insumo.setNrQtdBase(atualizacao.getNrQtdBase());
		insumo.setDsMedida(atualizacao.getDsMedida());
		insumo.setVlPreco(atualizacao.getVlPreco());
		insumo.setDtAtualizacao(LocalDate.now());
		
		insumoRepository.save(insumo);			
	
		return responseUtil.responseSucesso(OK, MENSAGEM_OK);
	}

	@Override
	public ResponseEntity<EntidadeResponse> excluir(Integer idInsumo) throws ServiceException {
		
		// Verifica se o Insumo existe
		Optional<Insumo> optional = insumoRepository.findById(idInsumo);
		optional.orElseThrow(() -> new ServiceException("idInsumo", "Insumo não encontrado"));

		insumoRepository.deleteById(idInsumo);
		
		return responseUtil.responseSucesso(OK, MENSAGEM_OK);
	}

}