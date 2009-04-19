package br.com.persistencia.dto;

public class PerfilDTO extends GenericDTO {

	private String descricao;

	public PerfilDTO() {
	}

	public PerfilDTO(Long id) {
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
