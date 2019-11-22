package br.com.agencia.adi.agencia_adi.decorator;

import java.util.Date;

public class SalaDecorator extends HistoricoDecorator {
    public SalaDecorator(Historico propriedade) {
        super(propriedade);
        nome = "Sala";
        data = new Date();
    }
}