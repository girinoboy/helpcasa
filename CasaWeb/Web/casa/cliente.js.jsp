<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/cliente!inclui.action?"/>';
		submeter = validaCamposAoIncluir();
	}else if(action == 'direcionaLogin'){
		url = '<c:url value="/casa/login!load.action?"/>';
		submeter = true;
	}else if(action == 'pesquisar'){
		var cpf = document.getElementById('cpf');
		var funcao = document.getElementById('funcao');
		if(funcao.value == 'cliente'){
			url = '<c:url value="/casa/cliente!pesquisar.action?"/>';	
			params='clienteDTO.cpf='+cpf.value;
		}
		else{
			url = '<c:url value="/casa/solicitacao!load.action?"/>';	
			params='solicitacaoDTO.cliente.cpf='+cpf.value;	
		}
		submeter = true;
	}else if(action == 'voltar'){		
		url = '<c:url value="/casa/cliente!pesquisar.action?"/>';
		params='funcao=cliente';
		submeter = true;
	}else if(action == 'altera'){
		url = '<c:url value="/casa/cliente!altera.action?"/>';
		submeter = validaCamposAoIncluir();
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
	var nome = document.getElementById('nome');
	var cpf = document.getElementById('cpf');
	var cep = document.getElementById('cep');
	var endereco = document.getElementById('endereco');
	var telefone = document.getElementById('telefone');
	var rg = document.getElementById('rg');
	
	var cidade = document.getElementById('cidade');
	var uf = document.getElementById('id');
	var celular = document.getElementById('celular');
	var nasc = dojo.widget.byId('nasc');
	var email = document.getElementById('email');
	var usuario = document.getElementById('usuario');
	var senha = document.getElementById('senha');
	var senhaRepita = document.getElementById('senhaRepita');
	
	if(nome.value == ''){
		alert('O campo Nome é obrigatório.');
		nome.focus();
		return false;
	}
	if(cpf.value == ''){
		alert('O campo CPF é obrigatório.');
		cpf.focus();
		return false;
	}
	if(cep.value == ''){
		alert('O campo CEP é obrigatório.');
		cep.focus();
		return false;
	}
	if(endereco.value == ''){
		alert('O campo Endereço é obrigatório.');
		endereco.focus();
		return false;
	}
	if(telefone.value == ''){
		alert('O campo Telefone é obrigatório.');
		telefone.focus();
		return false;
	}
	if(rg.value == ''){
		alert('O campo RG é obrigatório.');
		rg.focus();
		return false;
	}
	if(cidade.value == ''){
		alert('O campo Cidade é obrigatório.');
		cidade.focus();
		return false;
	}
	if(uf.value == ''){
		alert('O campo UF é obrigatório.');
		uf.focus();
		return false;
	}
	if(celular.value == ''){
		alert('O campo Celular é obrigatório.');
		celular.focus();
		return false;
	}
	if(nasc.inputNode.value == ''){
		alert('O campo Nascimento é obrigatório.');
		nasc.focus();
		return false;
	}
	if(email.value == ''){
		alert('O campo Email é obrigatório.');
		email.focus();
		return false;
	}else if (!checkMail(email.value)){
		alert('Email invalido.');
		email.focus();
		return false;
	}
	
	if(usuario.value == ''){
		alert('O campo Usuario é obrigatório.');
		usuario.focus();
		return false;
	}
	if(senha.value == ''){
		alert('O campo Senha é obrigatório.');
		senha.focus();
		return false;
	}
	if(senhaRepita.value != senha.value){
		alert('Senha não confere.');
		senhaRepita.focus();
		return false;
	}
	return true;
}

function checkMail(mail){
    var er = new RegExp(/^[A-Za-z0-9_\-\.]+@[A-Za-z0-9_\-\.]{2,}\.[A-Za-z0-9]{2,}(\.[A-Za-z0-9])?/);
    if(typeof(mail) == "string"){
        if(er.test(mail)){ return true; }
    }else if(typeof(mail) == "object"){
        if(er.test(mail.value)){
                    return true;
                }
    }else{
        return false;
        }
}
function disableAll(){
	var arrayInput = document.getElementsByTagName('input');
	var arrayTextarea = document.getElementsByTagName('textarea');	
	for (i = 0; i < arrayInput.length; i++) {
		if(arrayInput[i].type=='radio' || arrayInput[i].type=='text' || arrayInput[i].type=='password')
    		arrayInput[i].disabled = true;    	    	
  	}
  	for(i = 0; i < arrayTextarea.length; i++){
  		arrayTextarea[i].disabled=true;
  	}
  	
}

function loadMascara(){
	jQuery('#rg').numeric();
  	//jQuery('#cpf').numeric();
	jQuery('#cpf').mask('999.999.999-99');
	//document.getElementById('cpf').value = '';
	jQuery('#cep').mask('99999-999');
	jQuery('#telefone').mask('(99) 9999-9999');
	jQuery('#celular').mask('(99) 9999-9999');
}