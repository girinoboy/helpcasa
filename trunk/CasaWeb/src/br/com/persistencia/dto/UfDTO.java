package br.com.persistencia.dto;

public class UfDTO extends GenericDTO{

	private String uf;
	private String descricao;
	
	public UfDTO() {
		// TODO Auto-generated constructor stub
	}
	public UfDTO(Long id){
		this.id = id;
	} 
	
	public Long getId(){
		return id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
