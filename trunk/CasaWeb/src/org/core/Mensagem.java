package org.core;

import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável para armazenar as mensagens
 * @author marcleonio.medeiros
 *
 */
public class Mensagem implements Serializable{
	
	private boolean link;
	private String url;
    private String mensagem = "";
    private int tipo = 0;
    private List args;
    
    public static int INFORMACAO = 0;
    public static int PERGUNTA = 1;
    public static int ALERTA = 2;
    public static int ERRO = 3;
    
    public Mensagem(){}
    
    public Mensagem(String mensagem, int tipo){
    	this(mensagem, tipo, null);
    }
    
    public Mensagem(String mensagem, int tipo, List args){
		setTipo(tipo);
    	this.mensagem = mensagem;
    	this.args = args;
    }
    
    public boolean equals(Object objeto){
    	if(objeto instanceof Mensagem){
    		Mensagem outroObjeto = (Mensagem)objeto;
    		if(outroObjeto.mensagem == null ? this.mensagem == null : this.mensagem.equals(outroObjeto.mensagem)){
    			return outroObjeto.args == null ? this.args == null : this.args.equals(outroObjeto.args);
    		}
    	}
    	return false;
    }
    
    public void setMensagem(String mensagem, int tipo, List args){
    	setTipo(tipo);
        this.mensagem = mensagem;
        this.args = args;
    }
    
    public int getTipo() {
		return tipo;
	}

	private void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
    public String getMensagem(){
        return mensagem;
    }
    
    public boolean isLink() {
		return link;
	}

    public void setLink(boolean link) {
		this.link = link;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List getArgs() {
		return args;
	}
}
