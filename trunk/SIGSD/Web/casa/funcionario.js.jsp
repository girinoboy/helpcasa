<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/funcionario!inclui.action?"/>';
		submeter = validaCamposAoIncluir();
	}else if(action == 'direcionaLogin'){
		url = '<c:url value="/casa/login!load.action?"/>';
		submeter = true;
	}else if(action == 'voltar'){		
		url = '<c:url value="/casa/funcionario!load.action?"/>';		
		submeter = true;
	}else if(action == 'cadastrar'){
		url = '<c:url value="/casa/funcionario!cadastrar.action?"/>';
		submeter = true;
	}else if(action == 'altera'){
		var nasc = document.getElementById('nasc');
		url = '<c:url value="/casa/funcionario!altera.action?"/>';
		params+='&funcionarioDTO.nasc='+nasc.value;
		submeter = validaCamposAoIncluir();
	}else if(action == 'excluir'){
		url = '<c:url value="/casa/funcionario!exclui.action?"/>';
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

function loadMascara(){
  	//jQuery('#cpf').numeric();
  	jQuery('#rg').mask('9.999.999');
	jQuery('#cpf').mask('999.999.999-99');
//	document.getElementById('cpf').value = '';
	jQuery('#cep').mask('99999-999');
	jQuery('#matricula').mask('999999-9');
	jQuery('#telefone').mask('(99) 9999-9999');
	jQuery('#celular').mask('(99) 9999-9999');
	jQuery('#nasc').mask('99/99/9999');
}

function validaCamposAoIncluir(){
	var nome = document.getElementById('nome');
	var cpf = document.getElementById('cpf');
	var idprofissao = document.getElementById('idprofissao');
	var telefone = document.getElementById('telefone');
	var matricula = document.getElementById('matricula');
	
	
	var celular = document.getElementById('celular');
	var nasc = document.getElementById('nasc');
	var email = document.getElementById('email');
	var usuario = document.getElementById('usuario');
	var senha = document.getElementById('senha');
	var senhaRepita = document.getElementById('senhaRepita');
	
	if(nome.value == ''){
		alert('O Nome é obrigatório.');
		nome.focus();
		return false;
	}
	if(cpf.value == '' || cpf.value=='999.999.999-99'){
		alert('O CPF é obrigatório.');
		cpf.focus();
		return false;
	}
	if(telefone.value == ''){
		alert('O Telefone é obrigatório.');
		telefone.focus();
		return false;
	}
	if(matricula.value == ''){
		alert('A Matricula é obrigatória.');
		matricula.focus();
		return false;
	}
	if(idprofissao.value == ''){
		alert('O campo Profissão é obrigatório.');
		idprofissao.focus();
		return false;
	}
	if(celular.value == ''){
		alert('O Celular é obrigatório.');
		celular.focus();
		return false;
	}
	/*
	if(email.value == ''){
		alert('O Email é obrigatório.');
		email.focus();
		return false;
	}*/
	if (!checkMail(email.value)){
		alert('E-mail inválido. Digite novamente.');
		email.focus();
		return false;
	}
	if(!check_date(nasc.value)){
		return false;
	}
	if(usuario.value == ''){
		alert('O Usuario é obrigatório.');
		usuario.focus();
		return false;
	}
	if(senha.value == ''){
		alert('A Senha é obrigatório.');
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
   if (f<1809 || f>2009) err = 1
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
