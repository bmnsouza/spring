package br.com.boot.spring.oxeconfeitaria.camadas.controller.v1;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.boot.spring.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Atualizacao;
import br.com.boot.spring.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Cadastro;
import br.com.boot.spring.oxeconfeitaria.camadas.service.InsumoService;
import br.com.boot.spring.base.annotation.ApiResponsesCreated;
import br.com.boot.spring.base.annotation.ApiResponsesOk;
import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("v1/insumo")
@Tag(name = "insumo", description = "APIs de Insumo")
public class InsumoController {

	@Autowired
	private InsumoService insumoService;

	@Operation(summary = "Busca insumo pelo identificador ou descrição.",
			description = "Se nenhum parâmetro for informado, serão retornados todos os registros.<br><br>"
					+ "Esta API utiliza JPA.")
	@ApiResponsesOk
	@GetMapping("buscar")
	public ResponseEntity<EntidadeResponse> buscar(
			@Parameter(description = "Identificador do insumo.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer idInsumo,
			@Parameter(description = "Descrição do insumo.") @RequestParam(required = false) @Size(min = 3, max = 50) String dsInsumo) {
		return insumoService.buscar(idInsumo, dsInsumo);
	}
	
	@Operation(summary = "Busca insumo pelo identificador e/ou descrição.",
			description = "Se nenhum parâmetro for informado, serão retornados todos os registros.<br><br>"
					+ "Esta API utiliza query nativa.")
	@ApiResponsesOk
	@GetMapping("buscarNativeQuery")
	public ResponseEntity<EntidadeResponse> buscarNativeQuery(
			@Parameter(description = "Identificador do insumo.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer idInsumo,
			@Parameter(description = "Descrição do insumo.") @RequestParam(required = false) @Size(min = 3, max = 50) String dsInsumo) {
		return insumoService.buscarNativeQuery(idInsumo, dsInsumo);
	}
	
	@Operation(summary = "Cadastra um insumo.",
			description = "Recebe um objeto JSON contendo descrição, qtd. base, medida e preço do insumo para ser cadastrado.")
	@ApiResponsesCreated
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResponse> cadastrar(@Parameter(description = "Insumo para cadastrar.") @RequestBody @Valid Cadastro cadastro) {
		return insumoService.cadastrar(cadastro);
	}

	@Operation(summary = "Atualiza um insumo.",
			description = "Recebe um objeto JSON contendo identificador, descrição, qtd. base, medida e preço do insumo para ser atualizado")
	@ApiResponsesOk
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResponse> atualizar(@Parameter(description = "Insumo para atualizar.") @RequestBody @Valid Atualizacao atualizacao) throws ServiceException {
		return insumoService.atualizar(atualizacao);
	}

	@Operation(summary = "Exclui um insumo.", description = "Exclui a partir de seu identificador.")
	@ApiResponsesOk
	@DeleteMapping("excluir")
	public ResponseEntity<EntidadeResponse> excluir(
			@Parameter(description = "Identificador do insumo.") @RequestParam @NotNull @Min(1) @Max(9999999) Integer idInsumo) throws ServiceException {
		return insumoService.excluir(idInsumo);
	}

}