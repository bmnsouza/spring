package br.com.boot.spring.base.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.web.reactive.function.client.WebClient;

import br.com.boot.spring.base.model.TokenModel;

public class WebClientUtil {
	
	/**
	 * @param urlGateway
	 * @return WebClient sem Token
	 */
	public WebClient webClient(String urlGateway) {
		return WebClient.builder().baseUrl(urlGateway).build();
	}

	/**
	 * @param urlGateway
	 * @param chaveToken
	 * @return WebClient com Token (Authorization no Header)
	 */
	public WebClient webClientToken(String urlGateway, String chaveToken) {
		TokenModel tokenModel = obterToken(urlGateway, chaveToken);
		return WebClient.builder().baseUrl(urlGateway)
			.defaultHeader(AUTHORIZATION, new StringBuilder(tokenModel.getToken_type()).append(' ').append(tokenModel.getAccess_token()).toString())
			.build();
	}

	/**
	 * @param urlGateway
	 * @param chaveToken
	 * @return TokenModel para a chave enviada
	 */
	private TokenModel obterToken(String urlGateway, String chaveToken) {
		return webClient(urlGateway).get()
			.uri(uriBuilder -> uriBuilder.path("/corporativo/v1/obterToken")
					.queryParam("chave", chaveToken)
					.build())
			.retrieve()
			.bodyToMono(TokenModel.class)
			.block();
	}

}