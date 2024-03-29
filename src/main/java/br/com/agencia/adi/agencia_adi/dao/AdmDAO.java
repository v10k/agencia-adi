package br.com.agencia.adi.agencia_adi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import br.com.agencia.adi.agencia_adi.decorator.Deletar;
import br.com.agencia.adi.agencia_adi.decorator.Historico;
import br.com.agencia.adi.agencia_adi.decorator.UsuarioDecorator;
import br.com.agencia.adi.agencia_adi.factory.ConectaBanco;
import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AdmDAO implements IUsuario {
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<UsuarioModel> lista = new ArrayList<>();
	HistoricoDAO historico = new HistoricoDAO();
	private static final String FRASE_SEGREDO = "41A518402BA6C85B63E8CCC1BC321EE99945A1784CB1D16612CF6F471FEA46836F90601B0E66AF968B821F67747D7844EA030E2F8D8841238724E6AA1A6F4A6B";
	
	public AdmDAO() {
		conn = new ConectaBanco().getConexao();
	}
	
	public UsuarioModel CadastrarCliente(UsuarioModel usuario) {
		String sql = "INSERT INTO Usuario (nome_user, senha, email, admin) VALUES (?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome_user());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			stmt.setBoolean(4, usuario.getAdmin());
			stmt.execute();
			stmt.close();
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao cadastrar usuário: " +erro);
		}
		return usuario;
	}
	
	public UsuarioModel EditarCliente(UsuarioModel usuario) {
		String sql = "UPDATE Usuario SET nome_user = ?, email = ?, admin = ?, senha = ? WHERE id_user = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome_user());
			stmt.setString(2, usuario.getEmail());
			stmt.setBoolean(3, usuario.getAdmin());
			stmt.setString(4, usuario.getSenha());
			stmt.setInt(5, usuario.getId_user());
			stmt.execute();
			stmt.close();
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao editar usuário: " +erro);
		}
		return usuario;
	}
		
	public UsuarioModel ObterCliente(int id_usuario) {
		String sql = "SELECT * FROM Usuario WHERE id_user = '"+id_usuario+"'";
		UsuarioModel usuario = new UsuarioModel();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				usuario.setId_user(rs.getInt("id_user"));
				usuario.setNome_user(rs.getString("nome_user"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao obter cliente: "+erro);
		}
		return usuario;
	}
	
	public UsuarioModel ObterCliente(String email) {
		String sql = "SELECT * FROM Usuario WHERE email = '"+email+"'";
		UsuarioModel usuario = new UsuarioModel();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				usuario.setId_user(rs.getInt("id_user"));
				usuario.setNome_user(rs.getString("nome_user"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao obter cliente: "+erro);
		}
		return usuario;
	}
	
	public Boolean Login(UsuarioModel usuario) {
		String sql = "SELECT * FROM Usuario WHERE email = '"+usuario.getEmail()+"' AND senha = '" +usuario.getSenha()+ "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				usuario.setId_user(rs.getInt("id_user"));
				usuario.setNome_user(rs.getString("nome_user"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAdmin(rs.getBoolean("admin"));
				return true;
			}
			usuario.setId_user(-1);
			return false;
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao obter cliente: "+erro);
		}
	}
	
	public String gerarToken(String login, Integer expiraEmDias ) {
		SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS512;
		Date agora = new Date();
		Calendar expira = Calendar.getInstance();
		expira.add(Calendar.DAY_OF_MONTH, expiraEmDias);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(FRASE_SEGREDO);
		SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

		JwtBuilder construtor = Jwts.builder().setIssuedAt(agora).setIssuer(login).signWith(algoritimoAssinatura, key).setExpiration(expira.getTime());
		return construtor.compact();
	}
	
	public Claims validaToken(String token) {
		try{
		   Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(FRASE_SEGREDO)).parseClaimsJws(token).getBody();
		   return claims;
		}catch(Exception ex){
				throw ex;
		}
	}
	
	public NivelPermissao buscarNivelPermissao(String email) {
		UsuarioModel usuario = this.ObterCliente(email);
		if (usuario.getAdmin()) {
			return NivelPermissao.ADM;			
		}
		return NivelPermissao.USUARIO;
	}
	
	public Boolean DeletarCliente(int id) {
		String sql = "DELETE FROM Usuario WHERE id_user = " +id;
		try {
			st = conn.createStatement();
			int deletado = st.executeUpdate(sql);
			st.close();
			if (deletado != 0) {
				Historico decorator = new UsuarioDecorator(new Deletar());
				historico.CadastrarHistorico(decorator, id);
				return true; 
			}
			return false;
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao deletar usuário: "+erro);
		}
		
	}
	

	public ArrayList<UsuarioModel> ListarCliente() {
		String sql = "SELECT * FROM Usuario";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				UsuarioModel usuario = new UsuarioModel();
				usuario.setId_user(rs.getInt("id_user"));
				usuario.setNome_user(rs.getString("nome_user"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAdmin(rs.getBoolean("admin"));
				lista.add(usuario);
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro : "+erro);
		}
		return lista;
	}
}
