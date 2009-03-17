package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;

import org.core.Conexao;

import br.com.persistencia.dto.UsuarioDTO;
import br.com.persistencia.FactoryDAO;

public abstract class GenericBO {

	private UsuarioDTO sessaoUsuario;	
	
	/**
	 * Método responsável por retornar o FactoryDAO com uma instância do usuário da sessão
	 * 
	 * @return FactoryDAO
	 */
	public FactoryDAO getFactoryDAOInstance() {
		return FactoryDAO.getInstance(getSessaoUsuario());
	}
	
	public FactoryBO getFactoryBOInstance(){
		return FactoryBO.getInstance(getSessaoUsuario());
		
	}
	/**
	 * Método responsável por retornar a conexão com os dados do usuário logado
	 * para log do sistema
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return Conexao.getConnection(getSessaoUsuario().getUsuarioId());
	}
	
	/**
	 * Método responsável por retornar a conexão com os dados do usuário logado
	 * para log do sistema
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(int usuarioId) throws SQLException {
		return Conexao.getConnection(usuarioId);
	}

	/**
	 * @return UsuarioDTO sessaoUsuario
	 */
	public UsuarioDTO getSessaoUsuario() {
		return sessaoUsuario;
	}

	/**
	 * @param Seta o UsuarioDTO sessaoUsuario
	 */
	public void setSessaoUsuario(UsuarioDTO sessaoUsuario) {
		this.sessaoUsuario = sessaoUsuario;
	}
}

