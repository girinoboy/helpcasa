package br.com.web.actions;

import br.com.Mensagem;
import br.com.bo.AdicionaisBO;
import br.com.bo.FactoryBO;
import br.com.persistencia.dto.AdicionalDTO;

public class AdicionaisAction extends GenericAction{

	private AdicionaisBO adicionaisBO;
	private AdicionalDTO adicionaisDTO;
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

	public AdicionalDTO getAdicionaisDTO() {
		return adicionaisDTO;
	}

	public void setAdicionaisDTO(AdicionalDTO adicionaisDTO) {
		this.adicionaisDTO = adicionaisDTO;
	}

}
