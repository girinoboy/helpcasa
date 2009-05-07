package br.com.web.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.bo.ServicoBO;
import br.com.bo.SolicitacaoBO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;
import br.com.persistencia.dto.UfDTO;

public class SolicitacaoAction extends GenericAction{
	
	private SolicitacaoDTO solicitacaoDTO;
	private SolicitacaoBO solicitacaoBO;
	private ServicoBO servicoBO;
	private ClienteBO clienteBO;
	private List<SolicitacaoDTO> listSolicitacoes;
	private List<ServicoDTO> listServicos;
	private Map<Number, String> servicos;
	

	public SolicitacaoAction() {
		solicitacaoBO = FactoryBO.getInstance().getSolicitacaoBO();
		servicoBO = FactoryBO.getInstance().getServicoBO();
		clienteBO = FactoryBO.getInstance().getClienteBO();
	}
	
	public String load() {

		servicos = new HashMap<Number, String>();
		servicos.put(0, "Selecione...");
		try {
			listServicos = servicoBO.servicosListar();
			solicitacaoDTO.setCliente(clienteBO.consulta(solicitacaoDTO.getCliente().getCpf()));
			for (ServicoDTO servico : listServicos) {
				servicos.put(servico.getId(), servico.getNome());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "load.fwd";
	}
	
	public String solicitacaoListar(){
		try{
			this.listSolicitacoes = solicitacaoBO.solicitacaoListar(solicitacaoDTO.getCliente().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "solicitacoesListar.fwd";
	}
	
	public String solicitacaoInclui(){
		try{
			solicitacaoDTO.setCliente((ClienteDTO) getSessaoPessoa());
			solicitacaoBO.solicitacaoInclui(solicitacaoDTO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return load();
	}

	public SolicitacaoDTO getSolicitacaoDTO() {
		return solicitacaoDTO;
	}

	public void setSolicitacaoDTO(SolicitacaoDTO solicitacaoDTO) {
		this.solicitacaoDTO = solicitacaoDTO;
	}

	public List<SolicitacaoDTO> getListSolicitacoes() {
		return listSolicitacoes;
	}

	public void setListSolicitacoes(List<SolicitacaoDTO> listSolicitacoes) {
		this.listSolicitacoes = listSolicitacoes;
	}

	public Map<Number, String> getServicos() {
		return servicos;
	}

	public void setServicos(Map<Number, String> servicos) {
		this.servicos = servicos;
	}

	public List<ServicoDTO> getListServicos() {
		return listServicos;
	}

	public void setListServicos(List<ServicoDTO> listServicos) {
		this.listServicos = listServicos;
	}
	
	

}
