<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Help Casa</title>
</head>
<body>
	<!-- <a href="./login!abertura.action">Logar</a><br/> -->
	<input type="button" value="Login" onclick='<c:url value="/login!abertura.action"/>'/><br/> 
	
	<a href="./cliente!load.action">Cadastro</a><br/>
	<b>--------------------------</b><br/>
	<b>--------------------------</b>
</body>
</html>