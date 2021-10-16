package br.com.boot.spring.base.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class CacheUtil {
	
	/**
	 * Obtém o valor da key
	 * @param nmProjeto
	 * @param nmParametro
	 * @return String
	 */
	@Cacheable(value = "cache", key = "{#nmProjeto, #nmParametro}", unless = "#result == null")
	public String getCache(String nmProjeto, String nmParametro) {
		return null;
	}
	
	/**
	 * Cadastra ou atualiza o valor da key
	 * @param nmProjeto
	 * @param nmParametro
	 * @param vlParametro
	 * @return String
	 */
	@CachePut(value = "cache", key = "{#nmProjeto, #nmParametro}", unless = "#vlParametro == null")
	public String setCache(String nmProjeto, String nmParametro, String vlParametro) {
		return vlParametro;
	}
	
	/**
	 * Exclui a key
	 * @param nmProjeto
	 * @param nmParametro
	 */
	@CacheEvict(value = "cache", key = "{#nmProjeto, #nmParametro}")
	public void delCache(String nmProjeto, String nmParametro) { }

}
