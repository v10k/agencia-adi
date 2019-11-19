package br.com.agencia.adi.agencia_adi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class SalaModel implements Serializable {
        
   private static final long serialVersionUID = 1124129L;
    
   private int id_sala;
   private String nome_sala;
   private String descricao_sala;
   private Double tamanho_sala;
   private int capacidade_sala;
   private String andar_sala;
   
	public int getId_sala() {
		return id_sala;
	}
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
	public String getNome_sala() {
		return nome_sala;
	}
	public void setNome_sala(String nome_sala) {
		this.nome_sala = nome_sala;
	}
	public String getDescricao_sala() {
		return descricao_sala;
	}
	public void setDescricao_sala(String descricao_sala) {
		this.descricao_sala = descricao_sala;
	}
	public Double getTamanho_sala() {
		return tamanho_sala;
	}
	public void setTamanho_sala(Double tamanho_sala) {
		this.tamanho_sala = tamanho_sala;
	}
	public int getCapacidade_sala() {
		return capacidade_sala;
	}
	public void setCapacidade_sala(int capacidade_sala) {
		this.capacidade_sala = capacidade_sala;
	}
	public String getAndar_sala() {
		return andar_sala;
	}
	public void setAndar_sala(String andar_sala) {
		this.andar_sala = andar_sala;
	}	
}