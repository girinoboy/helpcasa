package br.com.web.actions;

import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.FuncionarioBO;
import br.com.persistencia.dto.FuncionarioDTO;

public class FuncionarioAction extends GenericAction {

	private FuncionarioBO funcionarioBO;
	private FuncionarioDTO funcionarioDTO;
	private List<FuncionarioDTO> listFuncionarios;
	private Long[] idsFuncionario;

	public FuncionarioAction() {
		funcionarioBO = FactoryBO.getInstance().getFuncionarioBO();
	}
	
	public String load(){
		return funcionariosListar();
	}

	public String funcionariosListar() {
		try {
			this.listFuncionarios = funcionarioBO.funcionariosListar();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "funcionariosListar.fwd";
	}

	public String cadastrar() {
		return "cadastrar.fwd";
	}

	public String inclui() throws Exception {
		try {
			funcionarioBO.inclui(funcionarioDTO);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return load();
	}
	
	public String exclui() throws Exception {
		try {
			if (idsFuncionario != null && idsFuncionario.length > 0) {
				funcionarioBO.exclui(idsFuncionario);
			} else {
				System.out.println("Nenhum item selecionado.");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return load();
	}

	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}

	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}

	public List<FuncionarioDTO> getListFuncionarios() {
		return listFuncionarios;
	}

	public void setListFuncionarios(List<FuncionarioDTO> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}

	public Long[] getIdsFuncionario() {
		return idsFuncionario;
	}

	public void setIdsFuncionario(Long[] idsFuncionario) {
		this.idsFuncionario = idsFuncionario;
	}
	

}
