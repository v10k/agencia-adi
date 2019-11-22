package br.com.agencia.adi.agencia_adi.decorator;

import java.util.Date;

public abstract class Historico {
 	String nome;
    Date data;
 
    public String getNome() {
        return nome;
    }
 
    public Date getData() {
        return data;
    }
}
