package br.com;

public class DTOFactoryException extends Exception {

	/*****Sobrecarga de Construtores e override de getMessage   *****/

	private String mensagemErro;
	private String mensagemAdicional;

	public DTOFactoryException(String strExp) {
		this.mensagemErro = strExp;
	}

	public DTOFactoryException(String strExp, String strAdicional) {
		this.mensagemErro = strExp;
		this.mensagemAdicional = strAdicional;
	}

	public String getMessageComplement() {
		if (this.mensagemAdicional != null)
			return this.mensagemAdicional;
		else
			return "";
	}

	public String getMessage() {
		return this.mensagemErro;
	}

}
