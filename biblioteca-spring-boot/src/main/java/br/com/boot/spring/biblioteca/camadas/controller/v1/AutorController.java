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

import br.com.boot.spring.biblioteca.camadas.entity.dto.AutorDTO.Request.Atualizacao;
import br.com.boot.spring.biblioteca.camadas.entity.dto.AutorDTO.Request.Cadastro;
import br.com.boot.spring.biblioteca.camadas.service.AutorService;
import br.com.boot.spring.base.annotation.ApiResponsesCreated;
import br.com.boot.spring.base.annotation.ApiResponsesOk;
import br.com.boot.spring.base.exception.ServiceException;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("v1/autor")
@Tag(name = "autor", description = "APIs de Autor")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Operation(summary = "Busca autor pelo código e/ou nome.",
			description = "Se nenhum parâmetro for informado, serão retornados todos os registros.<br><br>"
					+ "Esta API utiliza query nativa.")
	@ApiResponsesOk
	@GetMapping("buscar")
	public ResponseEntity<EntidadeResponse> buscar(
			@Parameter(description = "Código do autor.") @RequestParam(required = false) @Min(1) @Max(999999999) Integer codigo,
			@Parameter(description = "Primeior nome do autor.") @RequestParam(required = false) @Size(min = 3, max = 20) String primeiroNome,
			@Parameter(description = "Incial do meio do nome do autor.") @RequestParam(required = false) @Size(min = 1, max = 1) String inicialMeioNome,
			@Parameter(description = "ùltimo nome do autor.") @RequestParam(required = false) @Size(min = 3, max = 20) String ultimoNome) {
		return autorService.buscar(codigo, primeiroNome, inicialMeioNome, ultimoNome);
	}
	
	@Operation(summary = "Cadastra um autor.",
			description = "Recebe um objeto JSON contendo código e nome do autor para ser cadastrado.")
	@ApiResponsesCreated
	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResponse> cadastrar(@Parameter(description = "Autor para cadastrar.") @RequestBody @Valid Cadastro cadastro) throws ServiceException {
		return autorService.cadastrar(cadastro);
	}

	@Operation(summary = "Atualiza um autor.",
			description = "Recebe um objeto JSON contendo código e nome do autor para ser atualizado.")
	@ApiResponsesOk
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResponse> atualizar(@Parameter(description = "Autor para atualizar.") @RequestBody @Valid Atualizacao atualizacao) throws ServiceException {
		return autorService.atualizar(atualizacao);
	}

	@Operation(summary = "Exclui um autor.", description = "Exclui a partir do código.")
	@ApiResponsesOk
	@DeleteMapping("excluir")
	public ResponseEntity<EntidadeResponse> excluir(
			@Parameter(description = "Código do autor.") @RequestParam @NotNull @Min(1) @Max(9999999) Integer codigo) throws ServiceException {
		return autorService.excluir(codigo);
	}

}
