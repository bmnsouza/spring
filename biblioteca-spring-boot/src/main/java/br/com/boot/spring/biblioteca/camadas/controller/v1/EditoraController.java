package br.com.boot.spring.biblioteca.camadas.controller.v1;

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

import br.com.boot.spring.biblioteca.camadas.entity.dto.EditoraDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.EditoraDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.camadas.service.EditoraService;
import br.com.boot.spring.base.annotation.ApiResponsesCreated;
import br.com.boot.spring.base.annotation.ApiResponsesOk;
import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("v1/editora")
@Tag(name = "editora", description = "APIs de Editora")
public class EditoraController {

	@Autowired
	private EditoraService editoraService;

	@Operation(summary = "Busca editora pelo identificador ou descrição.",
			description = "Se nenhum parâmetro for informado, serão retornados todos os registros.<br><br>"
					+ "Esta API utiliza JPA.")
	@ApiResponsesOk
	@GetMapping("buscar")
	public ResponseEntity<EntidadeResponse> buscar(
			@Parameter(description = "Código da editora.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer codigo,
			@Parameter(description = "Nome da editora.") @RequestParam(required = false) @Size(min = 3, max = 50) String nome) {
		return editoraService.buscar(codigo, nome);
	}
	
	@Operation(summary = "Busca editora pelo identificador e/ou descrição.",
			description = "Se nenhum parâmetro for informado, serão retornados todos os registros.<br><br>"
					+ "Esta API utiliza query nativa.")
	@ApiResponsesOk
	@GetMapping("buscarNativeQuery")
	public ResponseEntity<EntidadeResponse> buscarNativeQuery(
			@Parameter(description = "Código da editora.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer codigo,
			@Parameter(description = "Nome do editora.") @RequestParam(required = false) @Size(min = 3, max = 50) String nome) {
		return editoraService.buscarNativeQuery(codigo, nome);
	}
	
	@Operation(summary = "Cadastra um editora.",
			description = "Recebe um objeto JSON contendo código e nome da editora para ser cadastrada.")
	@ApiResponsesCreated
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResponse> cadastrar(@Parameter(description = "Editora para cadastrar.") @RequestBody @Valid Cadastro cadastro) {
		return editoraService.cadastrar(cadastro);
	}

	@Operation(summary = "Atualiza uma editora.",
			description = "Recebe um objeto JSON contendo código e nome da editora para ser atualizada.")
	@ApiResponsesOk
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResponse> atualizar(@Parameter(description = "Editora para atualizar.") @RequestBody @Valid Atualizacao atualizacao) throws ServiceException {
		return editoraService.atualizar(atualizacao);
	}

	@Operation(summary = "Exclui uma editora.", description = "Exclui a partir do código.")
	@ApiResponsesOk
	@DeleteMapping("excluir")
	public ResponseEntity<EntidadeResponse> excluir(
			@Parameter(description = "Código da editora.") @RequestParam @NotNull @Min(1) @Max(9999999) Integer codigo) throws ServiceException {
		return editoraService.excluir(codigo);
	}

}
