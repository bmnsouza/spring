package br.com.oxeconfeitaria.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.oxeconfeitaria.util.response.EntidadeResponse.Response;

public class ResponseUtil {

	// Mensagens de retorno
	public static final String MENSAGEM_ERRO = "Não foi possível realizar a operação";
	public static final String MENSAGEM_OK = "Operação realizada com sucesso";

	public ResponseEntity<EntidadeResponse> responseSucesso(HttpStatus httpStatus, String msgUsuario, Object dados) {
		EntidadeResponse entidadeResponse = entidadeResponse("", msgUsuario, dados);
		return ResponseEntity.status(httpStatus).body(entidadeResponse);
	}

	public ResponseEntity<EntidadeResponse> responseSucesso(HttpStatus httpStatus, String msgUsuario) {
		return responseSucesso(httpStatus, msgUsuario, null);
	}

	public ResponseEntity<EntidadeResponse> responseErro(HttpStatus httpStatus, String msgTecnica, String msgUsuario) {
		EntidadeResponse entidadeResponse = entidadeResponse(msgTecnica, msgUsuario, null);
		return ResponseEntity.status(httpStatus).body(entidadeResponse);
	}

	public ResponseEntity<EntidadeResponse> resultErro(HttpStatus httpStatus, String msgUsuario) {
		return responseErro(httpStatus, "", msgUsuario);
	}

	public ResponseEntity<EntidadeResponse> responseErro(HttpStatus httpStatus, Throwable msgTecnica, String msgUsuario) {
		return responseErro(httpStatus, msgTecnica.toString(), msgUsuario);
	}

	private EntidadeResponse entidadeResponse(String msgTecnica, String msgUsuario, Object dados) {
		Response response = new EntidadeResponse().new Response();

		if (msgUsuario != null) {
			response.setMsgUsuario(msgUsuario);
		}

		if (msgTecnica != null) {
			response.setMsgTecnica(msgTecnica);
		}

		if (dados != null) {
			response.setDados(dados);
		}

		return new EntidadeResponse(response);
	}

}