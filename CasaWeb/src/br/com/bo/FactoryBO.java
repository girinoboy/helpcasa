package br.com.bo;

import br.com.persistencia.dto.UsuarioDTO;

public class FactoryBO {
	
	private static FactoryBO instance;
	private UsuarioDTO sessaoUsuario;

	//----------------------- BO's -----------------------------------
	private ClienteBO clienteBO;
	
	
	private FactoryBO() {
	}
	
	public static FactoryBO getInstance() {
		if (instance == null) {
			instance = new FactoryBO();
		}
		return instance;
	}
	
	public static FactoryBO getInstance(UsuarioDTO sessaoUsuario) {
		if (instance == null) {
			instance = new FactoryBO();
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
	
	
	public ClienteBO getClienteBO() {
		if (clienteBO == null) {
			clienteBO = new ClienteBO();
		}
		return clienteBO;
	}

}
