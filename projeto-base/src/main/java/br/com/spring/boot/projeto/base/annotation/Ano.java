package br.com.spring.boot.projeto.base.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Retention(RUNTIME)
@Constraint(validatedBy = AnoValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface Ano {

	String message() default "Ano inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

class AnoValidator implements ConstraintValidator<Ano, Integer> {

	@Override
	public boolean isValid(Integer ano, ConstraintValidatorContext constraintValidatorContext) {
		boolean isValid = true;
		
		if (ano != null) {
			// Verifica se o ano é menor ou igual a 1900 ou maior que 9999
			if (ano <= 1900 || ano > 9999) {
				isValid = false;
			} else {
				try {
					LocalDate.of(ano, LocalDate.now().getMonthValue(), 1);
				} catch (Exception e) {
					isValid = false;
				}
			}
		}
		
		return isValid;
	}

}