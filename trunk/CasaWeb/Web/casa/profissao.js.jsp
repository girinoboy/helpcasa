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
		submeter = confirm('Deseja realmente excluir o(s) selecionado(s)?');
	}else if(action == 'altera'){
		url = '<c:url value="/casa/profissao!altera.action?"/>';
		submeter = validaCamposAoIncluir();
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

function validaCamposAoIncluir(){
	var nome = document.getElementById('nome');
	var precoVisita = document.getElementById('precoVisita');
	var descricao = document.getElementById('descricao');
	
	if(nome.value == ''){
		alert('O campo Profiss�o � obrigat�rio.');
		nome.focus();
		return false;
	}
	if(precoVisita.value == ''){
		alert('O campo Pre�o da Visita � obrigat�rio.');
		precoVisita.focus();
		return false;
	}
	if(descricao.value == ''){
		alert('O campo Descri��o � obrigat�riO.');
		descricao.focus();
		return false;
	}
	return true;
}

function loadMascara(){
	jQuery('#precoVisita').numeric();
	
}