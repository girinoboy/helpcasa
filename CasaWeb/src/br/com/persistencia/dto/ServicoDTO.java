package br.com.persistencia.dto;

import java.util.Date;

public class ServicoDTO extends GenericDTO{

	private ProfissaoDTO profissaoDTO;
	private String nome;
	private String descricao;
	private Date dataCadastro;
	
	public ServicoDTO() {}
	public ServicoDTO(Long id){
		super.setId(id);
	}
	public Long getId(){
		return id;
	}
	public ProfissaoDTO getProfissaoDTO() {
		return profissaoDTO;
	}
	public void setProfissaoDTO(ProfissaoDTO profissaoDTO) {
		this.profissaoDTO = profissaoDTO;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
