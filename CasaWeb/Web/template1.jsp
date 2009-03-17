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
	
	<head>
		
		<title><tiles:getAsString name="title" /></title>
		
		<!-- CARREGA AS BIBLIOTECAS DO PLUGIN jQuery SOMENTE SE FOR INFORMADO NO DEFINITION -->
		<s:if test="${not empty jQuery and jQuery == 'true'}">
		
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
		<s:if test="${not empty jtabber and jtabber == 'true'}">
			<script language="javascript" type="text/javascript" src='<s:url value="/js/jquery/jquery-1.2.6.min.js"/>'></script>
			<script language="javascript" src="/ProntuarioWeb/js/jquery/jtabber.js" type="text/javascript"></script>
			<script language="javascript" src="/ProntuarioWeb/js/jquery/jtabberConfig.js" type="text/javascript"></script>
			<link href='<s:url value="/estilos/jquery/jtabber.css"/>' rel="stylesheet" type="text/css"></link>
		</s:if>
		
		<s:if test="${not empty js}">
			<script type="text/javascript" src='<s:url value="${js}"/>' language="javascript"></script>
		</s:if>
		
		<!-- CARREGA AS BIBLIOTECAS DOJO SOMENTE SE FOR INFORMADO NO DEFINITION -->
		<s:if test="${not empty dojo and dojo == 'true'}">
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
