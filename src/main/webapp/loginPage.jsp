<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file='Menu.jsp' %>
<form action="Auth" method="post">
user email: <input type="text" name="txtEmail"/><br>
password  : <input type="password" name="txtPwd"/><br>
<input type="submit" value="login"/>
</form>

</body>
</html>