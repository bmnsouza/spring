package br.com.spring.boot.projeto.base.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Retention(RUNTIME)
@Constraint(validatedBy = ValoresPermitidosValidator.class)
@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, TYPE_USE })
public @interface ValoresPermitidos {

    String message() default "Valores permitidos ";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

    String[] value();
}

class ValoresPermitidosValidator implements ConstraintValidator<ValoresPermitidos, Object> {
	
	private List<String> valoresEsperados;
    
	private String mensagem;
	
	@Override
	public void initialize(ValoresPermitidos valoresPermitidos) {
        valoresEsperados = Arrays.asList(valoresPermitidos.value());
        mensagem = new StringBuilder(valoresPermitidos.message()).append(valoresEsperados).toString();
	}
	
	@Override
	public boolean isValid(Object valor, ConstraintValidatorContext constraintValidatorContext) {
		boolean isValid = false;
		
		if (valor == null || valoresEsperados.contains(valor.toString())) {
			isValid = true;
		} else {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(mensagem).addConstraintViolation();
		}
		
		return isValid;
	}	

}