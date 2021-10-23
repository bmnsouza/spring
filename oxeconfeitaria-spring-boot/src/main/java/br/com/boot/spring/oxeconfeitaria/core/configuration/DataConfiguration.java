package br.com.boot.spring.oxeconfeitaria.core.configuration;

import static br.com.boot.spring.base.util.DataUtil.DDMMAAAA_BARRA;
import static br.com.boot.spring.base.util.DataUtil.DDMMAAAA_HHMMSS_BARRA;
import static br.com.boot.spring.base.util.DataUtil.HHMMSS_DOIS_PONTOS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

@Configuration
public class DataConfiguration {

	@PersistenceContext(unitName = OxeconfeitariaConfiguration.ENTITY_MANAGER_FACTORY)
	private EntityManager fazendarioEntityManager;
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			// Serializers
			builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(HHMMSS_DOIS_PONTOS).withResolverStyle(ResolverStyle.STRICT)));
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DDMMAAAA_BARRA).withResolverStyle(ResolverStyle.STRICT)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DDMMAAAA_HHMMSS_BARRA).withResolverStyle(ResolverStyle.STRICT)));

			// Deserializers
			builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(HHMMSS_DOIS_PONTOS).withResolverStyle(ResolverStyle.STRICT)));
			builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(DDMMAAAA_BARRA).withResolverStyle(ResolverStyle.STRICT)));
			builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DDMMAAAA_HHMMSS_BARRA).withResolverStyle(ResolverStyle.STRICT)));
		};
	}

	@Component
	public class LocalTimeConverter implements Converter<String, LocalTime> {
		@Override
		public LocalTime convert(String hora) {
			return LocalTime.parse(hora, DateTimeFormatter.ofPattern(HHMMSS_DOIS_PONTOS).withResolverStyle(ResolverStyle.STRICT));
		}
	}

	@Component
	public class LocalDateConverter implements Converter<String, LocalDate> {
		@Override
		public LocalDate convert(String data) {
			return LocalDate.parse(data, DateTimeFormatter.ofPattern(DDMMAAAA_BARRA).withResolverStyle(ResolverStyle.STRICT));
		}
	}
	
	@Component
	public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
		@Override
		public LocalDateTime convert(String dataHora) {
			return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern(DDMMAAAA_HHMMSS_BARRA).withResolverStyle(ResolverStyle.STRICT));
		}
	}

}