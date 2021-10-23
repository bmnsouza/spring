package br.com.boot.spring.oxeconfeitaria.core.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.boot.spring.base.util.WebClientUtil;
import br.com.boot.spring.base.util.response.ResponseUtil;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public ResponseUtil responseUtil() {
		return new ResponseUtil();
	}

	@Bean
	public WebClientUtil webClientUtil() {
		return new WebClientUtil();
	}

}