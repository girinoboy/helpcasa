package br.com.web.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.core.MensagemLista;



import br.com.bo.FactoryBO;
import br.com.persistencia.dto.UsuarioDTO;

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
	 * Método responsável por retornar a Sessão do Usuário (UsuarioDTO)
	 * 
	 * @return UsuarioDTO
	 */
	public UsuarioDTO getSessaoUsuario() {
		return (UsuarioDTO) getSession().getAttribute("usuarioSessao");
	}
	
	/**
	 * Método responsável por retornar o FactoryBO com uma instância do usuário da sessão
	 * 
	 * @return FactoryBO
	 */
	public FactoryBO getFactoryBOInstance() {
		return FactoryBO.getInstance(getSessaoUsuario());
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
