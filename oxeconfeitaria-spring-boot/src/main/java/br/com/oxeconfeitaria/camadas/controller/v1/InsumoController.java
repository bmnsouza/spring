package br.se.oxeconfeitaria.camadas.controller.v1;

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

import br.se.oxeconfeitaria.annotation.DefaultApiResponsesCreated;
import br.se.oxeconfeitaria.annotation.DefaultApiResponsesOk;
import br.se.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Atualizacao;
import br.se.oxeconfeitaria.camadas.entity.dto.InsumoDTO.Request.Cadastro;
import br.se.oxeconfeitaria.camadas.service.InsumoService;
import br.se.oxeconfeitaria.exception.ServiceException;
import br.se.oxeconfeitaria.util.response.EntidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("v1/insumo")
@Tag(name = "insumo", description = "APIs de Insumo")
public class InsumoControllerV1 {

	@Autowired
	private InsumoService insumoService;

	/**
	 * Busca um insumo a partir de seu identificador ou vários insumos através de sua descrição ou todos se nenhum dos parâmetros for informado.
	 * 
	 * @author bmnsouza
	 * @since 15/05/2021
	 * 
	 * @param idInsumo - Identificação única do insumo.
	 * @param dsInsumo - Descrição do insumo.
	 * 
	 * @return ResponseEntity com um objeto {@link EntidadeResponse}
	 */
	@Operation(summary = "Busca um insumo a partir de seu identificador ou vários insumos através de sua descrição.",
			description = "Busca todos se nenhum dos parâmetros for informado.")
	@DefaultApiResponsesOk
	@GetMapping("buscar")
	public ResponseEntity<EntidadeResponse> buscar(
			@Parameter(description = "Identificador do insumo.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer idInsumo,
			@Parameter(description = "Descrição do insumo.") @RequestParam(required = false) @Size(min = 3, max = 50) String dsInsumo) {
		return insumoService.buscar(idInsumo, dsInsumo);
	}
	
	/**
	 * Cadastra um insumo.
	 *
	 * @author bmnsouza
	 * @since 15/05/2021
	 * 
	 * @param cadastro - Objeto contendo descrição, qtd. base, medida e preço do insumo para ser cadastrado.
	 * 
	 * @return ResponseEntity com um objeto {@link EntidadeResponse}
	 */
	@Operation(summary = "Cadastra um insumo.",
			description = "Recebe um objeto JSON contendo descrição, qtd. base, medida e preço do insumo para ser cadastrado.")
	@DefaultApiResponsesCreated
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResponse> cadastrar(@Parameter(description = "Insumo para cadastrar.") @RequestBody @Valid Cadastro cadastro) {
		return insumoService.cadastrar(cadastro);
	}

	/**
	 * Atualiza um insumo a partir de seu identificador.
	 *
	 * @author bmnsouza
	 * @since 15/05/2021
	 * 
	 * @param atualizacao - Objeto contendo identificador, descrição, qtd. base, medida e preço do insumo para ser atualizado.
	 * 
	 * @return ResponseEntity com um objeto {@link EntidadeResponse}
	 */
	@Operation(summary = "Atualiza um insumo.",
			description = "Recebe um objeto JSON contendo identificador, descrição, qtd. base, medida e preço do insumo para ser atualizado")
	@DefaultApiResponsesOk
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResponse> atualizar(@Parameter(description = "Insumo para atualizar.") @RequestBody @Valid Atualizacao atualizacao) throws ServiceException {
		return insumoService.atualizar(atualizacao);
	}

	/**
	 * Exclui um insumo a partir de seu identificador.
	 *
	 * @author bmnsouza
	 * @since 15/05/2021
	 * 
	 * @param idInsumo - Identificador do insumo.
	 * 
	 * @return ResponseEntity com um objeto {@link EntidadeResponse}
	 */
	@Operation(summary = "Exclui um insumo.", description = "Exclui a partir de seu identificador.")
	@DefaultApiResponsesOk
	@DeleteMapping("excluir")
	public ResponseEntity<EntidadeResponse> excluir(
			@Parameter(description = "Identificador do insumo.") @RequestParam @NotNull @Min(1) @Max(9999999) Integer idInsumo) throws ServiceException {
		return insumoService.excluir(idInsumo);
	}

}