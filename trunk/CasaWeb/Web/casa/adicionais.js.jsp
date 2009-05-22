<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/adicionais!inclui.action?"/>';
		submeter = validaCamposAoIncluir();
	}else if(action == 'voltar'){	
		var idSolicitacao = document.getElementById('idSolicitacao');	
		url = '<c:url value="/casa/profissional!consultarAgendaDetalhada.action?"/>';
		params ='profissionalDTO.solicitacao.id='+idSolicitacao.value;		
		submeter = true;
	}else if(action == 'adicionar'){		
		url = '<c:url value="/casa/adicionais!load.action?"/>';		
		submeter = true;
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

function validaCamposAoIncluir(){
	var descricao = document.getElementById('descricao');
	var valor = document.getElementById('valor');
	var data = dojo.widget.byId('data');
	var observacoes = document.getElementById('observacoes');
	
	
	if(descricao.value == ''){
		alert('O campo Descricao é obrigatório.');
		descricao.focus();
		return false;
	}
	if(valor.value == ''){
		alert('O campo Valor é obrigatório.');
		valor.focus();
		return false;
	}
	if(data.inputNode.value == ''){
		alert('O campo Data é obrigatório.');
		data.focus();
		return false;
	}/*
	if(observacoes.value == ''){
		alert('O campo Observacoes é obrigatório.');
		observacoes.focus();
		return false;
	}*/

	return true;
}