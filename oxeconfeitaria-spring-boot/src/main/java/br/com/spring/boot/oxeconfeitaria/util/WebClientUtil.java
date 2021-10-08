package br.com.spring.boot.oxeconfeitaria.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.web.reactive.function.client.WebClient;

import br.com.spring.boot.oxeconfeitaria.model.TokenModel;

public class WebClientUtil {
	
	/**
	 * @param url
	 * @return WebClient sem Token
	 */
	public WebClient webClient(String url) {
		return WebClient.builder().baseUrl(url).build();
	}

	/**
	 * @param url
	 * @param path
	 * @param chaveToken
	 * @return WebClient com Token (Authorization no Header)
	 */
	public WebClient webClientToken(String url, String path, String chaveToken) {
		TokenModel tokenModel = obterToken(url, path, chaveToken);
		return WebClient.builder().baseUrl(url)
			.defaultHeader(AUTHORIZATION, new StringBuilder(tokenModel.getToken_type()).append(' ').append(tokenModel.getAccess_token()).toString())
			.build();
	}

	/**
	 * @param url
	 * @param path
	 * @param chaveToken
	 * @return TokenModel para a chave enviada
	 */
	private TokenModel obterToken(String url, String path, String chaveToken) {
		return webClient(url).get()
			.uri(uriBuilder -> uriBuilder.path(path)
					.queryParam("chave", chaveToken)
					.build())
			.retrieve()
			.bodyToMono(TokenModel.class)
			.block();
	}

}