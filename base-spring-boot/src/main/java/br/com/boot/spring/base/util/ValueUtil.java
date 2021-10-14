package br.com.boot.spring.base.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.boot.spring.base.model.ResponseModel;

public class ValueUtil {

    public static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException {
        return new ObjectMapper().readValue(content, valueType);
    }
    
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
        return new ObjectMapper().convertValue(fromValue, toValueType);
    }

	public static <T> T convertValue(ResponseModel responseModel, Class<T> toValueType) {
		List<?> dados = (List<?>) responseModel.getResponse().getDados();
		List<?> conteudo = (List<?>) dados.get(0);
		
		return convertValue(conteudo.get(0), toValueType);
	}

}
