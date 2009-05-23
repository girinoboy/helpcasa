<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'finalizar'){
		url = '<c:url value="/casa/profissional!finalizarServico.action?"/>';
		submeter = true;
	}else if(action == 'voltar'){		
		url = '<c:url value="/casa/profissional!load.action?"/>';		
		submeter = true;
	}else if(action == 'adicionar'){		
		var idSolicitacao = document.getElementById('idSolicitacao');
		url = '<c:url value="/casa/adicionais!load.action?"/>';
		params ='adicionaisDTO.solicitacao.id='+idSolicitacao.value;	
		submeter = true;
	}else if(action == 'excluir'){
		url = '<c:url value="/casa/profissional!exclui.action?"/>';
		submeter = confirm('Deseja realmente excluir o(s) selecionado(s)');
	}else{
		alert('A��o n�o encontrada.');
		submeter = false;
	}

	
	//Caso necessite de valida��o dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit(); 
	}
	
}



function checkUnCheckAll(check, nameCheckBox){

	var checkBoxs = document.getElementsByName(nameCheckBox);
	if(checkBoxs != null && checkBoxs.length > 0){
		if(check.checked){
			for(i=0; i < checkBoxs.length; i++){
				checkBoxs[i].checked = true;
			}
		}else{
			for(i=0; i < checkBoxs.length; i++){
				checkBoxs[i].checked = false;
			}
		}
	}
}