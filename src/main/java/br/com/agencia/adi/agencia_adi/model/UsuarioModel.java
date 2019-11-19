package br.com.agencia.adi.agencia_adi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class UsuarioModel implements Serializable {
        
   private static final long serialVersionUID = 11254885L;
    
   private int id_user;
   private String nome_user;
   private String senha;
   private String email;
   private Boolean admin;
   
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getNome_user() {
		return nome_user;
	}
	public void setNome_user(String nome_user) {
		this.nome_user = nome_user;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}  
}