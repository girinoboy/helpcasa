<%@taglib prefix="s" uri="/struts-tags"%>
<table borde="1" bordecolor="whitc" cellpadding="2" cellspacing="2">
	<tr>
		<td><b>USUARIOS REGISTRADOS</b></td>
	</tr>
	<tr>
		<td style="width: 150px; text-align: center;">Name</td>
		<td style="width: 150px; text-align: center;">E-mail</td>
		<td style="width: 150px; text-align: center;">Birthday</td>
	</tr>
	
	<s:iterator>
		<tr>
			<td><s:property value="name"/></td>
			<td><s:property value="email"/></td>
			<td> <s:date name="birthday" format="dd/MM/yyyy"/></td>		
		</tr>
	</s:iterator>

</table>