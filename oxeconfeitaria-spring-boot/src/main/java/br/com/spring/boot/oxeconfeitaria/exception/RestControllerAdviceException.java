package br.com.spring.boot.oxeconfeitaria.exception;

import static br.com.spring.boot.oxeconfeitaria.util.response.ResponseUtil.MENSAGEM_ERRO;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.PAYLOAD_TOO_LARGE;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.spring.boot.oxeconfeitaria.model.ResponseModel;
import br.com.spring.boot.oxeconfeitaria.util.response.EntidadeResponse;
import br.com.spring.boot.oxeconfeitaria.util.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerAdviceException {

	@Autowired
	private ResponseUtil responseUtil;

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public ResponseEntity<EntidadeResponse> handleAsyncRequestTimeout(AsyncRequestTimeoutException arte) {
		return responseUtil.responseErro(SERVICE_UNAVAILABLE, arte, "O tempo limite de resposta do servidor foi excedido. Tente novamente mais tarde.");
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<EntidadeResponse> handleBind(BindException be) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, be, MENSAGEM_ERRO);
	}

	// Captura exceções do RequestParam
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<EntidadeResponse> handleConstraintViolation(ConstraintViolationException cve) {
		StringBuilder msgUsuario = new StringBuilder();
		for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
			msgUsuario.append(violation.getPropertyPath().toString().split("[.]")[1]).append(": ").append(violation.getMessage());
			break;
		}
		return responseUtil.resultErro(BAD_REQUEST, msgUsuario.toString());
	}

	@ExceptionHandler(ConversionNotSupportedException.class)
	public ResponseEntity<EntidadeResponse> handleConversionNotSupported(ConversionNotSupportedException cnse) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, cnse, MENSAGEM_ERRO);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<EntidadeResponse> handleDataAccess(DataAccessException dae) {
		return responseUtil.responseErro(BAD_REQUEST, dae.getRootCause().getMessage(), MENSAGEM_ERRO);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<EntidadeResponse> handleDataIntegrityViolation(DataIntegrityViolationException dive) {
		return responseUtil.responseErro(CONFLICT, dive.getRootCause().getMessage(),
				"Não foi possível realizar a operação porque as informações enviadas violam as restrições de integridade de dados");
	}

	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<EntidadeResponse> handleDataTime(DateTimeException dte) {
		return responseUtil.responseErro(BAD_REQUEST, dte.getMessage(), "Não foi possível realizar a formatação da data");
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<EntidadeResponse> handleDataTimeParse(DateTimeParseException dtpe) {
		return responseUtil.responseErro(BAD_REQUEST, dtpe.getMessage(), 
				new StringBuilder("Não foi possível realizar o parse do texto ").append(dtpe.getParsedString()).toString());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<EntidadeResponse> handleException(Exception ex) {
		log.error("handlerException", ex);
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, ex, MENSAGEM_ERRO);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<EntidadeResponse> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException hmtnae) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, hmtnae, MENSAGEM_ERRO);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<EntidadeResponse> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException hmtnse) {
		StringBuilder msgUsuario = new StringBuilder("O tipo de mídia ").append(hmtnse.getContentType()).append(" não é suportado. Tipo de mídia suportado: ")
				.append(hmtnse.getSupportedMediaTypes().get(0));
		return responseUtil.resultErro(UNSUPPORTED_MEDIA_TYPE, msgUsuario.toString());
	}

	// Captura exceções do RequestBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<EntidadeResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre, JsonMappingException jme) throws Throwable {
		StringBuilder msgUsuario = new StringBuilder(jme.getPath().get(0).getFieldName()).append(": ");
		
		try {
			throw hmnre.getRootCause();
		} catch (DateTimeException dte) {
			msgUsuario.append("data inválida");
		} catch (Exception ex) {
			msgUsuario.append("valor inválido");
		}
		
		return responseUtil.resultErro(BAD_REQUEST, msgUsuario.toString());
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseEntity<EntidadeResponse> handleHttpMessageNotWritable(HttpMessageNotWritableException hmnwe) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, hmnwe, MENSAGEM_ERRO);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<EntidadeResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException hrmnse) {
		StringBuilder msgUsuario = new StringBuilder("O método ").append(hrmnse.getMethod()).append(" não é suportado para esta solicitação. Método suportado: ")
				.append(hrmnse.getSupportedMethods()[0]);
		return responseUtil.resultErro(METHOD_NOT_ALLOWED, msgUsuario.toString());
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<EntidadeResponse> handleJsonMapping(JsonMappingException jme) {
		return responseUtil.responseErro(BAD_REQUEST, jme.getCause(), MENSAGEM_ERRO);
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<EntidadeResponse> handleJsonProcessing(JsonProcessingException jpe) {
		return responseUtil.responseErro(BAD_REQUEST, jpe.getCause(), MENSAGEM_ERRO);
	}

	// Captura exceções de upload
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<EntidadeResponse> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException musee) {
		return responseUtil.responseErro(PAYLOAD_TOO_LARGE, musee.getMessage(), "Arquivo maior que o permitido");
	}
	
	// Captura exceções do RequestBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EntidadeResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException manve) {
		StringBuilder msgUsuario = new StringBuilder(manve.getBindingResult().getFieldError().getField()).append(": ")
				.append(manve.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		return responseUtil.resultErro(BAD_REQUEST, msgUsuario.toString());
	}

	// Captura exceções do RequestParam
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<EntidadeResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException matme) throws Throwable {
		StringBuilder msgUsuario = new StringBuilder(matme.getName()).append(": ");

		try {
			throw matme.getRootCause();
		} catch (DateTimeException dte) {
			msgUsuario.append("data inválida");
		} catch (Exception ex) {
			msgUsuario.append("deve ser do tipo ").append(matme.getRequiredType().getName());
		}
		
		return responseUtil.resultErro(BAD_REQUEST, msgUsuario.toString());
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<EntidadeResponse> handleMissingPathVariable(MissingPathVariableException mpve) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, mpve, MENSAGEM_ERRO);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<EntidadeResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException msrpe) {
		return responseUtil.resultErro(BAD_REQUEST, new StringBuilder(msrpe.getParameterName()).append(": parâmetro não encontrado").toString());
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<EntidadeResponse> handleMissingServletRequestPart(MissingServletRequestPartException msrpe) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, msrpe, MENSAGEM_ERRO);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<EntidadeResponse> handleService(ServiceException se) {
		return responseUtil.resultErro(BAD_REQUEST, se.getMessage());
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<EntidadeResponse> handleServletRequestBinding(ServletRequestBindingException srbe) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, srbe, MENSAGEM_ERRO);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<EntidadeResponse> handleTypeMismatch(TypeMismatchException tme) {
		return responseUtil.responseErro(INTERNAL_SERVER_ERROR, tme, MENSAGEM_ERRO);
	}

	@ExceptionHandler(UndeclaredThrowableException.class)
	public ResponseEntity<EntidadeResponse> handleUndeclaredThrowable(UndeclaredThrowableException ute) {
		return responseUtil.responseErro(BAD_REQUEST, ute.getCause(), MENSAGEM_ERRO);
	}

	// Captura exceções do WebClientResquest
	@ExceptionHandler(WebClientRequestException.class)
	public ResponseEntity<EntidadeResponse> handleWebClientRequest(WebClientRequestException wcre) throws JsonMappingException, JsonProcessingException {
		return responseUtil.responseErro(SERVICE_UNAVAILABLE, wcre.getMessage(), MENSAGEM_ERRO);
	}
	
	// Captura exceções do WebClientResponse
	@ExceptionHandler(WebClientResponseException.class)
	public ResponseEntity<?> handleWebClientResponse(WebClientResponseException wcre) throws JsonMappingException, JsonProcessingException, Exception {
		ResponseEntity<?> responseEntity = null;

		try {
			ResponseModel resultModel = ResponseModel.readValue(wcre.getResponseBodyAsString(), ResponseModel.class);
			responseEntity = new ResponseEntity<ResponseModel>(resultModel, wcre.getStatusCode());
		} catch (Exception ex) {
			responseEntity = responseUtil.responseErro(wcre.getStatusCode(), wcre.getMessage(), MENSAGEM_ERRO);
		}
		
		return responseEntity;
	}
	
}