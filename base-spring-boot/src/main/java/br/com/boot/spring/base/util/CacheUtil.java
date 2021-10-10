package br.com.spring.boot.projeto.base.util;

import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class CacheUtil {
	
	@Cacheable(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro, #vlParametro}")
	public Map<String, Object> getFromCache(String cdSistema, String nmSecao, String nmParametro, String vlParametro) {
	    throw new ServiceException("Objeto não encontrado");
	}
	
	@CachePut(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro, #vlParametro}", unless = "#map == null")
	public Map<String, Object> populateCache(String cdSistema, String nmSecao, String nmParametro, String vlParametro, Map<String, Object> map) {
		return map;
	}
	
	@CacheEvict(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro, #vlParametro}")
	public void forgetAboutThis(String cdSistema, String nmSecao, String nmParametro, String vlParametro) {}
	
}