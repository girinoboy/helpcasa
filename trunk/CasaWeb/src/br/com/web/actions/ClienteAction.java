package br.com.web.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.Mensagem;
import br.com.bo.ClienteBO;
import br.com.bo.FactoryBO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.UfDTO;

public class ClienteAction extends GenericAction {

	// BOs
	private ClienteBO clienteBO;

	// DTOs
	private ClienteDTO clienteDTO;
	
	private List<UfDTO> listUf;
	private Map<Number, String> ufs;
	
	private String funcao;

	public ClienteAction() {
		clienteBO = FactoryBO.getInstance().getClienteBO();
	}

	public String load() throws Exception {

		return clienteCadastrar();
	}
	
	public String clienteCadastrar(){
		try {
			 	ufs = new HashMap<Number, String>();
			 	ufs.put(0, "Selecione...");
			this.listUf = this.clienteBO.listUf();
			
			for(UfDTO uf: listUf ){
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

	public String inclui() throws Exception {
		try {

			this.clienteDTO = this.clienteBO.inclui(this.clienteDTO);

		} catch (Exception e) {
			e.printStackTrace();
			// Manda para o request a mensagem de exce��o vinda do BO
			getMensagemGlobal().addMensagem("Erro ao incluir Cliente.",
					Mensagem.ERRO);
			return this.direcionaMenu();
		}

		 return consultaParaCliente();
		//return "clienteCadastrar.fwd";
	}

	public String consultaParaCliente() throws Exception {
		try {
			if(getSessaoPessoa() != null && getSessaoPessoa().getCpf() !=null && this.clienteDTO == null){
				this.clienteDTO = this.clienteBO.consulta(getSessaoPessoa().getCpf());
			}
			if (this.clienteDTO != null && this.clienteDTO.getCpf() != null) {
				this.clienteDTO = this.clienteBO.consulta(this.clienteDTO.getCpf());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Manda para o request a mensagem de exce��o vinda do BO
			getMensagemGlobal().addMensagem("Erro ao buscar Cliente.",
					Mensagem.ERRO);
			return this.direcionaMenu();
		}
		return "clienteConsultar.fwd";
	}

	public String pesquisar() throws Exception {
		try {
			if (clienteDTO != null && clienteDTO.getCpf() != null && funcao != null && funcao.equals("cliente")) {
				return consultaParaCliente();
			}if(clienteDTO != null && clienteDTO.getCpf() != null && funcao != null && funcao.equals("servico")){
				return "servicosListar.fwd";
			}else
				return "clientePesquisar.fwd";
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return "clientePesquisar.fwd";
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

}
