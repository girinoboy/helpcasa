package br.com.web.actions;

import java.util.List;

import br.com.Mensagem;
import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.bo.HistoricoBO;
import br.com.bo.NotasBO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.NotaDTO;

public class HistoricoAction extends GenericAction{
	
	private HistoricoDTO historicoDTO;
	private HistoricoBO historicoBO;
	private ClienteBO clienteBO;
	private List<HistoricoDTO> listHistorico;
	private List<HistoricoDTO> listaHistoricoDetalhada;
	private NotasBO notasBO;
	private List<NotaDTO> listNota;

	public HistoricoAction() {
		historicoBO = FactoryBO.getInstance().getHistoricoBO();
		notasBO = FactoryBO.getInstance().getNotasBO();
		clienteBO = FactoryBO.getInstance().getClienteBO();
	}
	
	public String load(){
		try {
			listNota = notasBO.consultarNotas();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return historicoListar();
	}
	
	public String historicoListar(){
		try{			
			
			ClienteDTO cliente =clienteBO.consulta(historicoDTO.getSolicitacao().getCliente().getCpf());
			//retorna para a pesquisa caso CPF n exista
			if(cliente == null){
				getMensagemGlobal().addMensagem("Cliente não encontrado no sistema.",	Mensagem.ALERTA);
				return "clientePesquisar.fwd";
			}
			historicoDTO.getSolicitacao().setCliente(cliente);			
			Long idCliente = historicoDTO.getSolicitacao().getCliente().getId();
			this.listHistorico = historicoBO.historicoListar(idCliente);
			this.listNota = notasBO.consultarNotas();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "load.fwd";
	}

	public String aplicaClassificacao(){
		try{			
			Long idSolicitacao = historicoDTO.getSolicitacao().getId();
			Long idNota = historicoDTO.getSolicitacao().getNota().getId();
			this.historicoBO.aplicaClassificacao(idSolicitacao,idNota);
			getSessaoPessoa();
		}catch (Exception e) {
			e.printStackTrace();			
		}

		return load();
	}
	
	public String consultarHistorico(){
		
		Long idSolicitacao = historicoDTO.getSolicitacao().getId();
		try {
			listaHistoricoDetalhada = historicoBO.consultarHistorico(idSolicitacao);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "historicoConsultar.fwd";
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

	public List<HistoricoDTO> getListaHistoricoDetalhada() {
		return listaHistoricoDetalhada;
	}

	public void setListaHistoricoDetalhada(
			List<HistoricoDTO> listaHistoricoDetalhada) {
		this.listaHistoricoDetalhada = listaHistoricoDetalhada;
	}

}
