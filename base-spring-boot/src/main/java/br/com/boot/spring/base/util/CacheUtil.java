package br.com.boot.spring.base.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class CacheUtil {
	
	@Cacheable(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro}", unless = "#result == null")
	public String getCache(String cdSistema, String nmSecao, String nmParametro) {
		return null;
	}
	
	@CachePut(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro}", unless = "#vlParametro == null")
	public String setCache(String cdSistema, String nmSecao, String nmParametro, String vlParametro) {
		return vlParametro;
	}
	
	@CacheEvict(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro}")
	public void delCache(String cdSistema, String nmSecao, String nmParametro) { }

}
