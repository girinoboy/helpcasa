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
	 * M�todo respons�vel por retornar a Sess�o do Usu�rio (UsuarioDTO)
	 * 
	 * @return UsuarioDTO
	 */
	public UsuarioDTO getSessaoUsuario() {
		return (UsuarioDTO) getSession().getAttribute("usuarioSessao");
	}
	
	/**
	 * M�todo respons�vel por retornar o FactoryBO com uma inst�ncia do usu�rio da sess�o
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
