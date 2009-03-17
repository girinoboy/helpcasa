<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<STYLE type="text/css">
.diives{
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
</STYLE>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Struts 2 Examplo</title>
</head>
<body>
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
						     <s:textfield name="user.name" label="Username" size="40"></s:textfield>
						   	 <s:textfield name="user.email" label="E-mail" size="40"></s:textfield>
						   	 <s:password name="user.password" label="Password" size="40"></s:password>
						     
						    
						   </s:form>
						</td>
					</tr>	
				</table>
		
			</div>
		<s:div id="userList" href="user!ListAll.action" thema="ajax" cssClass="divs" ></s:div>
		
		<div class="divs">
			<s:submit href="user!clear.action" value="Clear" notifyTopics="listUsersTopic" theme="ajax" align="center" ></s:submit>
		</div>
		
		</center>
</body>
</html>