<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>


function selectAction(action,valor){
	
	var url = '';
	var params = '';
	var submeter;

	if(action =='aplicaClassificacao'){
	
		var notas = document.getElementById(valor.id);
		var indexSelect = notas.selectedIndex;
		var valueSelected = notas.options[indexSelect].text;
		var idNota;
		
		url = '<c:url value="/casa/historico!aplicaClassificacao.action?"/>';
		params = 'historicoDTO.solicitacao.id=' + valor.value;
		if(valueSelected =='nenhuma'){
		 idNota=1;
		}
		if(valueSelected =='Excelente'){
		 idNota=2;
		}
		if(valueSelected =='�timo'){
		 idNota=3;
		}
		if(valueSelected =='Bom'){
		 idNota=4;
		}
		if(valueSelected =='Ruim'){
		 idNota=5;
		}
		if(valueSelected =='P�ssimo'){
		 idNota=6;
		}
		params +='&historicoDTO.solicitacao.nota.id='+idNota;
		//alert(valor.value+'-'+valor.name+'-'+valueSelected);
		//alert(url + params);  
		submeter = true;
	}else if(action == 'voltar'){		
		url = '<c:url value="/casa/historico!load.action?"/>';		
		submeter = true;
	}else{
		alert('Solicita��o n�o poder� ser encaminhada, pois a a��o n�o foi encontrada.');
		submeter = false;
	}
	
	//Caso necessite de valida��o dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit();
	}
}

