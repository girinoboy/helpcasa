package br.com.web.actions;

import br.com.persistencia.dto.ProfissionalDTO;

/**
 * @author Ivana
 *
 */
public class ProfissionalAction extends GenericAction {
	
	private ProfissionalDTO profissionalDTO;

	public ProfissionalAction() {
		// TODO Auto-generated constructor stub
	}

	public String load(){

		return "load.fwd";
	}
	
	public String consultar(){
		try{
			profissionalDTO.setListar(true);

		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();			
		}
		return load();
	}

	public ProfissionalDTO getProfissionalDTO() {
		if(profissionalDTO==null){
			profissionalDTO = new ProfissionalDTO();	
		}
		return profissionalDTO;
	}

	public void setProfissionalDTO(ProfissionalDTO profissionalDTO) {
		this.profissionalDTO = profissionalDTO;
	}

}
