package br.com.boot.spring.biblioteca.api.controller.v1;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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

import br.com.boot.spring.base.annotation.ApiResponsesCreated;
import br.com.boot.spring.base.annotation.ApiResponsesOk;
import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.biblioteca.domain.entity.dto.LocalDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.domain.entity.dto.LocalDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.domain.service.LocalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("v1/local")
@Tag(name = "local", description = "APIs de Local")
@AllArgsConstructor
public class LocalController {

	private final LocalService localService;

	@Operation(summary = "Busca local pelo código e/ou nome.",
			description = "Se nenhum parâmetro for informado, serão retornados todos os registros paginados.")
	@ApiResponsesOk
	@GetMapping("buscar")
	public ResponseEntity<EntidadeResponse> buscar(
			@Parameter(description = "Código do local.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer codigo,
			@Parameter(description = "Nome do local.") @RequestParam(required = false) @Size(min = 3, max = 50) String nome,
			@Parameter(description = "Número da página desejada na paginação.") @RequestParam(defaultValue = "0") @PositiveOrZero Integer pagina) {
		return localService.buscar(codigo, nome, pagina);
	}
	
	@Operation(summary = "Cadastra um local.",
			description = "Recebe um objeto JSON contendo código e nome do local para ser cadastrado.")
	@ApiResponsesCreated
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResponse> cadastrar(@Parameter(description = "Local para cadastrar.") @RequestBody @Valid Cadastro cadastro) throws ServiceException {
		return localService.cadastrar(cadastro);
	}

	@Operation(summary = "Atualiza um local.",
			description = "Recebe um objeto JSON contendo código e nome do local para ser atualizado.")
	@ApiResponsesOk
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResponse> atualizar(@Parameter(description = "Local para atualizar.") @RequestBody @Valid Atualizacao atualizacao) throws ServiceException {
		return localService.atualizar(atualizacao);
	}

	@Operation(summary = "Exclui um local.", description = "Exclui a partir do código.")
	@ApiResponsesOk
	@DeleteMapping("excluir")
	public ResponseEntity<EntidadeResponse> excluir(
			@Parameter(description = "Código do local.") @RequestParam @NotNull @Min(1) @Max(9999999) Integer codigo) throws ServiceException {
		return localService.excluir(codigo);
	}

}
