package br.com.persistencia;

import br.com.persistencia.dao.ClienteDAO;
import br.com.persistencia.dao.LoginDAO;
import br.com.persistencia.dto.PessoaDTO;

public class FactoryDAO {

	private PessoaDTO sessaoPessoa;
	private static FactoryDAO instance;
	
	//----------------------- DAO's -----------------------------------
	ClienteDAO clienteDAO;
	LoginDAO loginDAO;
	
	private FactoryDAO() {
	}

	public static FactoryDAO getInstance() {
		if (instance == null) {
			instance = new FactoryDAO();
		}
		return instance;
	}

	public static FactoryDAO getInstance(PessoaDTO sessaoPessoa) {
		if(instance == null) {
			instance = new FactoryDAO();
		}
		instance.setSessaoPessoa(sessaoPessoa);
		return instance;
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
	
	
	/**
	 * 
	 * @return ClienteDAO clienteDAO
	 */
	public ClienteDAO getClienteDAO() {
		if(clienteDAO == null){
			clienteDAO = new ClienteDAO();
		}
		return clienteDAO;
	}

	public LoginDAO getLoginDAO() {
		if(loginDAO == null){
			loginDAO = new LoginDAO();
		}
		return loginDAO;
	}
}
