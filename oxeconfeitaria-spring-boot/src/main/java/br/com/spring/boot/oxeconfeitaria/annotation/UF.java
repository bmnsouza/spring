package br.com.spring.boot.oxeconfeitaria.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.ArrayUtils;

@Retention(RUNTIME)
@Constraint(validatedBy = UFValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface UF {

	String message() default "UF inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

class UFValidator implements ConstraintValidator<UF, String> {

	@Override
	public boolean isValid(String uf, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] {
				null, "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT",
				"PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE","SP", "TO"
		};
		
		return ArrayUtils.contains(array, uf);
	}

}