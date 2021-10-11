package br.com.boot.spring.biblioteca.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
	
	@Value("${project.version}")
	private String version;
	
	@Bean
	public GroupedOpenApi groupApisAutor() {
		return GroupedOpenApi.builder().group("autor")
				.pathsToMatch("/v1/autor/**")
				.addOpenApiCustomiser(openApi -> openApi.setInfo(new Info().title("Autor").version(version))).build();
	}

	@Bean
	public GroupedOpenApi groupApisEditora() {
		return GroupedOpenApi.builder().group("editora")
				.pathsToMatch("/v1/editora/**")
				.addOpenApiCustomiser(openApi -> openApi.setInfo(new Info().title("Editora").version(version))).build();
	}

	@Bean
	public GroupedOpenApi groupApisLocal() {
		return GroupedOpenApi.builder().group("local")
				.pathsToMatch("/v1/local/**")
				.addOpenApiCustomiser(openApi -> openApi.setInfo(new Info().title("Local").version(version))).build();
	}

}
