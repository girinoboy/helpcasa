package br.com;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsável para armazenar as mensagens de erro, de aviso, e de mensagens de regras de negócio, que por ventura, lançar a exceção
 * @author marcleonio.medeiros
 *
 */
public class MensagemLista implements Serializable{

    private ArrayList<Mensagem> mensagens;
    
    public MensagemLista(){
        mensagens = new ArrayList<Mensagem>();
    }
    
    public MensagemLista(String mensagem, int tipo){
        mensagens = new ArrayList<Mensagem>();
        addMensagem(mensagem, tipo, null);
    }
    
    public MensagemLista(String mensagem, int tipo, List args){
        mensagens = new ArrayList<Mensagem>();
        addMensagem(mensagem, tipo, args);
    }
    
    public void addMensagem(String mensagem, int tipo){
    	addMensagem(mensagem, tipo, null);
    }
    
    public void addMensagens(ArrayList<Mensagem> mensagens){
    	this.mensagens.addAll(mensagens);
    }
    
    public void addMensagem(String mensagem, int tipo, List args){
    	Mensagem mensagemTemp = new Mensagem(mensagem, tipo, args);
    	if(!this.mensagens.contains(mensagemTemp)){
    		this.mensagens.add(mensagemTemp);
    	}
    }
    
    public ArrayList<Mensagem> getMensagens(){
        return this.mensagens;
    }
    public void setMensagens(ArrayList<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public void removeTodosElementos(){
        this.mensagens.clear();
    }
    
    public int getNumeroRegistros(){
        return this.mensagens.size();
    }
}
