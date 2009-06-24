<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function reloadPagina(){
window.parent.location.reload();
}

 
 
 function selectAction(action){
 
 	var url = '';
	var params = '';
	var submeter;
	
	
	if(action == 'voltar'){				
		url = '<c:url value="/casa/cliente!pesquisar.action?"/>';
		params='funcao=cliente';
		params+='&telaConsulta=false';	
		params+='&clienteDTO.perfil.id=3';			
		submeter = true;
	}else if(action == 'voltarCliente'){
		//var cpf = document.getElementById('cpf');			
		url = '<c:url value="/casa/cliente!consultaParaCliente.action?"/>';
		params='telaConsulta=false';	
		submeter = true;
	}else if(action == 'altera'){
		var nasc = document.getElementById('nasc');
		url = '<c:url value="/casa/cliente!altera.action?"/>';
		params+='&clienteDTO.nasc='+nasc.value;
		submeter = validaCamposAoIncluir();
	}else if(action == 'alterar'){
	
		//var cpf = document.getElementById('cpf');			
		url = '<c:url value="/casa/cliente!consultaParaCliente.action?"/>';
		params='telaConsulta=true';			
				
		submeter = true;
	}else if(action == 'exclui'){
		url = '<c:url value="/casa/cliente!exclui.action?"/>';
		submeter = true;
	}else{
		alert('Ação não encontrada.');
		submeter = false;
	}
	
	//Caso necessite de validação dos campos
	if(submeter == true){
		document.getElementById('login').action = url + params;
		document.getElementById('login').submit(); 
	}
 
 
 
 
 }