package br.com.agencia.adi.agencia_adi.decorator;

import java.util.Date;

public class UsuarioDecorator extends HistoricoDecorator {
    public UsuarioDecorator(Historico propriedade) {
        super(propriedade);
        nome = "Usuário";
        data = new Date();
    }
}