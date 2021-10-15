package br.com.boot.spring.base.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class CacheUtil {
	
	/**
	 * Obtém o valor da key
	 * @param cdSistema
	 * @param nmSecao
	 * @param nmParametro
	 * @return String
	 */
	@Cacheable(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro}", unless = "#result == null")
	public String getCache(String cdSistema, String nmSecao, String nmParametro) {
		return null;
	}
	
	/**
	 * Cadastra ou atualiza o valor para a key
	 * @param cdSistema
	 * @param nmSecao
	 * @param nmParametro
	 * @param vlParametro
	 * @return String
	 */
	@CachePut(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro}", unless = "#vlParametro == null")
	public String setCache(String cdSistema, String nmSecao, String nmParametro, String vlParametro) {
		return vlParametro;
	}
	
	/**
	 * Exclui a key
	 * @param cdSistema
	 * @param nmSecao
	 * @param nmParametro
	 */
	@CacheEvict(value = "cache", key = "{#cdSistema, #nmSecao, #nmParametro}")
	public void delCache(String cdSistema, String nmSecao, String nmParametro) { }

}
