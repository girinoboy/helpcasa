package br.com.persistencia.dto;

import java.util.Date;

public class ProfissionalDTO extends FuncionarioDTO {
	private Boolean listar;
	private Date data;
	private SolicitacaoDTO solicitacao;
	
	public Boolean getListar() {
		return listar;
	}

	public void setListar(Boolean listar) {
		this.listar = listar;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public SolicitacaoDTO getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoDTO solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	
}
