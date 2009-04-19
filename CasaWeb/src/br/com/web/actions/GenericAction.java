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
	 * Método responsável por retornar a sessao
	 * 
	 * @return HttpSession sessao
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession(false);
	}
	
	/**
	 * Método responsável por retornar o request
	 * 
	 * @return HttpServletRequest request
	 */
	public HttpServletRequest getRequest() { 
		return ServletActionContext.getRequest();
	}
	
	/**
	 * Método responsável por retornar o response
	 * 
	 * @return HttpServletResponse response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * Método responsável por retornar o context
	 * 
	 * @return ServletContext context
	 */
	public ServletContext getContext(){
		return ServletActionContext.getServletContext();
	}
	
	/**
	 * Método responsável por retornar a Sessão da Pessoal (PessoaDTO)
	 * 
	 * @return PessoaDTO
	 */
	public PessoaDTO getSessaoPessoa() {
		return (PessoaDTO) getSession().getAttribute("pessoaSessao");
	}
	
	/**
	 * Método responsável por retornar o FactoryBO com uma instância da Pessoa da sessão
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
