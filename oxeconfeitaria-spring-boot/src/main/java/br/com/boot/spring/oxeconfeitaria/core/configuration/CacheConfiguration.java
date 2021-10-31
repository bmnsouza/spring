package br.com.boot.spring.oxeconfeitaria.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.boot.spring.base.util.CacheUtil;

@Configuration
public class CacheConfiguration {

	@Bean
	public CacheUtil cacheUtil() {
		return new CacheUtil();
	}
	
	/**
	 * Limpa os caches de 6 em 6 horas
	 */
	@Scheduled(cron = "0 0 */6 * * *")
	private void clearCache() {
		cacheUtil().clearCache(CacheUtil.CACHE_OXECONFEITARIA);
	}

}
