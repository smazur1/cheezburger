<!--Eric made this-->

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

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
				<img src="Images/CheezburgerHR_HDZ_Logo.png" height="30">
			</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="index.jsp">Home</a></li>
			<li><a href="Login.jsp">Employee Login</a></li>
			<li><a href="NewApplicationFormServlet">New Application</a></li>
		</ul>
	</div>
</nav>

<div class="container">
	<div class="page-header text-center">
		<h1>Dalton HR</h1>
		<img src="Images/CheezburgerHR_HDZ_Logo.png" height="100">
	</div>
	<div class="row">
		<div class="col-sm-6 text-center">
			<form action="Login.jsp" method="post">
				<input type="submit" value="HR Login">
			</form>
		</div>
		<div class="col-sm-6 text-center">
			<form action="NewApplicationFormServlet" method="post">
				<input type="submit" value="New Application">
			</form>
		</div>
	</div>
</div>
</body>
</html>