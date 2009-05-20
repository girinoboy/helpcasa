package br.com.web.actions;

import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.ProfissionalBO;
import br.com.persistencia.dto.AdicionaisDTO;
import br.com.persistencia.dto.ProfissionalDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

/**
 * @author Marcleônio do N. Medeiros
 *
 */
public class ProfissionalAction extends GenericAction {
	
	private ProfissionalDTO profissionalDTO;
	private ProfissionalBO profissionalBO;
	private List<SolicitacaoDTO> listAgenda;
	private List<AdicionaisDTO> listAgendaDetalhada;
	
	
	public ProfissionalAction() {
		profissionalBO = FactoryBO.getInstance().getProfissionalBO();
	}

	public String load(){

		return "load.fwd";
	}
	
	public String consultarAgenda(){
		try{
			Long idFuncionario = getSessaoPessoa().getId();
			listAgenda = profissionalBO.consultarAgenda(profissionalDTO.getData(),idFuncionario);
			
			profissionalDTO.setListar(true);
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();			
		}
		return load();
	}
	
	public String consultarAgendaDetalhada(){
		try{
			listAgendaDetalhada = profissionalBO.consultarAgendaDetalhada(idSolicitacao);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "profissionalAgendaDetalhada.fwd";
	}
	
	public String finalizarServico(){
		profissionalBO.finalizarServico(idSolicitacao);
		return load();
	}

	public ProfissionalDTO getProfissionalDTO() {
		if(profissionalDTO==null){
			profissionalDTO = new ProfissionalDTO();	
		}
		return profissionalDTO;
	}

	public void setProfissionalDTO(ProfissionalDTO profissionalDTO) {
		this.profissionalDTO = profissionalDTO;
	}

	public List<SolicitacaoDTO> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<SolicitacaoDTO> listAgenda) {
		this.listAgenda = listAgenda;
	}

	public List<AdicionaisDTO> getListAgendaDetalhada() {
		return listAgendaDetalhada;
	}

	public void setListAgendaDetalhada(List<AdicionaisDTO> listAgendaDetalhada) {
		this.listAgendaDetalhada = listAgendaDetalhada;
	}

}
