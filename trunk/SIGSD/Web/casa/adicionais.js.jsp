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
		params ='profissionalDTO.historico.solicitacao.id='+idSolicitacao.value;		
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
	//var data = dojo.widget.byId('data');
	//var data = document.getElementById('data');
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
	if(data.value == ''){
		alert('O campo Data é obrigatório.');
		data.focus();
		return false;
	}/*
	if(!check_date(data.value)){
		return false;
	}*/
	/*
	if(data.inputNode.value == ''){
		alert('O campo Data é obrigatório.');
		data.focus();
		return false;
	}
	if(observacoes.value == ''){
		alert('O campo Observacoes é obrigatório.');
		observacoes.focus();
		return false;
	}*/

	return true;
}

function loadMascara(){  	
	jQuery('#data').mask('99/99/9999');
	jQuery('#valor').numeric();
	var descricao = document.getElementById('descricao').value='';
	var valor = document.getElementById('valor').value='';	
	var data = document.getElementById('data').value='';
	var observacoes = document.getElementById('observacoes').value='';
	
}

function check_date(date) {
   var err = 0
   string = date
   var valid = "0123456789/"
   var ok = "yes";
   var temp;
   for (var i=0; i< string.length; i++) {
     temp = "" + string.substring(i, i+1);
     if (valid.indexOf(temp) == "-1") err = 1;
   }
   if (string.length != 10) err=1
   b = string.substring(3, 5)		// month
   c = string.substring(2, 3)		// '/'
   d = string.substring(0, 2)		// day 
   e = string.substring(5, 6)		// '/'
   f = string.substring(6, 10)	// year
   if (b<1 || b>12) err = 1
   if (c != '/') err = 1
   if (d<1 || d>31) err = 1
   if (e != '/') err = 1
   if (f<2009 || f>2050) err = 1
   if (b==4 || b==6 || b==9 || b==11){
     if (d==31) err=1
   }
   if (b==2){
     var g=parseInt(f/4)
     if (isNaN(g)) {
         err=1 
     }
     if (d>29) err=1
     if (d==29 && ((f/4)!=parseInt(f/4))) err=1
   }
   if (err==1) {
   	alert("Data inválida");
    return false;
   }
   else {
   	//alert("Data correta");
    return true;
   }
}