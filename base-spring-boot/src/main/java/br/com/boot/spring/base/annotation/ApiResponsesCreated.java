package br.com.spring.boot.projeto.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.spring.boot.projeto.base.util.response.EntidadeResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
		 @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso",
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "401", description = "Não autorizado. Isso pode acontecer pelos seguintes motivos: 1 - Token inválido ou expirado. Solicite um novo token. "
		 		+ "2 - Dados de acesso do usuário são inválidos ou estão expirados. Consulte o suporte da SEFAZ em apoio.tecnologico@sefaz.se.gov.br", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "403", description = "Proibido. O complemento da mensagem varia de acordo com a operação", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "404", description = "Não encontrado", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "405", description = "Método não suportado", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "429", description = "Não autorizado. Limite de uso excedido", 
				 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) }),
		 @ApiResponse(responseCode = "500", description = "Erro genérico. A mensagem varia de acordo com a operação", 
		 		 content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EntidadeResponse.class)) })
})
public @interface ApiResponsesCreated {}