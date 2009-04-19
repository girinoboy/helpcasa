package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.LoginDAO;
import br.com.persistencia.dto.PessoaDTO;

public class LoginBO extends GenericBO{
	
	private LoginDAO loginDAO;

	public LoginBO() {
		super();
		loginDAO = FactoryDAO.getInstance().getLoginDAO();
	}

	public PessoaDTO login(PessoaDTO pessoaDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		PessoaDTO resultadoLogin ;
		try{
			resultadoLogin = loginDAO.login(conn,pessoaDTO);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			conn.close();
		}
		return resultadoLogin;
	}

}
