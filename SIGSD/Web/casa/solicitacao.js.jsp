<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action,validaTudo){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/solicitacao!solicitacaoInclui.action?"/>';
		submeter = validaCamposAoIncluir(validaTudo);
		caculaDistanciaFixa();
	}else if(action == 'voltar'){
		url = '<c:url value="/casa/cliente!pesquisar.action?"/>';
		params='funcao=servico';
		submeter = true;
	}else if(action == 'disponiveis'){
		var arraydistancia = new Array();
		arraydistancia[0]='2.34';
		arraydistancia[1]='2.35';
		arraydistancia[2]='2.36';
		url = '<c:url value="/casa/solicitacao!calcula.action?"/>';
		params='clienteDTO.cpf='+cpf.value;
		submeter =  validaCamposAoIncluir();
	}else if(action == 'listar'){
		url = '<c:url value="/casa/solicitacao!solicitacaoListar.action?"/>';		
		submeter = true;
	}else{
		alert('Ação não encontrada.');
		submeter = false;
	}
	//var a = setDirections('73340-702','70390-130','pt_BR');
	//alert(a.substr(0,a.indexOf('&'))); 
	//document.getElementById('distancia1').value = setDirections('73340-702','70390-130','pt_BR').substr(0,4);
	
	//Caso necessite de validação dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit(); 
	}
	
}

function validaCamposAoIncluir(validaTudo){
	var data = document.getElementById('data');
	var id = document.getElementById('id');
	var periodo1 = document.getElementById('periodo1');
	var periodo2 = document.getElementById('periodo2');
	var periodo3 = document.getElementById('periodo3');
	
	if(data.value == ''){
		alert('A Data é obrigatória.');
		data.inputNode.focus();
		return false;
	}
	if(id.value == 0){
		alert('O Serviço é obrigatório.');
		id.focus();
		return false;
	}
	if(validaTudo)
	if(periodo1.checked == false && periodo2.checked == false && periodo3.checked == false){
		alert('O Periodo é obrigatório.');
		id.focus();
		return false;
	}
	
	return true;
}

function caculaDistanciaFixa(){
	//var distancias = new Array();
	var max = document.getElementById('max').value;
	var distancia;
	var cep;	
	for(var i=1;i<=max;i++){
		cep = document.getElementById('cep'+i+'').value;
		distancia = setDirections('${pessoaSessao.cep}',cep,'pt_BR');
		
		document.getElementById('distancia'+i+'').value = distancia.substr(0,distancia.indexOf('&'));
	}
}

 	var map;
    var gdir;
    var geocoder = null;
    var addressMarker;
   // var distancia = gdit.getDistance().html;

    function initialize() {
      if (GBrowserIsCompatible()) {      
        map = new GMap2(document.getElementById("map_canvas"));
        gdir = new GDirections(map, document.getElementById("directions"));
        GEvent.addListener(gdir, "load", onGDirectionsLoad);
        GEvent.addListener(gdir, "error", handleErrors);

        setDirections("73340-702", "70390-130", "pt_BR");
      }
    }
    
    function setDirections(fromAddress, toAddress, locale) {
      gdir.load("from: " + fromAddress + " to: " + toAddress,
                { "locale": locale });
        /*        
       alert('distância total da solicitação de direções (por todas as rotas): '+gdir.getDistance().html);
	   alert('tempo total da solicitação de direções (por todas as rotas): '+gdir.getDuration().html);
	   alert('número de entradas geocodificadas disponíveis no resultado: '+gdir.getNumGeocodes());
	   alert('Numeto de rotas: '+gdir.getNumRoutes());
	   */
	   var distancia = document.getElementById('distancia');
		        	distancia.value = gdir.getDistance().html;
		document.getElementById('distance').innerHTML = 'distancia: '+gdir.getDistance().html;
		
		return gdir.getDistance().html;
    }

    function handleErrors(){
	   if (gdir.getStatus().code == G_GEO_UNKNOWN_ADDRESS)
	     alert("No corresponding geographic location could be found for one of the specified addresses. This may be due to the fact that the address is relatively new, or it may be incorrect.\nError code: " + gdir.getStatus().code);
	   else if (gdir.getStatus().code == G_GEO_SERVER_ERROR)
	     alert("A geocoding or directions request could not be successfully processed, yet the exact reason for the failure is not known.\n Error code: " + gdir.getStatus().code);
	   
	   else if (gdir.getStatus().code == G_GEO_MISSING_QUERY)
	     alert("The HTTP q parameter was either missing or had no value. For geocoder requests, this means that an empty address was specified as input. For directions requests, this means that no query was specified in the input.\n Error code: " + gdir.getStatus().code);

	//   else if (gdir.getStatus().code == G_UNAVAILABLE_ADDRESS)  <--- Doc bug... this is either not defined, or Doc is wrong
	//     alert("The geocode for the given address or the route for the given directions query cannot be returned due to legal or contractual reasons.\n Error code: " + gdir.getStatus().code);
	     
	   else if (gdir.getStatus().code == G_GEO_BAD_KEY)
	     alert("The given key is either invalid or does not match the domain for which it was given. \n Error code: " + gdir.getStatus().code);

	   else if (gdir.getStatus().code == G_GEO_BAD_REQUEST)
	     alert("A directions request could not be successfully parsed.\n Error code: " + gdir.getStatus().code);
	    
	   else alert("An unknown error occurred.");
	   
	   
	   
	}

	function onGDirectionsLoad(){ 
      // Use this function to access information about the latest load()
      // results.

      // e.g.
      // document.getElementById("getStatus").innerHTML = gdir.getStatus().code;
	  // and yada yada yada...
	}
	

function loadMascara(){  	
	jQuery('#data').mask('99/99/99');
}