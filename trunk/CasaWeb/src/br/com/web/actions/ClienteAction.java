package br.com.web.actions;


import br.com.Mensagem;
import br.com.bo.ClienteBO;
import br.com.persistencia.dto.ClienteDTO;

public class ClienteAction extends GenericAction{
	
	// BOs
	private ClienteBO clienteBO;
	
	//DTOs
	private ClienteDTO clienteDTO;
	
	
	//BOs
	protected ClienteBO getClienteBO() {
		if(this.clienteBO == null){
			clienteBO = getFactoryBOInstance().getClienteBO();
		}
		return clienteBO;
	}
	
	public String load()throws Exception {
		
		
		return inclui();
	}

	private String direcionaMenu() {
		
		return "abertura.fwd";
	}

	public String inclui()throws Exception {
		try{

		//	this.clienteDTO = this.getClienteBO().inclui(this.clienteDTO);

		} catch (Exception e){
			e.printStackTrace();
			//Manda para o request a mensagem de exce��o vinda do BO
			getMensagemGlobal().addMensagem("Erro ao incluir Cliente.", Mensagem.ERRO);
			return this.direcionaMenu();
		}
		
		//return consultaParaCliente();
		return "clienteInclusao.fwd";
	}
	
	public String consultaParaCliente()throws Exception {
		try{
			
			if(this.clienteDTO != null && this.clienteDTO.getId() != null){
				this.clienteDTO = this.getClienteBO().consulta(this.clienteDTO.getId());
			}
		} catch (Exception e){
			e.printStackTrace();
			//Manda para o request a mensagem de exce��o vinda do BO
			getMensagemGlobal().addMensagem("Erro ao buscar Cliente.", Mensagem.ERRO);
			return this.direcionaMenu();
		}
		return "clienteConsultar.fwd";
	}
	
	public String boleto(){
		
		return "clienteBoleto.fwd";
	}
	
	public String classificar(){
		
		return "clienteClassificar.fwd";
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

}
