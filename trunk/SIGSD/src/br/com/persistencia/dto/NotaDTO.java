package br.com.persistencia.dto;

public class NotaDTO extends GenericDTO {

	private String descricao;

	public NotaDTO() {
	}

	public NotaDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
