package br.com.agencia.adi.agencia_adi.dao;

import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;

public interface IUsuario {
	UsuarioModel CadastrarCliente(UsuarioModel usuario);
	UsuarioModel EditarCliente(UsuarioModel usuario);
	UsuarioModel ObterCliente(int id_usuario);
	UsuarioModel ObterCliente(String email);
	Boolean Login(UsuarioModel usuario);
	String gerarToken(String login, Integer expiraEmDias);
	NivelPermissao buscarNivelPermissao(String email);
}
