package br.com.boot.spring.base.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DataUtil {

	// Formatos para hora
	public static final String HHMM_BASICO = "HHmm";
	public static final String HHMMSS_BASICO = "HHmmss";
	public static final String HHMM_DOIS_PONTOS = "HH:mm";
	public static final String HHMMSS_DOIS_PONTOS = "HH:mm:ss";

	// Formatos para data
	public static final String AAAAMMDD_BASICO = "uuuuMMdd";
	public static final String AAAAMMDD_HIFEN = "uuuu-MM-dd";
	public static final String DDMMAAAA_BARRA = "dd/MM/uuuu";
	public static final String DDMMAAAA_BASICO = "ddMMuuuu";

	// Formatos para data e hora
	public static final String AAAAMMDD_HHMMSS_BASICO = "uuuuMMdd HHmmss";
	public static final String AAAAMMDD_HHMMSS_HIFEN = "uuuu-MM-dd HH:mm:ss";
	public static final String DDMMAAAA_HHMMSS_BARRA = "dd/MM/uuuu HH:mm:ss";
	public static final String DDMMAAAA_HHMMSS_BASICO = "ddMMuuuu HHmmss";
	
	/**
	 * Verifica se data está dentro do intervalo de dataInicio (inclusive) e dataFim (inclusive)
	 * @param data a ser verificada no intervalo
	 * @param dataIncio início do intervalo
	 * @param dataFim fim do intervalo
	 * @return boolean
	 */
	public static boolean isDateWithinRange(LocalDate data, LocalDate dataIncio, LocalDate dataFim) {
		return (!data.isBefore(dataIncio) && !data.isAfter(dataFim));
	}
	
	/**
	 * Realiza o parse de uma String para um LocalTime no formato HH:mm:ss
	 * @param hora
	 * @return LocalTime
	 */
	public static LocalTime parseLocalTime(String hora) {
		return parseLocalTime(hora, HHMMSS_DOIS_PONTOS); 
	}
	
	/**
	 * Realiza o parse de uma String para um LocalTime no formato desejado
	 * @param hora
	 * @param formatoDesejado
	 * @return LocalTime
	 */
	public static LocalTime parseLocalTime(String hora, String formatoDesejado) {
		return LocalTime.parse(hora, DateTimeFormatter.ofPattern(formatoDesejado).withResolverStyle(ResolverStyle.STRICT));
	}

	/**
	 * Realiza o parse de uma String para um LocalDate no formato dd/MM/uuuu
	 * @param data
	 * @return LocalDate
	 */
	public static LocalDate parseLocalDate(String data) {
		return parseLocalDate(data, DDMMAAAA_BARRA); 
	}
	
	/**
	 * Realiza o parse de uma String para um LocalDate no formato desejado
	 * @param data
	 * @param formatoDesejado
	 * @return LocalDate
	 */
	public static LocalDate parseLocalDate(String data, String formatoDesejado) {
		return LocalDate.parse(data, DateTimeFormatter.ofPattern(formatoDesejado).withResolverStyle(ResolverStyle.STRICT));
	}
	
	/**
	 * Realiza o parse de uma String para um LocalDateTime no formato dd/MM/uuuu HH:mm:ss
	 * @param dataHora
	 * @return LocalDateTime
	 */
	public static LocalDateTime parseLocalDateTime(String dataHora) {
		return parseLocalDateTime(dataHora, DDMMAAAA_HHMMSS_BARRA); 
	}
	
	/**
	 * Realiza o parse de uma String para um LocalDateTime no formato desejado
	 * @param dataHora
	 * @param formatoDesejado
	 * @return LocalDateTime
	 */
	public static LocalDateTime parseLocalDateTime(String dataHora, String formatoDesejado) {
		return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern(formatoDesejado).withResolverStyle(ResolverStyle.STRICT));
	}
	
	/**
	 * Formata um LocalTime em uma String no formato HH:mm:ss
	 * @param localTime
	 * @return String
	 */
	public static String formatar(LocalTime localTime) {
		return formatar(localTime, HHMMSS_DOIS_PONTOS);
	}

	/**
	 * Formata um LocalTime em uma String no formato desejado
	 * @param localTime
	 * @param formatoDesejado
	 * @return String
	 */
	public static String formatar(LocalTime localTime, String formatoDesejado) {
		return localTime.format(DateTimeFormatter.ofPattern(formatoDesejado).withResolverStyle(ResolverStyle.STRICT));
	}
	
	/**
	 * Formata um LocalDate em uma String no formato dd/MM/uuuu
	 * @param localDate
	 * @return String
	 */
	public static String formatar(LocalDate localDate) {
		return formatar(localDate, DDMMAAAA_BARRA);
	}

	/**
	 * Formata um LocalDate em uma String no formato desejado
	 * @param localDate
	 * @param formatoDesejado
	 * @return String
	 */
	public static String formatar(LocalDate localDate, String formatoDesejado) {
		return localDate.format(DateTimeFormatter.ofPattern(formatoDesejado).withResolverStyle(ResolverStyle.STRICT));
	}

	/**
	 * Formata um LocalDateTime em uma String no formato dd/MM/uuuu HH:mm:ss
	 * @param localDateTime
	 * @return String
	 */
	public static String formatar(LocalDateTime localDateTime) {
		return formatar(localDateTime, DDMMAAAA_HHMMSS_BARRA);
	}

	/**
	 * Formata um LocalDateTime em uma String no formato desejado
	 * @param localDateTime
	 * @param formatoDesejado
	 * @return String
	 */
	public static String formatar(LocalDateTime localDateTime, String formatoDesejado) {
		return localDateTime.format(DateTimeFormatter.ofPattern(formatoDesejado).withResolverStyle(ResolverStyle.STRICT));
	}

}