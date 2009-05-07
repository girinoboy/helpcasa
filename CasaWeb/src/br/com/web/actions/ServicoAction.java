package br.com.web.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.Mensagem;
import br.com.bo.FactoryBO;
import br.com.bo.ProfissaoBO;
import br.com.bo.ServicoBO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;

public class ServicoAction extends GenericAction{
	private ServicoBO servicoBO;
	private List<ServicoDTO> listServicos;
	private Long[] idsServico;
	private ServicoDTO servicoDTO;
	private ProfissaoBO profissaoBO;
	private List<ProfissaoDTO> listProfissoes;
	private Map<Number, String> profissoes;

	public ServicoAction() {		
		servicoBO = FactoryBO.getInstance().getServicoBO();
		profissaoBO = FactoryBO.getInstance().getProfissaoBO();
	}
	
	public String load() {
		return listar();
	}

	public String listar() {
		try {
			listServicos = servicoBO.servicosListar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listar.fwd";
	}
	
	public String inclui() throws Exception {
		try {
			servicoBO.inclui(servicoDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return cadastrar();
		}
		return load();
	}
	
	public String exclui() throws Exception {
		try {
			if (idsServico != null && idsServico.length > 0) {
				servicoBO.exclui(idsServico);
			} else {
				getMensagemGlobal().addMensagem("Nenhum item selecionado.", Mensagem.ALERTA);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return load();
	}
	
	public String cadastrar(){
		profissoes = new HashMap<Number, String>();
		profissoes.put(0, "Selecione...");
		try {
			this.listProfissoes = profissaoBO.profissaoListar();
			for (ProfissaoDTO profissao : listProfissoes) {
				profissoes.put(profissao.getId(), profissao.getNome());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "cadastrar.fwd";
	}
	
	public String altera(){
		
		try{
			servicoBO.altera(servicoDTO);
			getMensagemGlobal().addMensagem("Serviço alterado com sucesso.", Mensagem.ALERTA);
		}catch(Exception e){
			getMensagemGlobal().addMensagem("Ocorreu um erro ao alterar Serviço.", Mensagem.ALERTA);
			e.printStackTrace();			
		}
	
		return load();
		
	}
	
	public String alterar(){
		profissoes = new HashMap<Number, String>();
		profissoes.put(0, "Selecione...");
		try {
			this.listProfissoes = profissaoBO.profissaoListar();
			for (ProfissaoDTO profissao : listProfissoes) {
				profissoes.put(profissao.getId(), profissao.getNome());
			}
			Long id = servicoDTO.getId();
			servicoDTO = servicoBO.consultarPor(id);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "alterar.fwd";
	}

	public List<ServicoDTO> getListServicos() {
		return listServicos;
	}

	public void setListServicos(List<ServicoDTO> listServicos) {
		this.listServicos = listServicos;
	}

	public Long[] getIdsServico() {
		return idsServico;
	}

	public void setIdsServico(Long[] idsServico) {
		this.idsServico = idsServico;
	}

	public ServicoDTO getServicoDTO() {
		return servicoDTO;
	}

	public void setServicoDTO(ServicoDTO servicoDTO) {
		this.servicoDTO = servicoDTO;
	}

	public List<ProfissaoDTO> getListProfissoes() {
		return listProfissoes;
	}

	public void setListProfissoes(List<ProfissaoDTO> listProfissoes) {
		this.listProfissoes = listProfissoes;
	}

	public Map<Number, String> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(Map<Number, String> profissoes) {
		this.profissoes = profissoes;
	}

}
