package br.com.persistencia.dto.cliente;

import br.com.persistencia.dto.GenericDTO;

public class ClienteDTO  extends GenericDTO{
	
	
	private String nome;
	private int cpf;
	private int cep;
	private String endereco;
	private int telefone;
	private int rg;
	private String email;
	
	public ClienteDTO(){}
	public ClienteDTO(Long id){
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
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
