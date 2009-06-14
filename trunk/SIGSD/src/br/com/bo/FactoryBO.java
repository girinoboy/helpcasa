package br.com.bo;

import br.com.persistencia.dto.PessoaDTO;

public class FactoryBO {
	
	private static FactoryBO instance;
	private PessoaDTO pessoaSessao;

	//----------------------- BO's -----------------------------------
	private ClienteBO clienteBO;
	private RelatoriosBO relatoriosBO;
	private LoginBO loginBO;
	private ProfissaoBO profissaoBO;
	private ServicoBO servicoBO;
	private FuncionarioBO funcionarioBO;
	private SolicitacaoBO solicitacaoBO;
	private HistoricoBO historicoBO;
	private ProfissionalBO profissionalBO;
	private AdicionaisBO adicionaisBO;
	private NotasBO notasBO;
	
	private FactoryBO() {
	}
	
	public static FactoryBO getInstance() {
		if (instance == null) {
			instance = new FactoryBO();
		}
		return instance;
	}
	
	public static FactoryBO getInstance(PessoaDTO pessoaSessao) {
		if (instance == null) {
			instance = new FactoryBO();
		}
		instance.setSessaoPessoa(pessoaSessao);
		return instance;
	}
	
	/**
	 * @return PessoaDTO sessaoPessoa
	 */
	public PessoaDTO getSessaoPessoa() {
		return pessoaSessao;
	}

	/**
	 * @param Seta o PessoaDTO sessaoPessoa
	 */
	public void setSessaoPessoa(PessoaDTO pessoaSessao) {
		this.pessoaSessao = pessoaSessao;
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

	public ProfissionalBO getProfissionalBO() {
		if (profissionalBO == null) {
			profissionalBO = new ProfissionalBO();
		}
		return profissionalBO;
	}

	public AdicionaisBO getAdicionaisBO() {
		if (adicionaisBO == null) {
			adicionaisBO = new AdicionaisBO();
		}
		return adicionaisBO;
	}

	public NotasBO getNotasBO() {
		if (notasBO == null) {
			notasBO = new NotasBO();
		}
		return notasBO;
	}

}
