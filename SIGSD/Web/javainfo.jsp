<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="javax.servlet.jsp.JspFactory" %>
<%@ page import= "java.io.*"%>
<%@ page import= "javax.servlet.*"%>
<%@ page import= "javax.servlet.http.*"%>
<%@ page import= "java.util.*"%>
<body background="#CDCDB4">

<center><img src="http://www.locaweb.com.br/images/locaweb/pt_BR/logo_locaweb.jpg" width="193" height="84" border="0"></center>
<table border=1 cellpadding="0" cellspacing="0" bgcolor="#CDCDB4">
<td height="1" valign="top">
<%
TreeSet propKeys = new TreeSet(System.getProperties().keySet());
for (Iterator it = propKeys.iterator(); it.hasNext(); ) {
String key = (String)it.next();
String value = System.getProperty(key);
%>
<tr>
<td>

<i><%= key %>:</i><td><b><%= value %></b><br>
<%
}
%>
</table>

</body>
