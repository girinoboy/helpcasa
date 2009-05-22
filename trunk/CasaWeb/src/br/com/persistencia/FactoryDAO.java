package br.com.persistencia;

import br.com.persistencia.dao.AdicionaisDAO;
import br.com.persistencia.dao.ClienteDAO;
import br.com.persistencia.dao.FuncionarioDAO;
import br.com.persistencia.dao.HistoricoDAO;
import br.com.persistencia.dao.LoginDAO;
import br.com.persistencia.dao.NotasDAO;
import br.com.persistencia.dao.ProfissaoDAO;
import br.com.persistencia.dao.ProfissionalDAO;
import br.com.persistencia.dao.ServicoDAO;
import br.com.persistencia.dao.SolicitacaoDAO;
import br.com.persistencia.dto.PessoaDTO;

public class FactoryDAO {

	private PessoaDTO sessaoPessoa;
	private static FactoryDAO instance;
	
	//----------------------- DAO's -----------------------------------
	private ClienteDAO clienteDAO;
	private LoginDAO loginDAO;
	private ProfissaoDAO profissaoDAO;
	private ServicoDAO servicoDAO;
	private FuncionarioDAO funcionarioDAO;
	private SolicitacaoDAO solicitacaoDAO;
	private HistoricoDAO historicoDAO;
	private ProfissionalDAO profissionalDAO;
	private AdicionaisDAO adicionaisDAO;
	private NotasDAO notasDAO;
	
	private FactoryDAO() {}

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

	public ProfissaoDAO getProfissaoDAO() {
		if(profissaoDAO == null){
			profissaoDAO = new ProfissaoDAO();
		}
		return profissaoDAO;
	}

	public ServicoDAO getServicoDAO() {
		if(servicoDAO == null){
			servicoDAO = new ServicoDAO();
		}
		return servicoDAO;
	}

	public FuncionarioDAO getFuncionarioDAO() {
		if(funcionarioDAO == null){
			funcionarioDAO = new FuncionarioDAO();
		}
		return funcionarioDAO;
	}

	public SolicitacaoDAO getSolicitacaoDAO() {
		if(solicitacaoDAO == null){
			solicitacaoDAO = new SolicitacaoDAO();
		}
		return solicitacaoDAO;
	}

	public HistoricoDAO getHistoricoDAO() {
		if(historicoDAO == null){
			historicoDAO = new HistoricoDAO();
		}
		return historicoDAO;
	}

	public ProfissionalDAO getProfissionalDAO() {
		if(profissionalDAO == null){
			profissionalDAO = new ProfissionalDAO();
		}
		return profissionalDAO;
	}

	public AdicionaisDAO getAdicionaisDAO() {
		if(adicionaisDAO == null){
			adicionaisDAO = new AdicionaisDAO();
		}
		return adicionaisDAO;
	}

	public NotasDAO getNotasDAO() {
		if(notasDAO == null){
			notasDAO = new NotasDAO();
		}
		return notasDAO;
	}
}
