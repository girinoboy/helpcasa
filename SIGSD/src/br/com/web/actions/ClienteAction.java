package br.com.web.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ConstantesENUM;
import br.com.Mensagem;
import br.com.RegraNegocioException;
import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.PerfilDTO;
import br.com.persistencia.dto.UfDTO;

public class ClienteAction extends GenericAction {

	// BOs
	private ClienteBO clienteBO;

	// DTOs
	private ClienteDTO clienteDTO;

	private List<UfDTO> listUf;
	private Map<Number, String> ufs;

	private String funcao;
	private Boolean alterado;

	private Boolean telaConsulta = new Boolean(false);
	private Boolean naoPesquisar = new Boolean(false);
	
	public ClienteAction() {
		clienteBO = FactoryBO.getInstance().getClienteBO();
	}

	public String load() {

		return clienteCadastrar();
	}

	public String clienteCadastrar(){
		try {
			ufs = new HashMap<Number, String>();
			//ufs.put(0, "Selecione...");
			this.listUf = this.clienteBO.listUf();

			for(UfDTO uf: listUf ){
				if(uf.getUf().equals("DF"))
				ufs.put(uf.getId(), uf.getUf());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "clienteCadastrar.fwd";

	}

	private String direcionaMenu() {

		return "abertura.fwd";
	}

	public String inclui(){
		try {
			clienteDTO.setAtivo(true);

			PerfilDTO perfil = new PerfilDTO();
			perfil.setId(ConstantesENUM.CLIENTE_ID.id());
			clienteDTO.setPerfil(perfil);
			this.clienteDTO = this.clienteBO.inclui(this.clienteDTO);

		} catch (RegraNegocioException e){
			//e.printStackTrace();
			//manda para o request a mensagem de exce��o vinda do bo
			getMensagemGlobal().setMensagens(e.getMensagens());	
			return this.load();
		}catch (Exception e) {
			e.printStackTrace();			
			getMensagemGlobal().addMensagem("Erro ao incluir Cliente.",
					Mensagem.ERRO);
			return this.load();
		}

		//return consultaParaCliente();
		//return "clienteCadastrar.fwd";
		return "paginaAbertura.fwd";
	}

	public String consultaParaCliente() {
		try {
			if(telaConsulta)//se estiver na pagina de cliente consultar
				setAlterado(true);
			else
				setAlterado(false);
			
			if(getSessaoPessoa() != null && getSessaoPessoa().getCpf() !=null && this.clienteDTO == null){
				this.clienteDTO = this.clienteBO.consulta(getSessaoPessoa().getCpf());
			}
			if (this.clienteDTO != null && this.clienteDTO.getCpf() != null) {
				this.clienteDTO = this.clienteBO.consulta(this.clienteDTO.getCpf());
				//retorna para a pesquisa caso CPF n exista
				if(clienteDTO == null){
					//getRequest().getSession().setAttribute("mens", "Login invalido.");
					naoPesquisar = true;
					setNaoPesquisar(true);
					getRequest().setAttribute("naoPesquisar", naoPesquisar);
					getMensagemGlobal().addMensagem("Cliente não encontrado no sistema.",	Mensagem.ALERTA);
					return "clienteVoltaPesquisar.fwd";
				}else setNaoPesquisar(false);
			}
			
			ufs = new HashMap<Number, String>();
			ufs.put(0, "Selecione...");
			this.listUf = this.clienteBO.listUf();

			for(UfDTO uf: listUf ){
				if(uf.getUf().equals("DF"))
				ufs.put(uf.getId(), uf.getUf());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// Manda para o request a mensagem de exce��o vinda do BO
			//getMensagemGlobal().addMensagem("Erro ao buscar Cliente.",Mensagem.ERRO);
			return this.direcionaMenu();
		}
		if(funcao != null && funcao.equals("cliente"))
			return "clienteConsultar.fwd";
		else if(funcao != null && funcao.equals("servico"))
			return "servicosListar.fwd";		

		return "clienteConsultar.fwd";
	}

	public String pesquisar() throws Exception {
		try {	
			
			Long idPerfil = getSessaoPessoa().getPerfil().getId();
			if(idPerfil.equals(ConstantesENUM.CLIENTE_ID.id())){
				if(naoPesquisar)
					setTelaConsulta(false);
				return consultaParaCliente();
			}else if (clienteDTO != null && clienteDTO.getCpf() != null && !telaConsulta && idPerfil.equals(ConstantesENUM.ADMINISTRADOR_ID.id())){
				return consultaParaCliente();
			}else
				return "clientePesquisar.fwd";
		
			/*
			if (clienteDTO != null && clienteDTO.getCpf() != null && telaConsulta && idPerfil.equals(ConstantesENUM.ADMINISTRADOR_ID.id())) 
				return consultaParaCliente();
			else
				return "clientePesquisar.fwd";*/
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return "clientePesquisar.fwd";
	}

	public String altera(){

		try{
			clienteDTO = clienteBO.altera(clienteDTO);
			setTelaConsulta(false);			
			getMensagemGlobal().addMensagem("Alterações salvas com sucesso.", Mensagem.ALERTA);
		}catch(Exception e){
			getMensagemGlobal().addMensagem("Ocorreu um erro ao alterar Cliente.", Mensagem.ALERTA);
			e.printStackTrace();			
		}
		
		return consultaParaCliente();

	}
	/*
	public String alterar(){
		setAlterado(true);
		clienteDTO.setCpf(clienteDTO.getCpf());
		return consultaParaCliente();
	}*/

	public String exclui(){
		try{
			clienteBO.exclui(clienteDTO.getCpf());
			getMensagemGlobal().addMensagem("Cadastro excluido!.", Mensagem.ALERTA);
			
			Long perfil = getSessaoPessoa().getPerfil().getId();
			if(perfil.equals(ConstantesENUM.CLIENTE_ID.id()))
				return "paginaLogin.fwd";
			else
				return "clienteVoltaPesquisar.fwd";		
		}catch(Exception e){
			clienteDTO.setUsuario(getSessaoPessoa().getUsuario());
			clienteDTO.setSenha(getSessaoPessoa().getSenha());
			e.printStackTrace();
		}
		return "paginaAbertura.fwd";
	}

	public String boleto() {

		return "clienteBoleto.fwd";
	}

	public String classificar() {

		return "clienteClassificar.fwd";
	}

	public String listar() {
		return "clienteListar.fwd";
	}

	public ClienteDTO getClienteDTO() {
		if (clienteDTO == null) {
			clienteDTO = new ClienteDTO();
		}
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<UfDTO> getListUf() {
		return listUf;
	}

	public void setListUf(List<UfDTO> listUf) {
		this.listUf = listUf;
	}

	public Map<Number, String> getUfs() {
		return ufs;
	}

	public void setUfs(Map<Number, String> ufs) {
		this.ufs = ufs;
	}

	public Boolean getAlterado() {
		return alterado;
	}

	public void setAlterado(Boolean alterado) {
		this.alterado = alterado;
	}

	public Boolean getTelaConsulta() {
		return telaConsulta;
	}

	public void setTelaConsulta(Boolean telaConsulta) {
		this.telaConsulta = telaConsulta;
	}

	public Boolean getNaoPesquisar() {
		return naoPesquisar;
	}

	public void setNaoPesquisar(Boolean naoPesquisar) {
		this.naoPesquisar = naoPesquisar;
	}

}
