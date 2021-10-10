package br.com.boot.spring.base.annotation;

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
@Constraint(validatedBy = MesValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface Mes {

	String message() default "Mês inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

class MesValidator implements ConstraintValidator<Mes, Integer> {

	@Override
	public boolean isValid(Integer mes, ConstraintValidatorContext constraintValidatorContext) {
		boolean isValid = true;
		
		if (mes != null) {
			try {
				LocalDate.of(LocalDate.now().getYear(), mes, 1);
			} catch (Exception e) {
				isValid = false;
			}			
		}

		return isValid;
	}

}