package br.com.udemy.vendas.domain.validadores;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = NotEmptyListValidador.class)
public @interface NotEmptyList {
    String message() default  "A lista não pode ser vazia!!";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}


//Anotacao customizada para validar se a lista nao esta vazia