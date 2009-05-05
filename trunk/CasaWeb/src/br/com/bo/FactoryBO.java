package br.com.bo;

import br.com.persistencia.dto.PessoaDTO;

public class FactoryBO {
	
	private static FactoryBO instance;
	private PessoaDTO sessaoUsuario;

	//----------------------- BO's -----------------------------------
	private ClienteBO clienteBO;
	private RelatoriosBO relatoriosBO;
	private LoginBO loginBO;
	private ProfissaoBO profissaoBO;
	private ServicoBO servicoBO;
	private FuncionarioBO funcionarioBO;
	private SolicitacaoBO solicitacaoBO;
	private HistoricoBO historicoBO;
	
	private FactoryBO() {
	}
	
	public static FactoryBO getInstance() {
		if (instance == null) {
			instance = new FactoryBO();
		}
		return instance;
	}
	
	public static FactoryBO getInstance(PessoaDTO sessaoUsuario) {
		if (instance == null) {
			instance = new FactoryBO();
		}
		instance.setSessaoUsuario(sessaoUsuario);
		return instance;
	}
	
	/**
	 * @return UsuarioDTO sessaoUsuario
	 */
	public PessoaDTO getSessaoUsuario() {
		return sessaoUsuario;
	}

	/**
	 * @param Seta o UsuarioDTO sessaoUsuario
	 */
	public void setSessaoUsuario(PessoaDTO sessaoUsuario) {
		this.sessaoUsuario = sessaoUsuario;
	}
	
	
	public ClienteBO getClienteBO() {
		if (clienteBO == null) {
			clienteBO = new ClienteBO();
		}
		return clienteBO;
	}

	public RelatoriosBO getRelatoriosBO() {
		if (relatoriosBO == null) {
			relatoriosBO = new RelatoriosBO();
		}
		return relatoriosBO;
	}

	public LoginBO getLoginBO() {
		if (loginBO == null) {
			loginBO = new LoginBO();
		}
		return loginBO;
	}

	public ProfissaoBO getProfissaoBO() {
		if (profissaoBO == null) {
			profissaoBO = new ProfissaoBO();
		}
		return profissaoBO;
	}

	public ServicoBO getServicoBO() {
		if (servicoBO == null) {
			servicoBO = new ServicoBO();
		}
		return servicoBO;
	}

	public FuncionarioBO getFuncionarioBO() {
		if (funcionarioBO == null) {
			funcionarioBO = new FuncionarioBO();
		}
		return funcionarioBO;
	}

	public SolicitacaoBO getSolicitacaoBO() {
		if (solicitacaoBO == null) {
			solicitacaoBO = new SolicitacaoBO();
		}
		return solicitacaoBO;
	}

	public HistoricoBO getHistoricoBO() {
		if (historicoBO == null) {
			historicoBO = new HistoricoBO();
		}
		return historicoBO;
	}

}
