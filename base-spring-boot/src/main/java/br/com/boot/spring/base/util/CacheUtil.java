package br.com.boot.spring.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheUtil {
	
	// Constantes que identificam os nomes dos caches
	public static final String CACHE_BIBLIOTECA = "cache-biblioteca";
	public static final String CACHE_OXECONFEITARIA = "cache-oxeconfeitaria"; 
	
	@Autowired
    private CacheManager cacheManager;
	
	/**
	 * Obtém o valor da key
	 * @param nmProjeto
	 * @param nmParametro
	 * @return String
	 */
	@Cacheable(value = "parametro", key = "{#nmProjeto, #nmParametro}", unless = "#result == null")
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
	@CachePut(value = "parametro", key = "{#nmProjeto, #nmParametro}", unless = "#vlParametro == null")
	public String setCache(String nmProjeto, String nmParametro, String vlParametro) {
		return vlParametro;
	}
	
	/**
	 * Exclui a key
	 * @param nmProjeto
	 * @param nmParametro
	 */
	@CacheEvict(value = "parametro", key = "{#nmProjeto, #nmParametro}")
	public void delCache(String nmProjeto, String nmParametro) {}


	/**
	 * Realiza a limpeza do cache com o nome passado por parâmetro
	 */
	public void clearCache(String name) {
		cacheManager.getCache(name).clear();
		log.info(new StringBuilder(">>>>> Limpeza periódica do cache: ").append(name).append(" <<<<<").toString());
	}

}
