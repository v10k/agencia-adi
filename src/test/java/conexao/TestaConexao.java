package conexao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.agencia.adi.agencia_adi.factory.ConectaBanco;

public class TestaConexao {
    
    public static void main (String [] args) throws SQLException {
     Connection conexao = new ConectaBanco().getConexao();
     System.out.println("Conexão funcionando");
     conexao.close();
    }
}
