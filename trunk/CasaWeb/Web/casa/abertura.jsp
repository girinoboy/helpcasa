<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:form>
<h2>Menu de Ações do Sistema Help Casa</h2><hr/>

<div id="wrap">
			
			<h1>Seja bem vindo,&nbsp;<c:out value="${pessoaSessao.perfil.descricao}"/>&nbsp;<c:out value="${pessoaSessao.nome}"/></h1>
			
			
			<div class="barra_botoes" align="left">				
				<input align="left" name="incluir" type="button" value="Logout"
				onClick="location.href='casa/login!logout.action?'" class="voltar">
			</div>
			
			<br>
			
		<!-- ajusta abertura conforme o perfil -->
			<s:if test="${pessoaSessao.perfil.descricao eq 'Cliente'}">
				<div id="nav">
					<a href="#" title="divname0">Perfil</a>
					<a href="#" title="divname7">Gerar Boletos</a>
					<a href="#" title="divname8">Solicitar Serviços</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>
					
	
					<div class="clear"></div>
				</div><!-- fim div nav -->
				
			</s:if>
			<s:elseif test="${pessoaSessao.perfil.descricao eq 'Profissional'}">
				<div id="nav">
					<a href="#" title="divname1">Agenda</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>
					
					
					<div class="clear"></div>
				</div><!-- fim div nav -->
				
			</s:elseif>
			<s:elseif test="${pessoaSessao.perfil.descricao eq 'Atendente'}">
				<div id="nav">
					<a href="#" title="divname2">Consultar Cliente</a>				
					<a href="#" title="divname7">Gerar Boletos</a>
					<a href="#" title="divname8">Solicitar Serviços</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>
											
				
					<div class="clear"></div>
				</div><!-- fim div nav -->
			
			</s:elseif>
			<s:elseif test="${pessoaSessao.perfil.descricao eq 'Administrador'}">
				<div id="nav">
					<a href="#" title="divname2">Consultar Cliente</a>
					<a href="#" title="divname3">Funcionários</a>
					<a href="#" title="divname4">Profissões</a>
					<a href="#" title="divname5">Serviços</a>
					<a href="#" title="divname6">Relatórios</a>
					<a href="#" title="divname7">Gerar Boletos</a>
					<a href="#" title="divname8">Solicitar Serviços</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>
				
					<div class="clear"></div>
				</div><!-- fim div nav -->
			</s:elseif>	
			<!-- 					
			<div id="nav">
				<a href="#" title="divname0">Perfil</a>
				<a href="#" title="divname1">Agenda</a>
				<a href="#" title="divname2">Consultar Cliente</a>
				<a href="#" title="divname3">Funcionario</a>
				<a href="#" title="divname4">Profissoes</a>
				<a href="#" title="divname5">Serviços</a>
				<a href="#" title="divname6">Relatórios</a>
				<a href="#" title="divname7">Gerar Boletos</a>
				<a href="#" title="divname8">Solicitar Serviços</a>
				<a href="#" title="divname9">Consultar Histórico de Serviços</a>
				<a href="#" title="divname">Como chegar</a>
				
				
				<div class="clear"></div>
			</div>--><!-- fim div nav -->
			 
			<div id="divname0" class="hiddencontent" >				
				<IFRAME name="palco" src='cliente!consultaParaCliente.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname1" class="hiddencontent">			
				<IFRAME name="palco" src='profissional!load.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>	
			</div>
			
			<div id="divname2" class="hiddencontent">			
				<IFRAME name="palco" src='cliente!pesquisar.action?funcao=cliente' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>				
			</div>
			
			<div id="divname3" class="hiddencontent">		
				<IFRAME name="palco" src='funcionario!load.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>		
			 
			<div id="divname4" class="hiddencontent">		
				<IFRAME name="palco" src='profissao!load.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname5" class="hiddencontent">				
				<IFRAME name="palco" src='servico!load.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname6" class="hiddencontent">				
				<IFRAME name="palco" src='relatorio!load.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname7" class="hiddencontent">				
				<IFRAME name="palco" src='cliente!boleto.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname8" class="hiddencontent">				
				<IFRAME name="palco" src='cliente!pesquisar.action?funcao=servico' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname9" class="hiddencontent">				
				<IFRAME name="palco" src='historico!load.action' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
			
			<div id="divname" class="hiddencontent">				
				<IFRAME name="palco" src='http://maps.google.com.br/' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</div>
								
</div><!-- fim div wrap -->
</s:form>