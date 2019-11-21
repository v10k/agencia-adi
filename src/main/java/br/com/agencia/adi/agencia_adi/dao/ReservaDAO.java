package br.com.agencia.adi.agencia_adi.dao;

import br.com.agencia.adi.agencia_adi.model.ReservaModel;
import br.com.agencia.adi.agencia_adi.factory.ConectaBanco;
import java.sql.*;
import java.util.ArrayList;

public class ReservaDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<ReservaModel> lista = new ArrayList<>();
	
	public ReservaDAO() {
		conn = new ConectaBanco().getConexao();
	}
	
	public ReservaModel AgendarSala(ReservaModel reserva) {
		String sql = "INSERT INTO Reserva (id_user, id_sala, data_reserva) VALUES (?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reserva.getId_user());
			stmt.setInt(2, reserva.getId_sala());
			stmt.setDate(3, reserva.getData_reserva());
			stmt.execute();
			stmt.close();
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao cadastrar reserva: " +erro);
		}
		return reserva;
	}
	
	public ArrayList<ReservaModel> ConsultarAgendamento(String data) {
		String sql = "SELECT * FROM Reserva WHERE id_sala = '"+data+"' OR data_reserva = '"+data+"'";
	try {
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			ReservaModel reserva = new ReservaModel();
			reserva.setId_user(rs.getInt("id_user"));
			reserva.setId_sala(rs.getInt("id_sala"));
			reserva.setData_reserva(rs.getDate("data_reserva"));
			lista.add(reserva);
		}
	} catch(Exception erro) {
		throw new RuntimeException("Erro : "+erro);
	}
	return lista;
}
	
//	public SalaModel EditarSala(SalaModel sala) {
//		String sql = "UPDATE Sala SET nome_sala = ?, descricao_sala = ?, tamanho_sala = ?, capacidade_sala = ?, andar_sala = ? WHERE id_sala = ?";
//		try {
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, sala.getNome_sala());
//			stmt.setString(2, sala.getDescricao_sala());
//			stmt.setDouble(3, sala.getTamanho_sala());
//			stmt.setInt(4, sala.getCapacidade_sala());
//			stmt.setString(5, sala.getAndar_sala());
//			stmt.setInt(6, sala.getId_sala());
//			stmt.execute();
//			stmt.close();
//		} catch(Exception erro) {
//			throw new RuntimeException("Erro ao editar sala: " +erro);
//		}
//		return sala;
//	}
//	
	public Boolean CancelarAgendamento(int id, Date data_reserva) {
		String sql = "DELETE FROM Reserva WHERE id_sala = '" +id+ "' AND data_reserva = '"+data_reserva+"'";
		try {
			st = conn.createStatement();
			int deletado = st.executeUpdate(sql);
			st.close();
			return (deletado != 0) ? true : false;
		} catch(Exception erro) {
			throw new RuntimeException("Erro ao deletar reserva: "+erro);
		}
	}
	

	public ArrayList<ReservaModel> ListarReservas(int...id) {
		String sql = (id.length > 0) ? "SELECT * FROM Reserva WHERE id_user = '"+id[0]+"' ORDER BY data_reserva DESC, id_sala ASC" : "SELECT * FROM Reserva ORDER BY data_reserva DESC, id_sala ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ReservaModel reserva = new ReservaModel();
				reserva.setId_reserva(rs.getInt("id_reserva"));
				reserva.setId_user(rs.getInt("id_user"));
				reserva.setId_sala(rs.getInt("id_sala"));
				reserva.setData_reserva(rs.getDate("data_reserva"));
				lista.add(reserva);
			}
		} catch(Exception erro) {
			throw new RuntimeException("Erro : "+erro);
		}
		return lista;
	}

	
	
//	public SalaModel ObterSala(int id_sala) {
//		String sql = "SELECT * FROM Sala WHERE id_sala LIKE '%"+id_sala+"%'";
//		SalaModel sala = new SalaModel();
//		try {
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//			while (rs.next()) {
//				sala.setId_sala(rs.getInt("id_sala"));
//				sala.setNome_sala(rs.getString("nome_sala"));
//				sala.setDescricao_sala(rs.getString("descricao_sala"));
//				sala.setTamanho_sala(rs.getDouble("tamanho_sala"));
//				sala.setCapacidade_sala(rs.getInt("capacidade_sala"));
//				sala.setAndar_sala(rs.getString("andar_sala"));
//			}
//		} catch(Exception erro) {
//			throw new RuntimeException("Erro ao obter sala: " +erro);
//		}
//		return sala;
//	}
	
}
