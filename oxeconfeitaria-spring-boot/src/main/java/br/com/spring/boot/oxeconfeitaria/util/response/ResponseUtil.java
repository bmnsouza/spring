package br.com.spring.boot.oxeconfeitaria.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.spring.boot.oxeconfeitaria.util.response.EntidadeResponse.Response;

@Component
public class ResponseUtil {
	
	private final String MSG_USUARIO_SUCESSO = "Operação realizada com sucesso";

	public ResponseEntity<EntidadeResponse> responseSucesso(HttpStatus httpStatus) {
		return responseSucesso(httpStatus, MSG_USUARIO_SUCESSO, null);
	}

	public ResponseEntity<EntidadeResponse> responseSucesso(HttpStatus httpStatus, Object dados) {
		return responseSucesso(httpStatus, MSG_USUARIO_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResponse> responseSucesso(HttpStatus httpStatus, String msgUsuario, Object dados) {
		return ResponseEntity.status(httpStatus).body(entidadeResponse(null, msgUsuario, dados));
	}

	public ResponseEntity<EntidadeResponse> responseErro(HttpStatus httpStatus, String msgTecnica, String msgUsuario) {
		return ResponseEntity.status(httpStatus).body(entidadeResponse(msgTecnica, msgUsuario, null));
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