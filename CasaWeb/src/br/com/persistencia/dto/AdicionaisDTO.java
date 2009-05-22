package br.com.persistencia.dto;

import java.util.Date;

public class AdicionaisDTO extends GenericDTO{

	private String observacao;
	private String descricao;
	private Double valor;
	private Date data;
	private SolicitacaoDTO solicitacao;
	
	public AdicionaisDTO(){}
	public AdicionaisDTO(Long id){
		this.id = id;
	} 
	
	public Long getId(){
		return id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
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
