<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
<div class="container">
  <div class="jumbotron">
    <h1>Dalton HR</h1>
    <img src="Images/CheezburgerHR_HDZ_Logo.png">
    <p></p> 
  </div>
</div>

	<form action="LoginServlet" method="post">
		Username: <input type="text" name="username"><br>
		Password: <input type="password" name="userpass"><br>
		<input type="submit" value="Login">
	</form>
	<i style="color:red"><c:out value="${loginError}" /></i>
	
</body>
</html>