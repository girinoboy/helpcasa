package br.com.web.actions;

import java.util.List;

import br.com.Mensagem;
import br.com.bo.AdicionaisBO;
import br.com.bo.FactoryBO;
import br.com.bo.ProfissionalBO;
import br.com.persistencia.dto.AdicionaisDTO;
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
	private List<SolicitacaoDTO> listAgenda;
	private List<AdicionaisDTO> listAdicionais;
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
			Long idFuncionario = getSessaoPessoa().getId();
			listAgenda = profissionalBO.consultarAgenda(profissionalDTO.getData(),idFuncionario);
			
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();			
		}
		return load();
	}
	
	public String consultarAgendaDetalhada(){
		try{
			Long idSolicitacao=profissionalDTO.getSolicitacao().getId();
			listAdicionais = profissionalBO.consultarAgendaDetalhada(idSolicitacao);
			SolicitacaoDTO solicitacao=profissionalBO.consultarSolicitacaoAgendaDetalhada(idSolicitacao);;
			profissionalDTO.setSolicitacao(solicitacao); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return "profissionalAdicionais.fwd";
	}
	
	public String finalizarServico(){
		Long idSolicitacao=profissionalDTO.getSolicitacao().getId();
		try {
			profissionalBO.finalizarServico(idSolicitacao);
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

	public List<SolicitacaoDTO> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<SolicitacaoDTO> listAgenda) {
		this.listAgenda = listAgenda;
	}

	public List<AdicionaisDTO> getListAdicionais() {
		return listAdicionais;
	}

	public void setListAdicionais(List<AdicionaisDTO> listAdicionais) {
		this.listAdicionais = listAdicionais;
	}

}
