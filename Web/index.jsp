<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Struts 2 - Exemplo</title>
</head>
<body>
	<s:form action="login!checkLogin">
	<s:textfield name="username" label="User"></s:textfield>
	<s:password name="password" label="Password"></s:password>
	<s:submit value="Sign In"></s:submit>
	</s:form>
</body>
</html>