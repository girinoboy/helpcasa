package br.com.web.actions;


public class AdministradorAction extends GenericAction{
	
	public String load(){
		
		return "";
	}
	
	
	public String manterServicos(){
		return "administradorManterServico.fwd";
	}
	
	public String manterFuncionario(){
		
		return "administradorManterFuncionario.fwd";
	}

}
