package br.com.web.actions;

import java.util.List;

import br.com.Mensagem;
import br.com.bo.AdicionaisBO;
import br.com.bo.FactoryBO;
import br.com.bo.ProfissionalBO;
import br.com.persistencia.dto.AdicionalDTO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.PessoaDTO;
import br.com.persistencia.dto.ProfissionalDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

/**
 * @author Marcleônio do N. Medeiros
 *
 */
public class ProfissionalAction extends GenericAction {
	
	private ProfissionalDTO profissionalDTO;
	private ProfissionalBO profissionalBO;
	private List<HistoricoDTO> listAgenda;
	private List<AdicionalDTO> listAdicionais;
	private Long[] idsAdicional;
	private AdicionaisBO adicionaisBO;
	
	public ProfissionalAction() {
		profissionalBO = FactoryBO.getInstance().getProfissionalBO();
		adicionaisBO = FactoryBO.getInstance().getAdicionaisBO();
	}

	public String load(){

		return "load.fwd";
	}
	
	public String consultarAgenda(){
		try{
			profissionalDTO.setListar(true);	
			getSession().getAttribute("pessoaSessao");
			getSession().getAttribute("pessoa");
			getSession().getAttribute("pessoaDTO");
			getSession().getAttribute("usuarioLogadoSistema");
			Long idFuncionario = profissionalDTO.getId();
			listAgenda = profissionalBO.consultarAgenda(profissionalDTO.getData(),idFuncionario);
			
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();			
		}
		return load();
	}
	
	public String consultarAgendaDetalhada(){
		try{
			Long idSolicitacao=profissionalDTO.getHistorico().getSolicitacao().getId();
			listAdicionais = profissionalBO.consultarAgendaDetalhada(idSolicitacao);
			HistoricoDTO historico=profissionalBO.consultarHistoricoAgendaDetalhada(idSolicitacao);
			profissionalDTO.setHistorico(historico); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return "profissionalAdicionais.fwd";
	}
	
	public String finalizarServico(){
		Long idSolicitacao=profissionalDTO.getHistorico().getSolicitacao().getId();
		Long alteradoPor = getSessaoPessoa().getId();
		try {
			profissionalBO.finalizarServico(idSolicitacao,alteradoPor);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return load();
	}
	
	public String exclui(){
		try {
			if (idsAdicional != null && idsAdicional.length > 0) {
				adicionaisBO.exclui(idsAdicional);
			} else {
				getMensagemGlobal().addMensagem("Nenhum item selecionado.", Mensagem.ALERTA);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return consultarAgendaDetalhada();
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

	public List<HistoricoDTO> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<HistoricoDTO> listAgenda) {
		this.listAgenda = listAgenda;
	}

	public List<AdicionalDTO> getListAdicionais() {
		return listAdicionais;
	}

	public void setListAdicionais(List<AdicionalDTO> listAdicionais) {
		this.listAdicionais = listAdicionais;
	}

	public Long[] getIdsAdicional() {
		return idsAdicional;
	}

	public void setIdsAdicional(Long[] idsAdicional) {
		this.idsAdicional = idsAdicional;
	}

}
