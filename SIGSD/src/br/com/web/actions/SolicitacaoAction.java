package br.com.web.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Request;
import org.jfree.data.general.DataUtilities;

import com.sun.mail.iap.Response;

import br.com.DataUtil;
import br.com.Mensagem;
import br.com.RegraNegocioException;
import br.com.bo.AdicionaisBO;
import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.bo.ServicoBO;
import br.com.bo.SolicitacaoBO;
import br.com.persistencia.dto.AdicionalDTO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;
import br.com.persistencia.dto.UfDTO;

public class SolicitacaoAction extends GenericAction{
	
	private SolicitacaoDTO solicitacaoDTO;
	private SolicitacaoBO solicitacaoBO;
	private ServicoBO servicoBO;
	private ClienteBO clienteBO;
	private AdicionaisBO adicionalBO;
	private List<SolicitacaoDTO> listSolicitacoes;
	private List<ServicoDTO> listServicos;
	private Map<Number, String> servicos;
	private List<SolicitacaoDTO> listFaturaBasica;
	private List<SolicitacaoDTO> listFaturaDetalhada;
	private List<SolicitacaoDTO> listHorariosDisponiveis;
	private List<AdicionalDTO> listAdicional;
	private Double[] distancia;
	private Long[] idsSolicitacao;
	private Boolean naoPesquisar = new Boolean(false);
	

	public SolicitacaoAction() {
		solicitacaoBO = FactoryBO.getInstance().getSolicitacaoBO();
		servicoBO = FactoryBO.getInstance().getServicoBO();
		clienteBO = FactoryBO.getInstance().getClienteBO();
		adicionalBO =FactoryBO.getInstance().getAdicionaisBO();
	}
	
	public String load() {

		servicos = new HashMap<Number, String>();
		servicos.put(0, "Selecione...");
		try {
			listServicos = servicoBO.servicosListar();
			ClienteDTO cliente =clienteBO.consulta(solicitacaoDTO.getCliente().getCpf());
			//retorna para a pesquisa caso CPF n exista
			if(cliente == null){
				setNaoPesquisar(true);
				getMensagemGlobal().addMensagem("Cliente não encontrado no sistema.",	Mensagem.ALERTA);
				return "clientePesquisar.fwd";
			}else setNaoPesquisar(false);
			solicitacaoDTO.setCliente(cliente);
					
			for (ServicoDTO servico : listServicos) {
				servicos.put(servico.getId(), servico.getNome());
			}
			//getRequest().getSession().removeAttribute("pessoaSessao");
			getRequest().getSession(true).setAttribute("pessoaSessao", solicitacaoDTO.getCliente());
			getRequest().getSession(true).setAttribute("pessoaDTO", solicitacaoDTO.getCliente());
			getRequest().getSession(true).setAttribute("pessoa", solicitacaoDTO.getCliente());
			//distancia = (Double[]) getRequest().getSession().getAttribute("distancia");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "load.fwd";
	}
	
	public String solicitacaoListar(){
		try{
			solicitacaoDTO.setCliente((ClienteDTO) getSessaoPessoa());
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
			int j = 0;
			List<SolicitacaoDTO> novalista = new ArrayList<SolicitacaoDTO>();
			for(SolicitacaoDTO solicitacao: listHorariosDisponiveis){
				FuncionarioDTO funcionario = new FuncionarioDTO();
				funcionario.setId(solicitacao.getFuncionario().getId());
				funcionario.setCep(solicitacao.getFuncionario().getCep());
				solicitacao.setOcupado(solicitacao.getOcupado());
				solicitacao.setPeriodo(solicitacao.getPeriodo());
				j=1;
				
				for(Double distancia : this.distancia){
					if(j==i){
						funcionario.setDistancia(distancia);
						solicitacao.setFuncionario(funcionario);
					}
					j++;
				}
				i++;			
				novalista.add(solicitacao);
				//solicitacao.setFuncionario(funcionario);
			}
			listHorariosDisponiveis = novalista;
			solicitacaoDTO.setCliente((ClienteDTO) getSessaoPessoa());
			//solicitacaoDTO.setFuncionario(funcionario);
			solicitacaoBO.solicitacaoInclui(solicitacaoDTO,listHorariosDisponiveis);
			getMensagemGlobal().addMensagem("Solicitacao efetuada.", Mensagem.ALERTA);
		} catch (RegraNegocioException e){
			//e.printStackTrace();
			//manda para o request a mensagem de exce��o vinda do bo
			getMensagemGlobal().setMensagens(e.getMensagens());				
		}catch(Exception e){
			getMensagemGlobal().addMensagem("Ocorreu um erro ao efetuar solicitacao.", Mensagem.ALERTA);
			e.printStackTrace();
		}
		return calcula();
	}
	
	public String calcula(){//verifica horarios disponiveis

		try{
			
			 Date hoje = DataUtil.converteDataHoraParaDate(DataUtil.pegarDataAtualCompleta());
			 
			 int total = DataUtil.diferencaDatas(hoje, solicitacaoDTO.getData(), 0, null);
			 if(total >=30){
				 getMensagemGlobal().addMensagem("A data n�o deve ser maior do que 30 dias, a contar de hoje.", Mensagem.ALERTA);
				 return load();
			 }
			 
			this.listHorariosDisponiveis = solicitacaoBO.horariosDisponiveisListar(solicitacaoDTO);
			if(listHorariosDisponiveis.size() ==0){
				getMensagemGlobal().addMensagem("Nenhum horario disponivel.", Mensagem.ALERTA);
				return load();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return load();
	}
	
	public String google(){
		return "google.fwd";
	}
	
	public String consultarFaturaBasica(){		
		
		try {			
			if(solicitacaoDTO != null){			
				ClienteDTO cliente =clienteBO.consulta(solicitacaoDTO.getCliente().getCpf());
				//retorna para a pesquisa caso CPF n exista
				if(cliente == null){
					setNaoPesquisar(true);
					getMensagemGlobal().addMensagem("Cliente não encontrado no sistema.",	Mensagem.ALERTA);
					return "clientePesquisar.fwd";
				}else setNaoPesquisar(false);
				solicitacaoDTO.setCliente(cliente);
				Long idCliente = solicitacaoDTO.getCliente().getId();
				this.listFaturaBasica = this.solicitacaoBO.consultarFaturaBasica(idCliente);
			}else
				this.listFaturaBasica = this.solicitacaoBO.consultarFaturaBasica(getSessaoPessoa().getId());			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "faturaBasica.fwd";
	}
	
	public String consultarFaturaDetalhada(){
		Long idSolicitacao = solicitacaoDTO.getId();
		try {
			this.listFaturaDetalhada = this.solicitacaoBO.consultarFaturaDetalhada(idSolicitacao);
			
			this.listAdicional= adicionalBO.adicionalListar(idSolicitacao);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "faturaDetalhada.fwd";
	}
	
	public String cancela(){
		try {
			if (idsSolicitacao != null && idsSolicitacao.length > 0) {
				solicitacaoBO.cancela(idsSolicitacao,getSessaoPessoa().getId());
			} else {
				getMensagemGlobal().addMensagem("Nenhum item selecionado.", Mensagem.ALERTA);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			getMensagemGlobal().addMensagem("Erro inesperado.", Mensagem.ALERTA);
			return solicitacaoListar();
		}
		return solicitacaoListar();
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
	
	public Long[] getIdsSolicitacao() {
		return idsSolicitacao;
	}

	public void setIdsSolicitacao(Long[] idsSolicitacao) {
		this.idsSolicitacao = idsSolicitacao;
	}

	public Double[] getDistancia() {
		return distancia;
	}

	public void setDistancia(Double[] distancia) {
		this.distancia = distancia;
	}

	public List<AdicionalDTO> getListAdicional() {
		return listAdicional;
	}

	public void setListAdicional(List<AdicionalDTO> listAdicional) {
		this.listAdicional = listAdicional;
	}

	public Boolean getNaoPesquisar() {
		return naoPesquisar;
	}

	public void setNaoPesquisar(Boolean naoPesquisar) {
		this.naoPesquisar = naoPesquisar;
	}
}
