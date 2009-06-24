<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="width: 650px;" class="container">
	<s:form>
		<div class="body">
			<h1>
			Clique no relatório que deseja emitir e espere o sistema gera-lo
			</h1>
			<s:a href="relatorio!resumoFaturamentoMensal.action?" targets="_blank" onclick="location.href='relatorio!resumoFaturamentoMensal.action?'">Resumo de faturamento mensal</s:a><br><br>
			
			<s:a href="relatorio!resumoFaturamentoMensalPorProfissional.action?" targets="_blank" onclick="location.href='relatorio!resumoFaturamentoMensalPorProfissional.action?'">Resumo de faturamento mensal por profissional</s:a><br>
		</div>
	</s:form>
</div>