<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1> Exemplos MVC - JSP e Servlet</h1>
    <p> Hojé é     
        <%=new Date().toLocaleString() %>    
    </p>
    <hr>
    <h4>Exemplos</h4>
    <a href="formlogin.jsp">Exemplo 1 (Login)</a><br>
    <a href="simula.jsp">Exemplo 2 (Simulador Aplicação)</a><br>
    <a href="ControleBlog">Exemplo (MiniBlog)</a><br>
</body>
</html>