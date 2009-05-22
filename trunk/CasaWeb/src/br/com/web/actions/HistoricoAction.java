package br.com.web.actions;

import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.HistoricoBO;
import br.com.bo.NotasBO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.NotaDTO;

public class HistoricoAction extends GenericAction{
	
	private HistoricoDTO historicoDTO;
	private HistoricoBO historicoBO;
	private List<HistoricoDTO> listHistorico;
	private List<HistoricoDTO> listaHistoricoDetalhada;
	private NotasBO notasBO;
	private List<NotaDTO> listNota;

	public HistoricoAction() {
		historicoBO = FactoryBO.getInstance().getHistoricoBO();
		notasBO = FactoryBO.getInstance().getNotasBO();
	}
	
	public String load(){
		try {
			listNota = notasBO.consultarNotas();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
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
	
	public List<NotaDTO> getListNota() {
		return listNota;
	}

	public void setListNota(List<NotaDTO> listNota) {
		this.listNota = listNota;
	}

}
