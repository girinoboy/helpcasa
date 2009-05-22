package br.com.web.actions;

import br.com.Mensagem;
import br.com.bo.AdicionaisBO;
import br.com.bo.FactoryBO;
import br.com.persistencia.dto.AdicionaisDTO;

public class AdicionaisAction extends GenericAction{

	private AdicionaisBO adicionaisBO;
	private AdicionaisDTO adicionaisDTO;
	public AdicionaisAction() {
		adicionaisBO = FactoryBO.getInstance().getAdicionaisBO();
	}
	
	public String load(){
		
		return "load.fwd";
	}
	
	public String inclui(){
		try{
			adicionaisBO.inclui(adicionaisDTO);
			getMensagemGlobal().addMensagem("Adicional incluso com sucesso.",Mensagem.INFORMACAO);
		}catch(Exception e){
			getMensagemGlobal().addMensagem("Erro ao incluir Adicional.",Mensagem.ERRO);
			e.printStackTrace();
		}
		return load();
	}

	public AdicionaisDTO getAdicionaisDTO() {
		return adicionaisDTO;
	}

	public void setAdicionaisDTO(AdicionaisDTO adicionaisDTO) {
		this.adicionaisDTO = adicionaisDTO;
	}

}
