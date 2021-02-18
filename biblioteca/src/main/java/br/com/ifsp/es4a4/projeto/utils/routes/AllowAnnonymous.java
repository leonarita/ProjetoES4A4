package br.com.ifsp.es4a4.projeto.utils.routes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowAnnonymous {
	
	ValidationType validationType() default ValidationType.ANNONYMOUS;

}
