package br.com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;



import br.com.bo.FactoryBO;
import br.com.bo.LoginBO;
import br.com.persistencia.dto.PessoaDTO;



public class TokenFilter implements Filter {	
	
	LoginBO autenticar = FactoryBO.getInstance().getLoginBO();
	LoginBO loginBO = FactoryBO.getInstance().getLoginBO();
	//PessoaDTO usuarioSessaoNovaArquitetura = null;
	String mensagemErro = null;
	
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filter) throws IOException, ServletException {
		
		HttpServletRequest serveletRequest = (HttpServletRequest) request;
		String nomeJSP = serveletRequest.getServletPath().replaceFirst("/", "");
		
		System.out.println("passou aki");
		String tkn = serveletRequest.getParameter("tkn");
		
		if (tkn != null && !tkn.equals("null") && !tkn.equals("")){
			String urlDestino = serveletRequest.getParameter("urlDestino");
			
			PessoaDTO usuarioSessao = (PessoaDTO) serveletRequest.getSession().getAttribute("usuarioSessao");
			
			if (usuarioSessao == null){
				System.out.println("passou aki");
				try {
					boolean autenticacao = autenticar(serveletRequest, tkn);
					
					if (!autenticacao){
						serveletRequest.getSession().removeAttribute("tkn");
						serveletRequest.getRequestDispatcher("/negadoAutenticacao.jsp?mensagemErro=" + mensagemErro).forward(serveletRequest, response);
					}else{
						filter.doFilter(serveletRequest, response);
					}			
					
				} catch (Exception e) {
					e.printStackTrace();					
				}
			}else{
				serveletRequest.getSession().setAttribute("usuarioLogadoSistema", new Boolean(true));
				filter.doFilter(serveletRequest, response);
			}
		}else{
			filter.doFilter(serveletRequest, response);
		}
	}
	
	private boolean autenticar(HttpServletRequest request, String tkn) throws Exception{
		System.out.println("passou aki");
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
		System.out.println("passou aki");
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("passou aki");
	}
	
	/**
	 * Verifica o login e senha.
	 * @return
	 */
	public PessoaDTO login(HttpServletRequest serveletRequest, String login, String senha ){
		System.out.println("passou aki");
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
