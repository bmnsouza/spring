package br.com.spring.boot.oxeconfeitaria.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.spring.boot.oxeconfeitaria.util.response.EntidadeResponse.Response;

public class ResponseUtil {

	public static final String MSG_USUARIO_SUCESSO = "Operação realizada com sucesso";
	
	public ResponseEntity<EntidadeResponse> responseSucesso(HttpStatus httpStatus, Object dados) {
		EntidadeResponse entidadeResponse = entidadeResponse("", MSG_USUARIO_SUCESSO, dados);
		return ResponseEntity.status(httpStatus).body(entidadeResponse);
	}

	public ResponseEntity<EntidadeResponse> responseErro(HttpStatus httpStatus, String msgTecnica, String msgUsuario) {
		EntidadeResponse entidadeResponse = entidadeResponse(msgTecnica, msgUsuario, null);
		return ResponseEntity.status(httpStatus).body(entidadeResponse);
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