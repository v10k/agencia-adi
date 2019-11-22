package br.com.agencia.adi.agencia_adi.decorator;

import java.util.Date;

public abstract class HistoricoDecorator extends Historico {
	Historico historico;
	 
    public HistoricoDecorator(Historico propriedade) {
        historico = propriedade;
    }
 
    @Override
    public String getNome() {
        return historico.getNome()  + " : " + nome;
    }
 
    public Date getData() {
        return historico.getData();
    }
}


