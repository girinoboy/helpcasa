package br.com.web.actions;

import java.util.List;

import br.com.Mensagem;
import br.com.bo.FactoryBO;
import br.com.bo.ProfissaoBO;
import br.com.persistencia.dto.ProfissaoDTO;

public class ProfissaoAction extends GenericAction {
    
	private ProfissaoBO profissaoBO;
	private List<ProfissaoDTO> listProfissoes;
	private Long[] idsProfissao;
	private ProfissaoDTO profissaoDTO;

	public ProfissaoAction() {
		profissaoBO = FactoryBO.getInstance().getProfissaoBO();
	}

	public String load() {
		try {
			this.listProfissoes = profissaoBO.profissaoListar();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "load.fwd";
	}

	public String inclui() throws Exception {
		try {
			profissaoBO.inclui(profissaoDTO);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return load();
	}

	public String exclui() throws Exception {
		try {
			if (idsProfissao != null && idsProfissao.length > 0) {
				profissaoBO.exclui(idsProfissao);
			} else {
				System.out.println("Nenhum item selecionado.");
			}
		} catch (Exception e) {
			System.out.println("Existe um funcionari anexado a essa função");
			e.printStackTrace();
		}
		return load();
	}
	
	public String altera(){
		
		try{
			profissaoBO.altera(profissaoDTO);
			getMensagemGlobal().addMensagem("Profissão alterada com sucesso.", Mensagem.ALERTA);
		}catch(Exception e){
			getMensagemGlobal().addMensagem("Ocorreu um erro ao alterar Prifissão.", Mensagem.ALERTA);
			e.printStackTrace();			
		}
		
		return load();
		
	}
	
	public String cadastrar(){
		return "cadastrar.fwd";
	}
	
	public String alterar(){
		try {
			Long id = profissaoDTO.getId();
			profissaoDTO = profissaoBO.consultarPor(id);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "alterar.fwd";
	}
	public List<ProfissaoDTO> getListProfissoes() {
		return listProfissoes;
	}

	public void setListProfissoes(List<ProfissaoDTO> listProfissoes) {
		this.listProfissoes = listProfissoes;
	}

	public Long[] getIdsProfissao() {
		return idsProfissao;
	}

	public void setIdsProfissao(Long[] idsProfissao) {
		this.idsProfissao = idsProfissao;
	}

	public ProfissaoDTO getProfissaoDTO() {
		return profissaoDTO;
	}

	public void setProfissaoDTO(ProfissaoDTO profissaoDTO) {
		this.profissaoDTO = profissaoDTO;
	}
	
	

}
