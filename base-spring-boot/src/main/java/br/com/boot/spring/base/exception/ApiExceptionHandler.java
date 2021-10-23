package br.com.boot.spring.base.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.PAYLOAD_TOO_LARGE;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.boot.spring.base.model.ResponseModel;
import br.com.boot.spring.base.util.ValueUtil;
import br.com.boot.spring.base.util.response.EntidadeResponse;
import br.com.boot.spring.base.util.response.ResponseUtil;
import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que possui os tratamentos de exceções para todos os projetos.
 * <br>
 * Caso algum projeto necessite de um tratamento diferenciado para um dos métodos existentes, deverá sobrescrevê-lo.
 */
@Slf4j
public class ApiExceptionHandler {

	private static final String MSG_USUARIO_CAMPO_INVALIDO = "Requisição possui campo inválido. Faça o preenchimento correto e tente novamente";
	private static final String MSG_USUARIO_ERRO = "Não foi possível realizar a operação";

	@Autowired
	private ResponseUtil responseUtil;
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<EntidadeResponse> handle(Exception e) {
		log.error("handle", e);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, e.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	protected ResponseEntity<EntidadeResponse> handleAsyncRequestTimeout(AsyncRequestTimeoutException arte) {
		return responseUtil.responseErro(SERVICE_UNAVAILABLE, arte.toString(), "O tempo limite de resposta do servidor foi excedido. Tente novamente mais tarde");
	}

	@ExceptionHandler(BindException.class)
	protected ResponseEntity<EntidadeResponse> handleBind(BindException be) {
		log.error("handleBind", be);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, be.toString(), MSG_USUARIO_ERRO);
	}

	// Captura exceções do RequestParam
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<EntidadeResponse> handleConstraintViolation(ConstraintViolationException cve) {
		String msgTecnica = cve.getConstraintViolations().stream()
				.map(cv -> new StringBuilder(cv.getPropertyPath().toString().split("[.]")[1]).append(": ").append(cv.getMessage()))
				.findFirst().get().toString();
		
		return responseUtil.responseErro(BAD_REQUEST, msgTecnica, MSG_USUARIO_CAMPO_INVALIDO);
	}

	@ExceptionHandler(ConversionNotSupportedException.class)
	protected ResponseEntity<EntidadeResponse> handleConversionNotSupported(ConversionNotSupportedException cnse) {
		log.error("handleConversionNotSupported", cnse);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, cnse.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<EntidadeResponse> handleDataAccess(DataAccessException dae) {
		log.error("handleDataAccess", dae);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, dae.getMessage(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<EntidadeResponse> handleDataIntegrityViolation(DataIntegrityViolationException dive) {
		return responseUtil.responseErro(CONFLICT, dive.getRootCause().getMessage(), "As informações enviadas violam as restrições de integridade de dados");
	}

	@ExceptionHandler(DateTimeException.class)
	protected ResponseEntity<EntidadeResponse> handleDataTime(DateTimeException dte) {
		return responseUtil.responseErro(BAD_REQUEST, dte.getMessage(), "Não foi possível realizar a formatação da data");
	}

	@ExceptionHandler(DateTimeParseException.class)
	protected ResponseEntity<EntidadeResponse> handleDataTimeParse(DateTimeParseException dtpe) {
		return responseUtil.responseErro(BAD_REQUEST, dtpe.getMessage(), 
				new StringBuilder("Não foi possível realizar o parse do texto ").append(dtpe.getParsedString()).toString());
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	protected ResponseEntity<EntidadeResponse> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException hmtnae) {
		log.error("handleHttpMediaTypeNotAcceptable", hmtnae);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, hmtnae.toString(), MSG_USUARIO_ERRO);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<EntidadeResponse> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException hmtnse) {
		StringBuilder msgUsuario = new StringBuilder("O tipo de mídia ").append(hmtnse.getContentType()).append(" não é suportado. Tipo de mídia suportado: ")
				.append(hmtnse.getSupportedMediaTypes().get(0));
		return responseUtil.responseErro(UNSUPPORTED_MEDIA_TYPE, hmtnse.toString(), msgUsuario.toString());
	}

	// Captura exceções do RequestBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<EntidadeResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre, JsonMappingException jme) throws Throwable {
		StringBuilder msgTecnica = new StringBuilder(jme.getPath().get(0).getFieldName()).append(": ");
		
		try {
			throw hmnre.getRootCause();
		} catch (DateTimeException dte) {
			msgTecnica.append("data inválida");
		} catch (Exception e) {
			msgTecnica.append("valor inválido");
		}
		
		return responseUtil.responseErro(BAD_REQUEST, msgTecnica.toString(), MSG_USUARIO_CAMPO_INVALIDO);
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	protected ResponseEntity<EntidadeResponse> handleHttpMessageNotWritable(HttpMessageNotWritableException hmnwe) {
		log.error("handleHttpMessageNotWritable", hmnwe);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, hmnwe.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<EntidadeResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException hrmnse) {
		StringBuilder msgUsuario = new StringBuilder("O método ").append(hrmnse.getMethod()).append(" não é suportado para esta solicitação. Método suportado: ")
				.append(hrmnse.getSupportedMethods()[0]);
		return responseUtil.responseErro(METHOD_NOT_ALLOWED, hrmnse.toString(), msgUsuario.toString());
	}
	
	@ExceptionHandler(JsonMappingException.class)
	protected ResponseEntity<EntidadeResponse> handleJsonMapping(JsonMappingException jme) {
		return responseUtil.responseErro(BAD_REQUEST, jme.getCause().toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(JsonProcessingException.class)
	protected ResponseEntity<EntidadeResponse> handleJsonProcessing(JsonProcessingException jpe) {
		return responseUtil.responseErro(BAD_REQUEST, jpe.getCause().toString(), MSG_USUARIO_ERRO);
	}

	// Captura exceções de upload
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	protected ResponseEntity<EntidadeResponse> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException musee) {
		return responseUtil.responseErro(PAYLOAD_TOO_LARGE, musee.getMessage(), "Upload do arquivo excede o tamanho máximo permitido");
	}
		
	// Captura exceções do RequestBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<EntidadeResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException manve) {
		StringBuilder msgTecnica = new StringBuilder(manve.getBindingResult().getFieldError().getField()).append(": ")
				.append(manve.getBindingResult().getFieldError().getDefaultMessage());
		return responseUtil.responseErro(BAD_REQUEST, msgTecnica.toString(), MSG_USUARIO_CAMPO_INVALIDO);
	}

	// Captura exceções do RequestParam
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<EntidadeResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException matme) throws Throwable {
		StringBuilder msgTecnica = new StringBuilder(matme.getName()).append(": ");

		try {
			throw matme.getRootCause();
		} catch (DateTimeException dte) {
			msgTecnica.append("data inválida");
		} catch (Exception e) {
			msgTecnica.append("deve ser do tipo ").append(matme.getRequiredType().getName());
		}
		
		return responseUtil.responseErro(BAD_REQUEST, msgTecnica.toString(), MSG_USUARIO_CAMPO_INVALIDO);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	protected ResponseEntity<EntidadeResponse> handleMissingPathVariable(MissingPathVariableException mpve) {
		return responseUtil.responseErro(EXPECTATION_FAILED, mpve.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<EntidadeResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException msrpe) {
		return responseUtil.responseErro(BAD_REQUEST, new StringBuilder(msrpe.getParameterName()).append(": parâmetro não encontrado").toString(), MSG_USUARIO_CAMPO_INVALIDO);
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	protected ResponseEntity<EntidadeResponse> handleMissingServletRequestPart(MissingServletRequestPartException msrpe) {
		return responseUtil.responseErro(EXPECTATION_FAILED, msrpe.toString(), MSG_USUARIO_ERRO);
	}
	
	@ExceptionHandler(RedisException.class)
	protected ResponseEntity<EntidadeResponse> handleRedis(RedisException re) {
		log.error("handleRedis", re);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, re.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(SerializationException.class)
	protected ResponseEntity<EntidadeResponse> handleSerialization(SerializationException se) {
		log.error("handleSerialization", se);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, se.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(ServiceException.class)
	protected ResponseEntity<EntidadeResponse> handleService(ServiceException se) {
		return responseUtil.responseErro(BAD_REQUEST, se.toString(), se.getMessage());
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	protected ResponseEntity<EntidadeResponse> handleServletRequestBinding(ServletRequestBindingException srbe) {
		log.error("handleServletRequestBinding", srbe);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, srbe.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(TypeMismatchException.class)
	protected ResponseEntity<EntidadeResponse> handleTypeMismatch(TypeMismatchException tme) {
		log.error("handleTypeMismatch", tme);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, tme.toString(), MSG_USUARIO_ERRO);
	}

	@ExceptionHandler(UndeclaredThrowableException.class)
	protected ResponseEntity<EntidadeResponse> handleUndeclaredThrowable(UndeclaredThrowableException ute) {
		log.error("handleUndeclaredThrowable", ute);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, ute.getCause().toString(), MSG_USUARIO_ERRO);
	}

	// Captura exceções do WebClientResquest
	@ExceptionHandler(WebClientRequestException.class)
	protected ResponseEntity<EntidadeResponse> handleWebClientRequest(WebClientRequestException wcre) throws JsonMappingException, JsonProcessingException {
		log.error("handleWebClientRequest", wcre);
		return responseUtil.responseErro(SERVICE_UNAVAILABLE, wcre.getMessage(), MSG_USUARIO_ERRO);
	}
	
	// Captura exceções do WebClientResponse
	@ExceptionHandler(WebClientResponseException.class)
	protected ResponseEntity<?> handleWebClientResponse(WebClientResponseException wcre) throws JsonMappingException, JsonProcessingException, Exception {
		log.error("handleWebClientResponse", wcre);

		ResponseEntity<?> responseEntity = null;

		try {
			responseEntity = new ResponseEntity<ResponseModel>(ValueUtil.readValue(wcre.getResponseBodyAsString(), ResponseModel.class), wcre.getStatusCode());
		} catch (Exception e) {
			responseEntity = responseUtil.responseErro(wcre.getStatusCode(), wcre.getMessage(), MSG_USUARIO_ERRO);
		}
		
		return responseEntity;
	}
	
}