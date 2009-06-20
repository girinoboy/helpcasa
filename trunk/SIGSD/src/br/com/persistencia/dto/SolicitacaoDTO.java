package br.com.persistencia.dto;

import java.util.Date;

public class SolicitacaoDTO extends GenericDTO{
	
	private Date data;
	private Integer periodo;
	private ClienteDTO cliente;
	private FuncionarioDTO funcionario;
	private ServicoDTO servico;
	private NotaDTO nota;
	private Double total;
	private Boolean ocupado;
	
	public SolicitacaoDTO(){
		
	}
	
	public SolicitacaoDTO(Long id){
		this.id = id;
	} 
	
	public Long getId(){
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	public ServicoDTO getServico() {
		return servico;
	}

	public void setServico(ServicoDTO servico) {
		this.servico = servico;
	}

	public NotaDTO getNota() {
		return nota;
	}

	public void setNota(NotaDTO nota) {
		this.nota = nota;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}
	
}
