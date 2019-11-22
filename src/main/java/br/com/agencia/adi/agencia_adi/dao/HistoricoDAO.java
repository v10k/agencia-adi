package br.com.agencia.adi.agencia_adi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.agencia.adi.agencia_adi.decorator.Historico;
import br.com.agencia.adi.agencia_adi.factory.ConectaBanco;

public class HistoricoDAO {
	
	private Connection conn;
	private PreparedStatement stmt;	
	
	public HistoricoDAO() {
		conn = new ConectaBanco().getConexao();
	}
	
	public void CadastrarHistorico(Historico historico, String mudanca) {
		String sql = "INSERT INTO Historico (acao, data, mudanca) VALUES (?, ?, ?)";
		java.sql.Date data = new java.sql.Date(historico.getData().getTime());
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, historico.getNome());
			stmt.setDate(2, data);
			stmt.setString(3, mudanca);
			stmt.execute();
			stmt.close();
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao cadastrar usuário: " +erro);
		}
	}
	
	public void CadastrarHistorico(Historico historico, int mudanca) {
		String sql = "INSERT INTO Historico (acao, data, mudanca) VALUES (?, ?, ?)";
		java.sql.Date data = new java.sql.Date(historico.getData().getTime());
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, historico.getNome());
			stmt.setDate(2, data);
			stmt.setInt(3, mudanca);
			stmt.execute();
			stmt.close();
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao cadastrar usuário: " +erro);
		}
	}
}
