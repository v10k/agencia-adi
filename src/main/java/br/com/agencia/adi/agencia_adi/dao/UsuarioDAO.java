package br.com.agencia.adi.agencia_adi.dao;

import br.com.agencia.adi.agencia_adi.model.UsuarioModel;
import br.com.agencia.adi.agencia_adi.factory.ConectaBanco;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<UsuarioModel> lista = new ArrayList<>();
	
	public UsuarioDAO() {
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
			throw new RuntimeException("Erro ao cadastrar usuário: "+erro);
		}
		return usuario;
	}
	
//	public void alterar(Produto produto) {
//		String sql = "UPDATE produto SET nome_produto = ?, descricao_produto = ?, preco = ? WHERE id_produto = ?";
//		try {
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, produto.getNome_produto());
//			stmt.setString(2, produto.getDescricao_produto());
//			stmt.setDouble(3, produto.getPreco());
//			stmt.setInt(4, produto.getId_produto());
//			stmt.execute();
//			stmt.close();
//		} catch(Exception erro) {
//			throw new RuntimeException("Erro 3: "+erro);
//		}
//	}
//	
//	public void excluir(int id) {
//		String sql = "DELETE FROM produto WHERE id_produto = " +id;
//		try {
//			st = conn.createStatement();
//			st.execute(sql);
//			st.close();
//		} catch(Exception erro) {
//			throw new RuntimeException("Erro 4: "+erro);
//		}
//	}
//	

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
				usuario.setEmail(rs.getString("admin"));
				lista.add(usuario);
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro : "+erro);
		}
		return lista;
	}
	
	public UsuarioModel ObterCliente(int id_usuario) {
		String sql = "SELECT * FROM Usuario WHERE id_user LIKE '%"+id_usuario+"%'";
		UsuarioModel usuario = new UsuarioModel();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				usuario.setId_user(rs.getInt("id_user"));
				usuario.setNome_user(rs.getString("nome_user"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAdmin(rs.getBoolean("admin"));
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao obter cliente: "+erro);
		}
		return usuario;
	}
	
}
