package br.com.persistencia.dto;


public class ClienteDTO  extends PessoaDTO{
	
	private String endereco;
	private String situacao;
	private String cep;
	private String cidade;
	private UfDTO uf;
	private String senhaRepita;
	
	public ClienteDTO(){}
	public ClienteDTO(Long id){
		super.setId(id);
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public UfDTO getUf() {
		return uf;
	}
	public void setUf(UfDTO uf) {
		this.uf = uf;
	}
	public String getSenhaRepita() {
		return senhaRepita;
	}
	public void setSenhaRepita(String senhaRepita) {
		this.senhaRepita = senhaRepita;
	}
	
	

}
