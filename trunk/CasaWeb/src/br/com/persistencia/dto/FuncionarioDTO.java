package br.com.persistencia.dto;

public class FuncionarioDTO extends PessoaDTO{
	private String Matricula;
	private ProfissaoDTO profissao; 

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
}
