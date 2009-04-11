package br.com.web.actions;

import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.RelatoriosBO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class RelatoriosAction extends GenericAction {

	private List<SolicitacaoDTO> listaPDFSolicitacao;

	private RelatoriosBO relatoriosBO;

	public RelatoriosAction() {
		relatoriosBO = FactoryBO.getInstance().getRelatoriosBO();
	}

	public String load() throws Exception {

		return "load.fwd";
	}

	public String resumoFaturamentoMensal() throws Exception {

		return "gerarPDF.fwd";
	}

	public List<SolicitacaoDTO> getListaPDFSolicitacao() {
		return listaPDFSolicitacao;
	}

	public void setListaPDFSolicitacao(List<SolicitacaoDTO> listaPDFSolicitacao) {
		this.listaPDFSolicitacao = listaPDFSolicitacao;
	}
	
	/*
	  public RelatoriosBO getRelatoriosBO() { return relatoriosBO; }
	  
	  public void setRelatoriosBO(RelatoriosBO relatoriosBO) {
	  this.relatoriosBO = relatoriosBO; }
	 */
}
