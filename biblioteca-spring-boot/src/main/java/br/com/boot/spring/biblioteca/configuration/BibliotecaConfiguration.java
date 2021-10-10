package br.com.boot.spring.biblioteca.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = BibliotecaConfiguration.ENTITY_MANAGER_FACTORY,
	transactionManagerRef = BibliotecaConfiguration.TRANSACTION_MANAGER,
	basePackages = { "br.com.boot.spring.biblioteca.camadas.repository" }
)
public class BibliotecaConfiguration {

	// Constantes
	public static final String DATA_SOURCE = "bibliotecaDataSource";
	public static final String ENTITY_MANAGER_FACTORY = "bibliotecaEntityManagerFactory";
	public static final String TRANSACTION_MANAGER = "bibliotecaTransactionManager";

	@Primary
	@Bean(DATA_SOURCE)
	@ConfigurationProperties(prefix = "biblioteca.datasource")
	public DataSource dataSource() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,	@Qualifier(DATA_SOURCE) DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

		return builder.dataSource(dataSource)
				.properties(properties)
				.packages("br.com.boot.spring.biblioteca.camadas.entity")
				.persistenceUnit("bibliotecaPU")
				.build();
	}
	
	@Primary
	@Bean(TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
