package br.com.web.actions;

import org.core.Mensagem;
import org.core.MensagemLista;

import br.com.persistencia.dto.UsuarioDTO;


public class LoginAction extends GenericAction{
	
	private String username;
	private String password;
	private MensagemLista mensagemGlobal = null;
	private UsuarioDTO usuarioSessao = null;
	
	public MensagemLista getMensagemGlobal() {
		if(this.mensagemGlobal == null){
			this.mensagemGlobal = new MensagemLista();
		}
		return this.mensagemGlobal;
	}

	public String checkLogin(){
		try{
			if (isInvalid(username) || isInvalid(password)){
				getMensagemGlobal().addMensagem("Usuario ou senha incorretos.", Mensagem.ALERTA); 
				return "invalid.fwd";
			}else{
				getRequest().getSession(true).setAttribute("usuarioSessao", usuarioSessao);
				getRequest().getSession(true).setAttribute("usuarioLogadoSistema", new Boolean(true));
			}
		}catch(Exception e){
			getMensagemGlobal().addMensagem("O Login ou a Senha não existe no sistema. Tente novamente.",Mensagem.ERRO);
			e.printStackTrace();
		}
		return "valid.fwd";
	}

	public String abertura(){
		return "abertura.fwd";
	}

	private boolean isInvalid(String value){
		return (value == null || value.length()==0);
	}
	
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}

}
