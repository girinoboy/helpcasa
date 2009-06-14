package br.com.persistencia.dto;

import java.util.Date;

public class ProfissaoDTO extends GenericDTO{
	
	private String nome;
	private Double precoVisita;	
	private String descricao;
	
	public ProfissaoDTO() {}
	public ProfissaoDTO(Long id){
		this.id = id;
	} 
	
	public Long getId(){
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPrecoVisita() {
		return precoVisita;
	}
	public void setPrecoVisita(Double precoVisita) {
		this.precoVisita = precoVisita;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
