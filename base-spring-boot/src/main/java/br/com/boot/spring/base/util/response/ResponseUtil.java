package br.com.boot.spring.base.util.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.boot.spring.base.util.response.EntidadeResponse.Paginacao;
import br.com.boot.spring.base.util.response.EntidadeResponse.Response;

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
			if (dados instanceof Slice) {
				dados = obterDados((Slice<?>)dados);
			} else if (dados instanceof ArrayList) {
				dados = obterDados((ArrayList<?>)dados);
			}
			
			response.setDados(dados);
		}

		return new EntidadeResponse(response);
	}

	private Object obterDados(Slice<?> slice) {
		Object dados = new HashMap<>();

		// Verifica se o objeto possui conteúdo
		if (slice.getContent().size() > 0) {
			
			// Adiciona o conteúdo à lista
			List<Object> lista = new ArrayList<Object>();
			lista.add(slice.getContent());
			
			// Obtém os dados da paginação
			Paginacao paginacao = new EntidadeResponse().new Paginacao();
			paginacao.setPagina(slice.getNumber());
			paginacao.setElementos(slice.getNumberOfElements());
			paginacao.setConteudo(slice.hasContent());
			paginacao.setAnterior(slice.hasPrevious());
			paginacao.setProxima(slice.hasNext());
			paginacao.setPrimeira(slice.isFirst());
			paginacao.setUltima(slice.isLast());
			
			// Adiciona paginacao à lista
			Map<String, Object> hashPaginacao = new HashMap<>();
			hashPaginacao.put("paginacao", paginacao);
			lista.add(hashPaginacao);

			dados = lista;
		}

		return dados;
	}

	private Object obterDados(ArrayList<?> arrayList) {
		Object dados = new HashMap<>();

		// Verifica se o objeto possui conteúdo
		if (arrayList.size() > 0) {
			dados = arrayList;
		}

		return dados;
	}
}