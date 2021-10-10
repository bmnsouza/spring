package br.com.spring.boot.oxeconfeitaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class OxeconfeitariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxeconfeitariaApplication.class, args);
	}
	
}