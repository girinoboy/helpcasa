<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<s:form>
<h2>Menu de Ações do Sistema SIGSD</h2><hr/>
<s:head theme="ajax"/>
<s:div refreshOnShow="true"  id="wrap">
			
			<h1>Seja bem vindo,&nbsp;<c:out value="${pessoaSessao.perfil.descricao}"/>&nbsp;<c:out value="${pessoaSessao.nome}"/></h1>
			
			
			<div class="barra_botoes" align="left">				
				<input align="left" name="incluir" type="button" value="Logout"
				onClick="location.href='login!logout.action?'" class="voltar">
			</div>
			
			<br>
			
		<!-- ajusta abertura conforme o perfil -->
			<s:if test="${pessoaSessao.perfil.descricao eq 'Cliente'}">
				<s:div refreshOnShow="true"  id="nav">
					<a href="#" title="divname0" name="palco1">Perfil</a>
					<a href="#" title="divname7">Gerar Boletos</a>
					<a href="#" title="divname8">Solicitar Serviços</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>					
	
					<s:div refreshOnShow="true"  cssClass="clear"></s:div>
				</s:div><!-- fim div nav -->																	
				<!--<s:div id="divname0" href='cliente!consultaParaCliente.action' cssClass="hiddencontent" theme="ajax"></s:div>-->				
				<s:div refreshOnShow="true"  id="divname0" cssClass="hiddencontent" >				
					<IFRAME id="palco" name="palco" src='cliente!consultaParaCliente.action' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
				<s:div refreshOnShow="true"  id="divname7" cssClass="hiddencontent">				
					<IFRAME name="palco" src='solicitacao!consultarFaturaBasica.action?solicitacaoDTO.cliente.cpf=${pessoaSessao.cpf}' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
				<s:div refreshOnShow="true"  id="divname8" cssClass="hiddencontent" theme="ajax">				
					<IFRAME name="palco" src='solicitacao!load.action?solicitacaoDTO.cliente.cpf=${pessoaSessao.cpf}' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
				<s:div refreshOnShow="true"  id="divname9" cssClass="hiddencontent" theme="ajax">				
					<IFRAME id="divname9" name="divname9" src='historico!historicoListar.action?historicoDTO.solicitacao.cliente.cpf=${pessoaSessao.cpf}' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
			</s:if>
				
			<s:elseif test="${pessoaSessao.perfil.descricao eq 'Profissional'}">
				<s:div refreshOnShow="true"  id="nav">
					<a href="#" title="divname1">Agenda</a>
									
					<s:div refreshOnShow="true"  cssClass="clear"></s:div>
				</s:div><!-- fim div nav -->
				
				<s:div refreshOnShow="true"  id="divname1" cssClass="hiddencontent">			
					<IFRAME name="palco" src='profissional!load.action?profissionalDTO.id=${pessoaSessao.id}' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>	
				</s:div>
			</s:elseif>
			<s:elseif test="${pessoaSessao.perfil.descricao eq 'Atendente'}">
				<s:div refreshOnShow="true"  id="nav">
					<a href="#" title="divname2">Consultar Cliente</a>				
					<a href="#" title="divname7">Gerar Boletos</a>
					<a href="#" title="divname8">Solicitar Serviços</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>
											
				
					<s:div refreshOnShow="true"  cssClass="clear"></s:div>
				</s:div><!-- fim div nav -->
			
			</s:elseif>
			<s:elseif test="${pessoaSessao.perfil.descricao eq 'Administrador'}">
				<s:div refreshOnShow="true"  id="nav">
					<a href="#" title="divname2">Consultar Cliente</a>
					<a href="#" title="divname3">Funcionários</a>
					<a href="#" title="divname4">Profissões</a>
					<a href="#" title="divname5">Serviços</a>
					<a href="#" title="divname6">Relatórios</a>
					<a href="#" title="divname7">Gerar Boletos</a>
					<a href="#" title="divname8">Solicitar Serviços</a>
					<a href="#" title="divname9">Consultar Histórico de Serviços</a>
					
					<s:div refreshOnShow="true"  cssClass="clear"></s:div>
				</s:div>	<!-- fim div nav -->
				
				<s:div refreshOnShow="true"  id="divname3" cssClass="hiddencontent">		
					<IFRAME name="palco" src='funcionario!load.action' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>		
			 
				<s:div refreshOnShow="true"  id="divname4" cssClass="hiddencontent">		
					<IFRAME name="palco" src='profissao!load.action' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
			
				<s:div refreshOnShow="true"  id="divname5" cssClass="hiddencontent">				
					<IFRAME name="palco" src='servico!servicosListar.action' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
			
				<s:div id="divname6" cssClass="hiddencontent" refreshOnShow="true">				
					<IFRAME name="palco" src='relatorio!load.action' frameBorder="0" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
			</s:elseif>
			<s:else>
				<s:div refreshOnShow="true"  id="nav"><a href="#" title="erro">Não logado</a><s:div refreshOnShow="true"  cssClass="clear"></s:div>
				</s:div>
			</s:else>
			<!--	
			######################################################################################################
			 
			-->
			<s:div refreshOnShow="true"  id="erro" cssClass="hiddencontent" ></s:div>
			<!--
			<s:div id="divname0" href='cliente!consultaParaCliente.action' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname1" href='profissional!load.action' cssClass="hiddencontent" theme="ajax"></s:div>
		 	
			<s:div id="divname2" href='cliente!pesquisar.action?funcao=cliente' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname3" href='funcionario!load.action' cssClass="hiddencontent" theme="ajax"></s:div>		
			 
			<s:div id="divname4" href='profissao!load.action' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname5" href='servico!servicosListar.action' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname6" href='relatorio!load.action' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname7" href='solicitacao!consultarFaturaBasica.action' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname8" href='cliente!pesquisar.action?funcao=servico' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname9" href='historico!historicoListar.action' cssClass="hiddencontent" theme="ajax"></s:div>
			
			<s:div id="divname" href='http://maps.google.com.br/' cssClass="hiddencontent" theme="ajax"></s:div>
		
			<s:div id="google" href='solicitacao!google.action?' cssClass="hiddencontent" theme="ajax"></s:div>
		-->		
			
			<s:div refreshOnShow="true"  id="divname2" cssClass="hiddencontent">			
				<IFRAME name="palco" src='cliente!pesquisar.action?funcao=cliente&clienteDTO.perfil.id=${pessoaSessao.perfil.id}' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
				<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>				
			</s:div>						
			
			
			<s:if test="${pessoaSessao.perfil.descricao eq 'Administrador' or pessoaSessao.perfil.descricao eq 'Atendente'}">
				<s:div refreshOnShow="true"  id="divname7" cssClass="hiddencontent">				
					<IFRAME name="palco" src='cliente!pesquisar.action?funcao=boleto&clienteDTO.perfil.id=${pessoaSessao.perfil.id}' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
				<s:div refreshOnShow="true"  id="divname8" cssClass="hiddencontent" theme="ajax">				
					<IFRAME name="palco" src='cliente!pesquisar.action?funcao=servico&clienteDTO.perfil.id=${pessoaSessao.perfil.id}' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
				</s:div>
				<s:div refreshOnShow="true"  id="divname9" cssClass="hiddencontent" theme="ajax">				
				<IFRAME name="divname9" id="divname9" src='cliente!pesquisar.action?funcao=historico&clienteDTO.perfil.id=${pessoaSessao.perfil.id}' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
				<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			</s:div>
			</s:if>
			
			
			<!--
			<s:div refreshOnShow="true"  id="divname" cssClass="hiddencontent">				
				<IFRAME name="palco" src='http://maps.google.com.br/' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</s:div>
				
			<s:div refreshOnShow="true"  id="google" cssClass="hiddencontent">				
				<IFRAME name="palco" src='solicitacao!google.action?' frameBorder="no" width="100%" height="100%" scrolling="auto" allowtransparency="true"></IFRAME>
			</s:div>
				-->			
</s:div><!-- fim div wrap -->
</s:form>