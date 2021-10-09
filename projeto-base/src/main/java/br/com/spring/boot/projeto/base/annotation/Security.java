package br.com.spring.boot.projeto.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SecurityScheme(
		name = "oauth_2_0", 
		type = SecuritySchemeType.OAUTH2, 
		flows = @OAuthFlows(clientCredentials = @OAuthFlow(tokenUrl = "https://apihml.sefaz.se.gov.br:8443/auth/oauth/v2/token"))
)
public @interface Security {}
