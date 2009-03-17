package br.com.persistencia;

import br.com.persistencia.dao.cliente.ClienteDAO;
import br.com.persistencia.dto.UsuarioDTO;

public class FactoryDAO {

	private UsuarioDTO sessaoUsuario;
	private static FactoryDAO instance;
	
	//----------------------- DAO's -----------------------------------
	ClienteDAO clienteDAO;
	
	private FactoryDAO() {
	}

	public static FactoryDAO getInstance() {
		if (instance == null) {
			instance = new FactoryDAO();
		}
		return instance;
	}

	public static FactoryDAO getInstance(UsuarioDTO sessaoUsuario) {
		if(instance == null) {
			instance = new FactoryDAO();
		}
		instance.setSessaoUsuario(sessaoUsuario);
		return instance;
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
	
	
	/**
	 * 
	 * @return SinaisVitaisDAO sinaisVitaisDAO
	 */
	public ClienteDAO getClienteDAO() {
		if(clienteDAO == null){
			clienteDAO = new ClienteDAO();
		}
		return clienteDAO;
	}
}
