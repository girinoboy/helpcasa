package br.com.persistencia.dto;

import java.util.Date;

public class PessoaDTO extends GenericDTO{
	
	private int pessoaId;
	
	private String usuario;
	private String senha;
	private String nome;
	private String cpf;
	private int rg;
	private String email;
	private Date nasc;
	private Boolean status;
	private Date dataCadastro;
	private int telefone;
	private int celular;
	private int telefoneComercial;
	
	private PerfilDTO perfil;

	
	public PessoaDTO(){}
	public PessoaDTO(Long id){
		this.id = id;
	} 
	
	public Long getId(){
		return id;
	}
	
	public int getPessoaId() {
		return pessoaId;
	}
	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public Date getNasc() {
		return nasc;
	}
	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	public int getTelefoneComercial() {
		return telefoneComercial;
	}
	public void setTelefoneComercial(int telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}
	public PerfilDTO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
}
