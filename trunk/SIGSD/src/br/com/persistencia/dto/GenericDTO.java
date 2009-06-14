package br.com.persistencia.dto; 

import java.io.Serializable;

public abstract class GenericDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Long id;
	

	public void setId(Long id) {
		this.id = id;
	}

	

	
}
