package br.com.persistencia.dto;

public class FuncionarioDTO extends PessoaDTO{
	private String Matricula;
	private ProfissaoDTO profissao;
	private Double distancia;
	private Boolean ocupado;

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public ProfissaoDTO getProfissao() {
		return profissao;
	}

	public void setProfissao(ProfissaoDTO profissao) {
		this.profissao = profissao;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	
}
