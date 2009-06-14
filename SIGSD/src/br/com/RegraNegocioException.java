package br.com;

import java.util.ArrayList;


/**
 * @author Marcleônio do N. Medeiros
 * Uma Exceção personalizada 
 */
public class RegraNegocioException extends Exception {

	private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
	private int indice = 0;
	
	/**
	 * Construtor que adiciona apenas uma mensagem
	 * @param String
	 */
	public RegraNegocioException(String mensagem, int tipo){
	    super();
	    setMensagem(mensagem, tipo);
	}
	
	/**
	 * Construtor que adiciona apenas uma mensagem
	 * @param MensagemTO
	 */
	public RegraNegocioException(Mensagem mensagemTO){
	    super();
	    setMensagem(mensagemTO);
	}
	
	/**
	 * Construtor que adiciona uma lista de mensagens
	 * @param MensagemListaTO
	 */
	public RegraNegocioException(MensagemLista mensagemListaTO){
	    super();
	    setMensagens(mensagemListaTO);
	}
    
	/**
	 * Método que adiciona uma mensagem de erro.
	 * @param mensagem
	 */
    public void setMensagem(String mensagem, int tipo){
    	this.mensagens.add(new Mensagem(mensagem, tipo));
    }
    
    /**
     * Método que adiciona uma mensagem de erro.
     * @param mensagemTO
     */
    public void setMensagem(Mensagem mensagemTO){
        this.mensagens.add(mensagemTO);
    }
    
    /**
     * Método que adiciona uma mensagem de erro
     * @param mensagem
     */
    public void setMensagens(MensagemLista mensagemListaTO){
        this.mensagens = mensagemListaTO.getMensagens();
    }
    /**
     * Método que passa para a próxima mensagem de erro, se existir
     *
     */
    public void proximaMensagem(){
        if(indice<this.mensagens.size()){
            indice = indice + 1;
        }
    }
    
    /**
     * Retorna uma mensagem de erro de acordo com o índice
     * @return String(mensagem) - Se existir uma mensagem
     * @return null - Se não existir mensagem
     */
    public String getMensagem(){
        if(mensagens.size()>=indice){
            return this.mensagens.get(indice).getMensagem();
        }
        return null;
    }
    /**
     * Método que retorna todas as mensagens de erro, se existir!
     * @return Vector(String) - Se existir + de uma mensagem
     * @return null = Se não existir mensagem
     */
    public ArrayList<Mensagem> getMensagens(){
        if(mensagens.size()>=0){
            return this.mensagens;
        }
        return null;
    }
    
    /**
     * Método que retorna o número de erros existentes
     * @return int
     */
    public int getNumeroMensagens(){
        return mensagens.size();
    }
    
}
