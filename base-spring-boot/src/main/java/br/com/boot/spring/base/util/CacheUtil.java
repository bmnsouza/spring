package br.com.boot.spring.base.util;

import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class CacheUtil {
	
	@Cacheable(value = "cache", key = "{#sistema, #parametro}")
	public Map<String, Object> getFromCache(String sistema, String parametro) {
	    throw new ServiceException("Objeto não encontrado");
	}
	
	@CachePut(value = "cache", key = "{#sistema, #parametro}", unless = "#map == null")
	public Map<String, Object> populateCache(String sistema, String parametro, Map<String, Object> map) {
		return map;
	}
	
	@CacheEvict(value = "cache", key = "{#sistema, #parametro}")
	public void forgetAboutThis(String sistema, String parametro) {}
	
}
