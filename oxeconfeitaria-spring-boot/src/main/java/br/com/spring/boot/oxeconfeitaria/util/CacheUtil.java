package br.com.spring.boot.oxeconfeitaria.util;

import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class CacheUtil {
	
	@Cacheable(value = "cache", key = "{#key, #value}")
	public Map<String, Object> getFromCache(String key, String value) {
	    throw new ServiceException("Objeto não encontrado no Redis");
	}
	
	@CachePut(value = "cache", key = "{#key, #value}", unless = "#map == null")
	public Map<String, Object> populateCache(String key, String value, Map<String, Object> map) {
		return map;
	}
	
	@CacheEvict(value = "cache", key = "{#key, #value}")
	public void forgetAboutThis(String key, String value) {}
	
}