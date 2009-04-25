package br.com.web.actions;

import java.util.List;

import br.com.bo.FactoryBO;
import br.com.bo.ServicoBO;
import br.com.persistencia.dto.ServicoDTO;

public class ServicoAction extends GenericAction{
	private ServicoBO servicoBO;
	private List<ServicoDTO> listServicos;
	private Long[] idsServico;
	private ServicoDTO servicoDTO;

	public ServicoAction() {
		super();
		servicoBO = FactoryBO.getInstance().getServicoBO();
	}
	
	public String load() throws Exception{
		return listar();
	}

	public String listar() throws Exception{
		listServicos = servicoBO.servicosListar();
		return "listar.fwd";
	}
	
	public String inclui() throws Exception {
		try {
			servicoBO.inclui(servicoDTO);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return load();
	}
	
	public String exclui() throws Exception {
		try {
			if (idsServico != null && idsServico.length > 0) {
				servicoBO.exclui(idsServico);
			} else {
				System.out.println("Nenhum item selecionado.");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return load();
	}
	
	public String cadastrar(){
		return "cadastrar.fwd";
	}

	public List<ServicoDTO> getListServicos() {
		return listServicos;
	}

	public void setListServicos(List<ServicoDTO> listServicos) {
		this.listServicos = listServicos;
	}

	public Long[] getIdsServico() {
		return idsServico;
	}

	public void setIdsServico(Long[] idsServico) {
		this.idsServico = idsServico;
	}

	public ServicoDTO getServicoDTO() {
		return servicoDTO;
	}

	public void setServicoDTO(ServicoDTO servicoDTO) {
		this.servicoDTO = servicoDTO;
	}
	
	

}
