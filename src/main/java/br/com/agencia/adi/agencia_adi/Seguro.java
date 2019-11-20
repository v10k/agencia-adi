package br.com.agencia.adi.agencia_adi;


import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

import br.com.agencia.adi.agencia_adi.model.NivelPermissao;



@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Seguro {
	NivelPermissao[] value() default{};
}

