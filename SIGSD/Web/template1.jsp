<%@ page pageEncoding="ISO-8859-1" contentType="text/html"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	
	<tiles:useAttribute name="body" scope="request"  ignore="true"/>
	<tiles:useAttribute name="onload" scope="request" ignore="true"/>
	<tiles:useAttribute name="js" scope="request" ignore="true"/>
	<tiles:useAttribute name="filtro" scope="request" ignore="true"/>
	
	<tiles:useAttribute name="jQuery" scope="request" ignore="true"/>
	<tiles:useAttribute name="jQueryMultipleFileUpload" scope="request" ignore="true"/>
	<tiles:useAttribute name="dojo" scope="request" ignore="true"/>
	<tiles:useAttribute name="jtabber" scope="request" ignore="true"/>
	<tiles:useAttribute name="gMaps" scope="request" ignore="true"/>
	
	<head>
		
		<title><tiles:getAsString name="title" /></title>
		
		<!-- api google maps -->
		<s:if test="${gMaps}">
		<!-- http://192.168.200.200/ 
		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA3MlzePjziJ1uP_37GBnQLBRO1SyGfQJyvPruXKjzCya-btAMYRRkga573edB1p2CDrqaqbKuFbecuw" type="text/javascript"></script>
		-->
		<!-- http://201.34.200.102/  
		 <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAA3MlzePjziJ1uP_37GBnQLBQEoy_OnvSicNPc4JVsc2xGAI7j-xSiLW3fOEp1m-Ru5XUbjCPJS6y5Jg" type="text/javascript"></script>
		-->
		<!-- http://201.34.200.102/SIGSD/
		 <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA3MlzePjziJ1uP_37GBnQLBR6Sx1U0p5dsF9UQa_9gAzptSVQ3RRIZK60IizTpOEDRbPXh6dM7Mi_lw" type="text/javascript"></script>
		 -->
		<!-- sigsd.com.br -->
		 <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA3MlzePjziJ1uP_37GBnQLBTY74gxr_pUnlzD-Q8T61LcYQfMUBQWsZou4hwnuwP8uzYExYAN56gy8Q" type="text/javascript"></script>
		 
		<!-- localhost 
		<script src="http://maps.google.com/?file=api&amp;v=2.x&amp;sensor=trues&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA" type="text/javascript"></script>
		-->
		</s:if>
		
		<!-- css -->
		<link href='<s:url value="/estilos/casa_web.css"/>' rel="stylesheet" type="text/css"></link>
				
		<!-- CARREGA AS BIBLIOTECAS DO PLUGIN jQuery SOMENTE SE FOR INFORMADO NO DEFINITION -->
		<s:if test="${jQuery}">
		
			<script language="javascript" type="text/javascript" src='<s:url value="/js/jquery/jquery-1.2.6.js"/>'></script>
			
			<!-- Para retirar os conflitos que poderam ocorrer com outros frameWorks que utilizam $ -->
			<script type="text/javascript" language="javascript">
				jQuery.noConflict();
			</script>	
			
			<script language="javascript" src='<s:url value="/js/jquery/jquery.maskedinput-1.1.4.js"/>'></script>
			<script language="javascript" src='<s:url value="/js/jquery/jquery.alphanumeric.js"/>'></script>
			<script language="javascript" src='<s:url value="/js/jquery/jquery.jHelperTip.1.0.js"/>'></script>				
			<script language="javascript" src='<s:url value="/js/jquery/jquery.numeric.js"/>'></script>
			<script language="javascript" src='<s:url value="/js/jqueryutil/jQueryUtil.js"/>'></script>
		</s:if>
		
		<!-- CARREGA AS BIBLIOTECAS DO PLUGIN JQUERY MULTIPLE-FILE-UPLOAD SOMENTE SE FOR INFORMADO NO DEFINITION -->
		<s:if test="${not empty jQueryMultipleFileUpload and jQueryMultipleFileUpload == 'true'}">
			<script language="javascript" src='<s:url value="/js/jquery/multiplefileupload/jquery.MultiFile.js"/>'></script>
			<link href='<s:url value="/estilos/jquery/multiplefileupload.css"/>' rel="stylesheet" type="text/css"></link>
		</s:if>
		
		<!-- CARREGA AS BIBLIOTECAS DO PLUGIN jtabber -->
		<s:if test="${jtabber}">
			<script language="javascript" type="text/javascript" src='<s:url value="/js/jquery/jquery-1.2.6.min.js"/>'></script>
			<script language="javascript" type="text/javascript" src='<s:url value="/js/jquery/jtabber.js"/>'></script>
			<script language="javascript" type="text/javascript" src='<s:url value="/js/jquery/jtabberConfig.js"/>'></script>
			<link href='<s:url value="/estilos/jquery/jtabber.css"/>' rel="stylesheet" type="text/css"></link>
		</s:if>		
		
		<s:if test="${not empty js}">
			<script type="text/javascript" src='<s:url value="${js}"/>' language="javascript"></script>
		</s:if>
		
		<!-- CARREGA AS BIBLIOTECAS DOJO SOMENTE SE FOR INFORMADO NO DEFINITION -->
		<s:if test="${dojo}">
			<s:head theme="ajax" debug="true"/>	
		</s:if>
		
	</head>
	
	<s:if test="${not empty onload}">
		<body onload="${onload}">
	</s:if>
	<s:else>
		<body>
	</s:else>
		<s:if test="${not empty filtro}">
			<tiles:insertAttribute name="filtro" />
		</s:if>
		<tiles:insertAttribute name="body" />		
	</body>

		<!-- MOSTRA MENSAGENS SE EXISTIR -->
		<c:if test="${not empty mensagemGlobal.mensagens}">
			<script type="text/javascript" language="javascript">
				var varMensagem = '';
			</script>
			<c:forEach var="mensagem" items="${mensagemGlobal.mensagens}">
				<s:set value="getText('${mensagem.mensagem}')" name="mens" scope="page"/>
				
					<s:if test="${mens == ''}">
						<script type="text/javascript" language="javascript">
							varMensagem += '${fn:replace(mens, "[n]", "\\n")}';
							varMensagem += '\n';
						</script>
					</s:if>
					<s:else>
						<script type="text/javascript" language="javascript">
							varMensagem += '${fn:replace(mensagem.mensagem, "[n]", "\\n")}';
							varMensagem += '\n';
						</script>
					</s:else>
				
			</c:forEach>
			
			<script type="text/javascript" language="javascript">
				alert(varMensagem);
			</script>
			<c:remove var="mensagem" scope="session"/>
			<c:remove var="mensagem" scope="request"/>
		</c:if>

</html>
