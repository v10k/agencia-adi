package br.com.agencia.adi.agencia_adi.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class ReservaModel implements Serializable {
        
   private static final long serialVersionUID = 1125L;
    
   private int id_reserva;
   private int id_user;
   private int id_sala;
   private Date data_reserva;
   
	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_sala() {
		return id_sala;
	}
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
	public Date getData_reserva() {
		return data_reserva;
	}
	public void setData_reserva(Date data_reserva) {
		this.data_reserva = data_reserva;
	}
}