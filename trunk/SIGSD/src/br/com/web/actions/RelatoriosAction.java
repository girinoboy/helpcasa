package br.com.web.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.RelatoriosBO;
import br.com.persistencia.dto.RelatorioDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class RelatoriosAction extends GenericAction {

	private List<SolicitacaoDTO> listaPDFSolicitacao;
	private Collection<RelatorioDTO> listaPDFTRelatorio;

	private RelatoriosBO relatoriosBO;

	public RelatoriosAction() {
		relatoriosBO = FactoryBO.getInstance().getRelatoriosBO();
	}

	public String load()  {

		return "load.fwd";
	}

	public String resumoFaturamentoMensal() {
		try{
	//	listaPDFTRelatorio = new ArrayList<RelatorioDTO>();
		
		listaPDFTRelatorio = relatoriosBO.resumoFaturamentoMensal();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "gerarPDF.fwd";
	}
	
	public String resumoFaturamentoMensalPorProfissional(){
		try{
			//	listaPDFTRelatorio = new ArrayList<RelatorioDTO>();

			listaPDFTRelatorio = relatoriosBO.resumoFaturamentoMensalPorProfissional();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "gerarPDF.fwd";
	}

	public List<SolicitacaoDTO> getListaPDFSolicitacao() {
		return listaPDFSolicitacao;
	}

	public void setListaPDFSolicitacao(List<SolicitacaoDTO> listaPDFSolicitacao) {
		this.listaPDFSolicitacao = listaPDFSolicitacao;
	}

	public Collection<RelatorioDTO> getListaPDFTRelatorio() {
		return listaPDFTRelatorio;
	}

	public void setListaPDFTRelatorio(Collection<RelatorioDTO> listaPDFTRelatorio) {
		this.listaPDFTRelatorio = listaPDFTRelatorio;
	}
	
	/*
	  public RelatoriosBO getRelatoriosBO() { return relatoriosBO; }
	  
	  public void setRelatoriosBO(RelatoriosBO relatoriosBO) {
	  this.relatoriosBO = relatoriosBO; }
	 */
}
