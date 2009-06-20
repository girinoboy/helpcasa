package br.com.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexao {

public static final Connection getConnection(Long idUsuario) throws SQLException {
		
		Connection conexao = null;
		
		try{
			conexao = Conexao.getConnection();
			String log = "INSERT INTO INFORMACAO_SESSAO VALUES (now(),?)" ;
			
		    conexao.prepareStatement(log).execute();
		    ////System.out.println("Passei pelo select do LOG");
		}
		catch (SQLException e) {
			System.err.println("Ocorreu um problema com a conex�o que ativa o log no Banco de Dados. Usuario: " + idUsuario);
			if(conexao != null)
				conexao.close();
			
			throw e;
		}

	    return conexao;
	}

public static final Connection getConnection() throws SQLException {

	Connection connection = null;

	try {
		// carregar o driver
		Class.forName("com.mysql.jdbc.Driver");
		// Conectar no banco dbclientes
		connection = DriverManager.getConnection(
				"jdbc:mysql://mysql01.sigsd.com.br:3306/sigsd?autoReconnect=true", "sigsd",
				"sigsd10");
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	

	return connection;
}



}
