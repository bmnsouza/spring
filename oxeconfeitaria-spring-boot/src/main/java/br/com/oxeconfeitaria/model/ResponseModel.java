package br.com.oxeconfeitaria.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
public class ResponseModel {

	private Response response;

	@Getter
	public class Response {

		private String msgTecnica;
	
		private String msgUsuario;
	
		private Object dados;

	}
	
    public static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException {
        return new ObjectMapper().readValue(content, valueType);
    }
    
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
        return new ObjectMapper().convertValue(fromValue, toValueType);
    }

}