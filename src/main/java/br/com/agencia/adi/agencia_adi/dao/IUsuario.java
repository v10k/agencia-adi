package br.com.agencia.adi.agencia_adi.dao;

import java.util.ArrayList;

import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;
import io.jsonwebtoken.Claims;

public interface IUsuario {
	UsuarioModel CadastrarCliente(UsuarioModel usuario);
	UsuarioModel EditarCliente(UsuarioModel usuario);
	UsuarioModel ObterCliente(int id_usuario);
	UsuarioModel ObterCliente(String email);
	Boolean Login(UsuarioModel usuario);
	String gerarToken(String login, Integer expiraEmDias);
	Claims validaToken(String token);
	NivelPermissao buscarNivelPermissao(String email);
	Boolean DeletarCliente(int id);
	ArrayList<UsuarioModel> ListarCliente();
}
