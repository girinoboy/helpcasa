<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/profissao!inclui.action?"/>';
		submeter = validaCamposAoIncluir();
	}else if(action == 'cadastrar'){
		url = '<c:url value="/casa/profissao!cadastrar.action?"/>';
		submeter = true;
	}else if(action == 'voltar'){
		url = '<c:url value="/casa/profissao!load.action?"/>';
		submeter = true;
	}else if(action == 'excluir'){
		url = '<c:url value="/casa/profissao!exclui.action?"/>';
		submeter = confirm('Deseja realmente excluir o(s) selecionado(s)');
	}else{
		alert('Ação não encontrada.');
		submeter = false;
	}
	
	//Caso necessite de validação dos campos
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

function validaCamposAoIncluir(){

	return true;
}