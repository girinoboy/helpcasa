package br.com.web.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Request;

import com.sun.mail.iap.Response;

import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.bo.ServicoBO;
import br.com.bo.SolicitacaoBO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.FuncionarioDTO;
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
	private List<SolicitacaoDTO> listFaturaBasica;
	private List<SolicitacaoDTO> listFaturaDetalhada;
	private List<SolicitacaoDTO> listHorariosDisponiveis;
	private Double[] distancia;
	


	public Double[] getDistancia() {
		return distancia;
	}

	public void setDistancia(Double[] distancia) {
		this.distancia = distancia;
	}

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
			//getRequest().getSession().removeAttribute("pessoaSessao");
			getRequest().getSession(true).setAttribute("pessoaSessao", solicitacaoDTO.getCliente());
			distancia = (Double[]) getRequest().getSession().getAttribute("distancia");
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
			this.listHorariosDisponiveis = solicitacaoBO.horariosDisponiveisListar(solicitacaoDTO);
			
			int i = 1;
			int j = 1;
			List<SolicitacaoDTO> novalista = new ArrayList<SolicitacaoDTO>();
			for(SolicitacaoDTO solicitacao: listHorariosDisponiveis){
				FuncionarioDTO funcionario = new FuncionarioDTO();
				funcionario.setId(solicitacao.getId());
				funcionario.setCep(solicitacao.getFuncionario().getCep());
				solicitacao.setPeriodo(solicitacao.getPeriodo());
				i++;
				
				for(Double distancia : this.distancia){
					if(j==i){
						funcionario.setDistancia(distancia);
						solicitacao.setFuncionario(funcionario);
					}
					j++;
				}
								
				novalista.add(solicitacao);
				//solicitacao.setFuncionario(funcionario);
			}
			listHorariosDisponiveis = novalista;
			solicitacaoDTO.setCliente((ClienteDTO) getSessaoPessoa());
			//solicitacaoDTO.setFuncionario(funcionario);
			solicitacaoBO.solicitacaoInclui(solicitacaoDTO,listHorariosDisponiveis);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return load();
	}
	
	public String calcula(){

		try{
			
			this.listHorariosDisponiveis = solicitacaoBO.horariosDisponiveisListar(solicitacaoDTO);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return load();
	}
	
	public String google(){
		return "google.fwd";
	}
	
	public String consultarFaturaBasica(){

		Long idCliente = null;
		try {

			if(getSessaoPessoa().getId() != null){
				idCliente = getSessaoPessoa().getId();
				this.listFaturaBasica = this.solicitacaoBO.consultarFaturaBasica(idCliente);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "faturaBasica.fwd";
	}
	
	public String consultarFaturaDetalhada(){
		Long idSolicitacao = solicitacaoDTO.getId();
		try {
			this.listFaturaDetalhada = this.solicitacaoBO.consultarFaturaDetalhada(idSolicitacao);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public String alteraCancela(){
		
		return "solicitacoesListar.fwd";
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

	public List<SolicitacaoDTO> getListFaturaBasica() {
		return listFaturaBasica;
	}

	public void setListFaturaBasica(List<SolicitacaoDTO> listFaturaBasica) {
		this.listFaturaBasica = listFaturaBasica;
	}

	public List<SolicitacaoDTO> getListFaturaDetalhada() {
		return listFaturaDetalhada;
	}

	public void setListFaturaDetalhada(List<SolicitacaoDTO> listFaturaDetalhada) {
		this.listFaturaDetalhada = listFaturaDetalhada;
	}

	public List<SolicitacaoDTO> getListHorariosDisponiveis() {
		return listHorariosDisponiveis;
	}

	public void setListHorariosDisponiveis(
			List<SolicitacaoDTO> listHorariosDisponiveis) {
		this.listHorariosDisponiveis = listHorariosDisponiveis;
	}
	
	

}
