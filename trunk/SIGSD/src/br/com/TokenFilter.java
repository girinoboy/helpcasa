package br.com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;



import br.com.bo.FactoryBO;
import br.com.bo.LoginBO;
import br.com.persistencia.dto.PessoaDTO;
import br.com.web.actions.GenericAction;



public class TokenFilter implements Filter {	
	
	LoginBO autenticar = FactoryBO.getInstance().getLoginBO();
	LoginBO loginBO = FactoryBO.getInstance().getLoginBO();
	//PessoaDTO usuarioSessaoNovaArquitetura = null;
	String mensagemErro = null;
	PessoaDTO login;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest serveletRequest = (HttpServletRequest) request;			
	
		PessoaDTO usuarioSessao = (PessoaDTO) serveletRequest.getSession().getAttribute("pessoa");
		String deslogado = (String) serveletRequest.getSession().getAttribute("inicio");
			
			
			/*
			if (usuarioSessao == null){
				
				try {
					boolean autenticacao = false;
					
					if (!autenticacao){
						serveletRequest.getSession().removeAttribute("tkn");
						serveletRequest.getRequestDispatcher("/negadoAutenticacao.jsp?mensagemErro=" + mensagemErro).forward(serveletRequest, response);
					}else{
						chain.doFilter(serveletRequest, response);
					}			
					
				} catch (Exception e) {
					e.printStackTrace();					
				}
			}else{
				serveletRequest.getSession().setAttribute("usuarioLogadoSistema", new Boolean(true));
				chain.doFilter(serveletRequest, response);
			}*/
		try{
		boolean isValid = false;  
		try {  
			if (usuarioSessao != null)
				isValid = true;  
			if(deslogado != null)
				isValid = true;
		} catch (Exception e) {  
			throw new ServletException(e.getMessage());  
		}  
		if (!isValid) {             
			String url = "index.jsp";  /*
			((HttpServletResponse) response).sendRedirect(url);  
			((HttpServletResponse)response).getWriter().flush();  
			((HttpServletResponse)response).getWriter().close();*/
			/* RequestDispatcher dispatcher = request.getRequestDispatcher(url);   
			    dispatcher.forward(request, response);*/   
			    serveletRequest.getRequestDispatcher("/index.jsp").forward(serveletRequest, response);
			//serveletRequest.getRequestDispatcher("/index.jsp?").forward(serveletRequest, response);
			//return;   
		}  
		chain.doFilter(request, response);  
		}catch(Exception e){
			e.printStackTrace();
		}

	}  

	
	private boolean autenticar(HttpServletRequest request, String tkn) throws Exception{
		
		HttpServletRequest serveletRequest = (HttpServletRequest) request;
		String autenticacao = null;
		/*
		String autenticacao = autenticar.loginSIGSD(tkn);
		
		PessoaDTO pessoaSessao = autenticar.login(autenticacao);
		if (autenticacao != null && (autenticacao.indexOf("erro:") < 0) && pessoaSessao != null){
				
			serveletRequest.getSession().setAttribute("pessoaSessao", pessoaSessao);
			usuarioSessaoNovaArquitetura = loginBO.transformaUsuarioVelhoParaNova(pessoaSessao);
			serveletRequest.getSession().setAttribute("pessoaSessaoNovaArquitetura", pessoaSessaoNovaArquitetura);
			
			
		}else{*/
			serveletRequest.getSession().removeAttribute("pessoaDTO");
			serveletRequest.getSession().removeAttribute("pessoaSessao");
			serveletRequest.getSession().removeAttribute("pessoa");									
			serveletRequest.getSession().removeAttribute("usuarioLogadoSistema");
			mensagemErro = autenticacao.substring(autenticacao.indexOf(":") + 1, autenticacao.length());
			autenticacao = null;
		//}
		
		if (autenticacao == null){
			return false;
		}else{
			return true;
		}
			
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Verifica o login e senha.
	 * @return
	 */
	public PessoaDTO login(HttpServletRequest serveletRequest, String login, String senha ){
		
		PessoaDTO usuarioDTO = new PessoaDTO();
		usuarioDTO.setUsuario(login);
		usuarioDTO.setSenha(senha);
		
		LoginBO loginBO = FactoryBO.getInstance().getLoginBO();		
		
		String retorno = null;
		
		try {
			
			
			PessoaDTO usuarioLogadoSistema = loginBO.login(usuarioDTO);
			
			if (usuarioLogadoSistema != null){
				
				usuarioDTO = usuarioLogadoSistema;
				
				
				serveletRequest.getSession().setAttribute("pessoaSessao", usuarioDTO);
				serveletRequest.getSession().setAttribute("pessoaDTO", usuarioDTO);
				serveletRequest.getSession().setAttribute("pessoaSessao", usuarioDTO);
				serveletRequest.getSession().setAttribute("pessoa", usuarioDTO);				
				serveletRequest.getSession().setAttribute("usuarioLogadoSistema", new Boolean(true));
				retorno = "direcionaLogin.fwd";
				
			}else{
				serveletRequest.getSession().setAttribute("mensagem","Login ou Senha inválidos. Tente novamente.");
				retorno = "login.fwd";
			}
			
		}catch (RegraNegocioException e) {
			serveletRequest.setAttribute("mensagens", e.getMensagens());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuarioDTO;
		
	}
}
