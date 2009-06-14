package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.persistencia.dto.PessoaDTO;
import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;

public abstract class GenericBO {

	private PessoaDTO sessaoPessoa;	
	
	/**
	 * M�todo respons�vel por retornar o FactoryDAO com uma inst�ncia da pessoa(usu�rio) da sess�o
	 * 
	 * @return FactoryDAO
	 */
	public FactoryDAO getFactoryDAOInstance() {
		return FactoryDAO.getInstance(getSessaoPessoa());
	}
	
	public FactoryBO getFactoryBOInstance(){
		return FactoryBO.getInstance(getSessaoPessoa());
		
	}
	/**
	 * M�todo respons�vel por retornar a conex�o com os dados da pessoa logada
	 * para log do sistema
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return Conexao.getConnection(getSessaoPessoa().getId());
	}
	
	/**
	 * M�todo respons�vel por retornar a conex�o com os dados da pessoa logado
	 * para log do sistema
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(Long pessoaId) throws SQLException {
		return Conexao.getConnection(pessoaId);
	}

	/**
	 * @return PessoaDTO sessaoPessoa
	 */
	public PessoaDTO getSessaoPessoa() {
		return sessaoPessoa;
	}

	/**
	 * @param Seta o PessoaDTO sessaoPessoa
	 */
	public void setSessaoPessoa(PessoaDTO sessaoPessoa) {
		this.sessaoPessoa = sessaoPessoa;
	}
}

