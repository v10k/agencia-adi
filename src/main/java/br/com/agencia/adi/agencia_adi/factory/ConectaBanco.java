package br.com.agencia.adi.agencia_adi.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {
	
    public Connection getConexao() {
    	try {
	    	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());	
	        return DriverManager.getConnection("jdbc:mysql://localhost/agencia-adi","root", "9264a630");
	    } catch(SQLException erro) {
	    	throw new RuntimeException("Erro ao conectar com o banco de dados: " +erro);
	    }
    }
}