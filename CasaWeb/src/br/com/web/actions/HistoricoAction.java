package br.com.web.actions;

import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.HistoricoBO;
import br.com.persistencia.dto.HistoricoDTO;

public class HistoricoAction extends GenericAction{
	
	private HistoricoDTO historicoDTO;
	private HistoricoBO historicoBO;
	private List<HistoricoDTO> listHistorico;
	private List<HistoricoDTO> listaHistoricoDetalhada;

	public HistoricoAction() {
		historicoBO = FactoryBO.getInstance().getHistoricoBO();
	}
	
	public String load(){
		
		return "load.fwd";
	}
	
	public String historicoListar(){
		try{
			this.listHistorico = historicoBO.historicoListar();
		}catch(Exception e){
			e.printStackTrace();
		}
		return load();
	}
	
	public String aplicaClassificacao(){
		try{
			Long idRespondavelClassificar = getSessaoPessoa().getId();
				Long id = null;
				this.historicoBO.aplicaClassificacao(id,idRespondavelClassificar);
		
		}catch (Exception e) {
			e.printStackTrace();			
		}
		
		return load();
	}
	
	public String consultarHistorico(){
		
		Long idSolicitacao;
		listaHistoricoDetalhada = historicoBO.consultarHistorico(idSolicitacao);
		return null;
	}

	public HistoricoDTO getHistoricoDTO() {
		return historicoDTO;
	}

	public void setHistoricoDTO(HistoricoDTO historicoDTO) {
		this.historicoDTO = historicoDTO;
	}

	public List<HistoricoDTO> getListHistorico() {
		return listHistorico;
	}

	public void setListHistorico(List<HistoricoDTO> listHistorico) {
		this.listHistorico = listHistorico;
	}
	
	

}
