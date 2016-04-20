<!--Eric made this-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		Username: <input type="text" name="username"><br>
		Password: <input type="password" name="userpass"><br>
		<input type="submit" value="Login">
	</form>
	<i style="color:red"><c:out value="${loginError}" /></i>
	
	<form action="NewApplicationFormServlet" method="post">
		<input type="submit" value="New Application">
	</form>
</body>
</html>