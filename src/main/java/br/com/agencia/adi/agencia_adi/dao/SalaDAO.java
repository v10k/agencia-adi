package br.com.agencia.adi.agencia_adi.dao;

import br.com.agencia.adi.agencia_adi.model.SalaModel;
import br.com.agencia.adi.agencia_adi.Observable;
import br.com.agencia.adi.agencia_adi.Observer;
import br.com.agencia.adi.agencia_adi.decorator.Adicionar;
import br.com.agencia.adi.agencia_adi.decorator.Alterar;
import br.com.agencia.adi.agencia_adi.decorator.Deletar;
import br.com.agencia.adi.agencia_adi.decorator.Historico;
import br.com.agencia.adi.agencia_adi.decorator.SalaDecorator;
import br.com.agencia.adi.agencia_adi.factory.ConectaBanco;
import java.sql.*;
import java.util.ArrayList;

public class SalaDAO implements Observable  {
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<SalaModel> lista = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();
	HistoricoDAO historico = new HistoricoDAO();
	
	public SalaDAO() {
		conn = new ConectaBanco().getConexao();
	}
	
	public SalaModel CadastrarSala(SalaModel sala) {
		String sql = "INSERT INTO Sala (nome_sala, descricao_sala, tamanho_sala, capacidade_sala, andar_sala) VALUES (?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sala.getNome_sala());
			stmt.setString(2, sala.getDescricao_sala());
			stmt.setDouble(3, sala.getTamanho_sala());
			stmt.setInt(4, sala.getCapacidade_sala());
			stmt.setString(5, sala.getAndar_sala());
			stmt.execute();
			stmt.close();
			Historico decorator = new SalaDecorator(new Adicionar());
			historico.CadastrarHistorico(decorator, sala.getNome_sala());
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao cadastrar sala: " +erro);
		}
		return sala;
	}
	
	public SalaModel EditarSala(SalaModel sala) {
		String sql = "UPDATE Sala SET nome_sala = ?, descricao_sala = ?, tamanho_sala = ?, capacidade_sala = ?, andar_sala = ? WHERE id_sala = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sala.getNome_sala());
			stmt.setString(2, sala.getDescricao_sala());
			stmt.setDouble(3, sala.getTamanho_sala());
			stmt.setInt(4, sala.getCapacidade_sala());
			stmt.setString(5, sala.getAndar_sala());
			stmt.setInt(6, sala.getId_sala());
			stmt.execute();
			stmt.close();
			Historico decorator = new SalaDecorator(new Alterar());
			historico.CadastrarHistorico(decorator, sala.getNome_sala());
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao editar sala: " +erro);
		}
		return sala;
	}
	
	public Boolean DeletarSala(int id) {
		String sql = "DELETE FROM Sala WHERE id_sala = " +id;
		try {
			st = conn.createStatement();
			int deletado = st.executeUpdate(sql);
			st.close();
			 this.notifyObservers(id);
			if (deletado != 0) {
				Historico decorator = new SalaDecorator(new Deletar());
				historico.CadastrarHistorico(decorator, id);
				return true; 
			}
			return false;
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao deletar sala: "+erro);
		}
	}
	

	public ArrayList<SalaModel> ListarSalas() {
		String sql = "SELECT * FROM Sala";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				SalaModel sala = new SalaModel();
				sala.setId_sala(rs.getInt("id_sala"));
				sala.setNome_sala(rs.getString("nome_sala"));
				sala.setDescricao_sala(rs.getString("descricao_sala"));
				sala.setTamanho_sala(rs.getDouble("tamanho_sala"));
				sala.setCapacidade_sala(rs.getInt("capacidade_sala"));
				sala.setAndar_sala(rs.getString("andar_sala"));
				lista.add(sala);
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro : "+erro);
		}
		return lista;
	}
	
	public ArrayList<SalaModel> ListarSalas(String data) {
		String sql = "SELECT * FROM Sala WHERE nome_sala LIKE '%"+data+"%' OR descricao_sala LIKE '%"+data+"%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				SalaModel sala = new SalaModel();
				sala.setId_sala(rs.getInt("id_sala"));
				sala.setNome_sala(rs.getString("nome_sala"));
				sala.setDescricao_sala(rs.getString("descricao_sala"));
				sala.setTamanho_sala(rs.getDouble("tamanho_sala"));
				sala.setCapacidade_sala(rs.getInt("capacidade_sala"));
				sala.setAndar_sala(rs.getString("andar_sala"));
				lista.add(sala);
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro : "+erro);
		}
		return lista;
	}
	
	public SalaModel ObterSala(int id_sala) {
		String sql = "SELECT * FROM Sala WHERE id_sala = '"+id_sala+"'";
		SalaModel sala = new SalaModel();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sala.setId_sala(rs.getInt("id_sala"));
				sala.setNome_sala(rs.getString("nome_sala"));
				sala.setDescricao_sala(rs.getString("descricao_sala"));
				sala.setTamanho_sala(rs.getDouble("tamanho_sala"));
				sala.setCapacidade_sala(rs.getInt("capacidade_sala"));
				sala.setAndar_sala(rs.getString("andar_sala"));
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao obter sala: " +erro);
		}
		return sala;
	}
		
	  public void registerObserver(Observer observer) {
          observers.add(observer);
     }
	  
	  public void removeObserver(Observer observer) {
          observers.remove(observer);
     }
	  
	  public void notifyObservers(int id) {
        for (Observer ob : observers) {
        	ob.update(id);
        }
	  }
	
}
