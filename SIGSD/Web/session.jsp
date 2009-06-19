<html>
<head>
<title>Teste de Sessao</title>
</head>

<body>
<h1>Teste de Sessao</h1>
 == Propriedades ==

<br>Criada ==> <%= new java.util.Date(session.getCreationTime()) %>
<br>Ultimo Acesso: ==> <%=new java.util.Date(session.getLastAccessedTime()) %>
<br>ID da sessao ==> <%= session.getId() %>

</body>
</html>
