package br.com.agencia.adi.agencia_adi.decorator;

import java.util.Date;

public class ReservaDecorator extends HistoricoDecorator {
    public ReservaDecorator(Historico propriedade) {
        super(propriedade);
        nome = "Reserva";
        data = new Date();
    }
}