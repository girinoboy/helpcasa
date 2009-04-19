package br.com.web.actions;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;



import br.com.MensagemLista;
import br.com.bo.FactoryBO;
import br.com.persistencia.dto.PessoaDTO;

import com.opensymphony.xwork2.ActionSupport;

public class GenericAction extends ActionSupport{
	
	private MensagemLista mensagemGlobal = null;
	
	public GenericAction(){
		super();		
	}
	
	/**
	 * M�todo respons�vel por retornar a sessao
	 * 
	 * @return HttpSession sessao
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession(false);
	}
	
	/**
	 * M�todo respons�vel por retornar o request
	 * 
	 * @return HttpServletRequest request
	 */
	public HttpServletRequest getRequest() { 
		return ServletActionContext.getRequest();
	}
	
	/**
	 * M�todo respons�vel por retornar o response
	 * 
	 * @return HttpServletResponse response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * M�todo respons�vel por retornar o context
	 * 
	 * @return ServletContext context
	 */
	public ServletContext getContext(){
		return ServletActionContext.getServletContext();
	}
	
	/**
	 * M�todo respons�vel por retornar a Sess�o da Pessoal (PessoaDTO)
	 * 
	 * @return PessoaDTO
	 */
	public PessoaDTO getSessaoPessoa() {
		return (PessoaDTO) getSession().getAttribute("pessoaSessao");
	}
	
	/**
	 * M�todo respons�vel por retornar o FactoryBO com uma inst�ncia da Pessoa da sess�o
	 * 
	 * @return FactoryBO
	 */
	public FactoryBO getFactoryBOInstance() {
		return FactoryBO.getInstance(getSessaoPessoa());
	}
	
	public MensagemLista getMensagemGlobal() {
		if(this.mensagemGlobal == null){
			this.mensagemGlobal = new MensagemLista();
		}
		return this.mensagemGlobal;
	}

	public void setMensagemGlobal(MensagemLista mensagemGlobal) {
		this.mensagemGlobal = mensagemGlobal;
	}
	
}
