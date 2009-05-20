package br.com.persistencia.dto;

import java.util.Date;

public class HistoricoDTO extends GenericDTO {
	private Date data;
	private Integer status;
	private String perfil;
	private String auteradoPor;
	private String observacao;
	private Double total;
	private SolicitacaoDTO solicitacao;
	

	public HistoricoDTO() {
	}

	public HistoricoDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getAuteradoPor() {
		return auteradoPor;
	}

	public void setAuteradoPor(String auteradoPor) {
		this.auteradoPor = auteradoPor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public SolicitacaoDTO getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoDTO solicitacao) {
		this.solicitacao = solicitacao;
	}

	
	
}
