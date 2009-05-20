package br.com.web.actions;

import br.com.ConstantesENUM;
import br.com.Mensagem;
import br.com.MensagemLista;
import br.com.RegraNegocioException;
import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.bo.LoginBO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.PerfilDTO;
import br.com.persistencia.dto.PessoaDTO;
import br.com.persistencia.dto.UfDTO;

public class LoginAction extends GenericAction{
	
	private MensagemLista mensagemGlobal = null;
	private LoginBO loginBO;
	private ClienteBO clienteBO;
	ClienteDTO clienteDTO;
	private PessoaDTO pessoaDTO;
	private PessoaDTO pessoaSessao = null;
	
	public LoginAction(){
		loginBO = FactoryBO.getInstance().getLoginBO();
		clienteBO = FactoryBO.getInstance().getClienteBO();
	}
	
	public MensagemLista getMensagemGlobal() {
		if(this.mensagemGlobal == null){
			this.mensagemGlobal = new MensagemLista();
		}
		return this.mensagemGlobal;
	}
	
	public String load(){
		return "load.fwd";
	}

	public String checkLogin(){
		try{
			boolean logado = getRequest().getSession().getAttribute("usuarioLogadoSistema") !=null && ((Boolean)getRequest().getSession().getAttribute("usuarioLogadoSistema")).booleanValue() ? true:false;

			if(!logado){
				if(pessoaDTO !=null){
					if (isInvalid(pessoaDTO.getUsuario()) || isInvalid(pessoaDTO.getSenha())){
						getMensagemGlobal().addMensagem("Usuario ou senha incorretos.", Mensagem.ALERTA);
						return load();

					}
				}else
					return load();
				PessoaDTO usuarioLogadoSistema = loginBO.login(pessoaDTO);
				if(usuarioLogadoSistema != null){
					pessoaDTO = usuarioLogadoSistema;
					pessoaSessao = usuarioLogadoSistema;
					getRequest().getSession(true).setAttribute("pessoaDTO", pessoaDTO);
					getRequest().getSession(true).setAttribute("pessoaSessao", pessoaSessao);
					getRequest().getSession(true).setAttribute("usuarioLogadoSistema", new Boolean(true));
					return abertura();
				}else{
					getRequest().getSession().setAttribute("mensagem", "Login invalido.");
					getMensagemGlobal().addMensagem("Usuario ou senha incorretos.", Mensagem.ALERTA);
					return load();
				}				
			}else
				return abertura();

		}catch(Exception e){
			e.printStackTrace();
			getMensagemGlobal().addMensagem("O Login ou a Senha não existe no sistema. Tente novamente.",Mensagem.ERRO);			
			return load();
		}		
	}

	public String abertura(){
		return "abertura.fwd";
	}
	
	public String incluiCliente() throws Exception{
		
			clienteDTO.setAtivo(true);

			PerfilDTO perfil = new PerfilDTO();
			perfil.setId(ConstantesENUM.CLIENTE_ID.id());
			clienteDTO.setPerfil(perfil);
		/*	UfDTO uf = new UfDTO();
			uf.setId(new Long(7));
			clienteDTO.setUf(uf);*/
try{
			pessoaDTO = clienteBO.inclui(clienteDTO);
}catch (RegraNegocioException e){
	e.printStackTrace();
	//manda para o request a mensagem de exceção vinda do bo
	getMensagemGlobal().setMensagens(e.getMensagens());	
	//return this.direcionaMenu();
	//ClienteAction.this.clienteCadastrar();
}catch(Exception e){
	
}
		
		return checkLogin();
	}
	
	public String logout(){
		getRequest().getSession().removeAttribute("usuarioLogadoSistema");
		getRequest().getSession().removeAttribute("pessoaDTO");
		getRequest().getSession().removeAttribute("pessoaSessao");
		getRequest().getSession().removeAttribute("mensagem");
		
		return checkLogin();
	}

	private boolean isInvalid(String value){
		return (value == null || value.length()==0);
	}

	public PessoaDTO getPessoaDTO() {
		return pessoaDTO;
	}

	public void setPessoaDTO(PessoaDTO pessoaDTO) {
		this.pessoaDTO = pessoaDTO;
	}

	public PessoaDTO getPessoaSessao() {
		return pessoaSessao;
	}

	public void setPessoaSessao(PessoaDTO pessoaSessao) {
		this.pessoaSessao = pessoaSessao;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
	
	

}
