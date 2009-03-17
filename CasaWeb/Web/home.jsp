
<%@ taglib prefix="s" uri="/struts-tags" %>    

<STYLE type="text/css">
.divs{
	border:1;
	background-color:buttonface;
	width:50%;
	padding:10;
	padding-bottom:30;
	padding-top:30;  
}
.table{
	text-align:center;
	background-color: buttonhighlight;
	border-color: buttonhighlight; 
}
.verm {
color: red;
padding-left: 400cm;
padding-bottom: 400em;
padding: 20px 400px 80px 5px;
border-color: green;
}
</STYLE>


		<h1> Struts 2 Usando Formulario</h1>
		<center>
			<div class="divs">
				<table>
					<tr>
					   <td><b>Registre-se no Formulário</b></td>
					</tr>
					<tr>
						<td>
						   <s:form action="user!insertUser" theme="ajax">
						     <s:textfield name="user.name" label="Username" size="40" cssClass="verm"></s:textfield>
						   	 <s:textfield name="user.email" label="E-mail" size="40"></s:textfield>
						   	 <s:password name="user.password" label="Password" size="40"></s:password>
						     <s:datetimepicker name="user.birthday" label="Birthday" theme="ajax" displayFormat="dd/MM/yyyy"> </s:datetimepicker>
						     <s:submit notifyTopics="listUsersTopic"></s:submit>
						   </s:form>
						</td>
					</tr>	
				</table>
		
			</div>
		<s:div id="userList" href="user!listAll.action" theme="ajax" cssClass="divs" ></s:div>
		
		<div class="divs">
			<s:submit href="user!clear.action" value="Clear" notifyTopics="listUsersTopic" theme="ajax" align="center" ></s:submit>
		</div>
		
		</center>
