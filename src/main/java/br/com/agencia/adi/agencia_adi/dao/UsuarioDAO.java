package br.com.agencia.adi.agencia_adi.dao;

import java.util.ArrayList;

import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;
import io.jsonwebtoken.Claims;

public class UsuarioDAO {
	
	public  IUsuario Iusuario;
	
	public UsuarioModel CadastrarCliente(UsuarioModel usuario) {
		return Iusuario.CadastrarCliente(usuario);
	}
	
	public UsuarioModel EditarCliente(UsuarioModel usuario) {
		return Iusuario.EditarCliente(usuario);
	}	
	
	public UsuarioModel ObterCliente(int id_usuario) {
		return Iusuario.ObterCliente(id_usuario);
	}
	
	public UsuarioModel ObterCliente(String email) {
		return Iusuario.ObterCliente(email);
	}
	
	public Boolean Login(UsuarioModel usuario) {
		return Iusuario.Login(usuario);
	}
	
	public String gerarToken(String login, Integer expiraEmDias ) {
		return Iusuario.gerarToken(login, expiraEmDias);
	}
	
	public Claims validaToken(String token) {
		return Iusuario.validaToken(token);
	}
	
	public NivelPermissao buscarNivelPermissao(String email) {
		return Iusuario.buscarNivelPermissao(email);
	}
	
	public ArrayList<UsuarioModel> ListarCliente() {
		return Iusuario.ListarCliente();
	}
	
	public Boolean DeletarCliente(int id) {
		return Iusuario.DeletarCliente(id);
	}
	
}
