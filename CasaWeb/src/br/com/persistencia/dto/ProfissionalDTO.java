package br.com.persistencia.dto;

import java.util.Date;

public class ProfissionalDTO extends FuncionarioDTO {
	private Boolean listar;
	private Date data;
	private HistoricoDTO historico;

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

	public HistoricoDTO getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoDTO historico) {
		this.historico = historico;
	}

}
