<!--Eric made this-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<style> 
div1 {
    width: 200px;
    height: 100px;
    background-color: white;
    position: relative;
    animation-name: welcome;
    animation-duration: 4s;
    animation-iteration-count: infinite;
}
@keyframes welcome {
    0%   {left:0px; }
    25%  {left:500px;}
    50%  {left:1000px;}
    75%  {left:500px; }
    100% {left:0px; }
}
h5 {
    text-decoration: underline;
}
</style>
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
			<c:choose>
					<c:when test="${loggedin == \"yes\"}">
						<li><a href="LogoutServlet">Logout</a></li>
						<li><a href="ApplicationListServlet">Application List</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="Login.jsp">Employee Login</a></li>
					</c:otherwise>
				</c:choose>
			<li><a href="NewApplicationFormServlet">New Application</a></li>
		</ul>
	</div>
</nav>

<div class="container">
	<div class="page-header text-center">
		<img src="Images/CheezburgerHR_HDZ_Logo.png" height="100">
	</div>
	<div>
		<br>
		<h3>Welcome to Dalton HR!</h3>
		<br>
	<div1>	
		<h5>Please use the navigation bar to select your desired task.</h5>
	</div1>
	</div>	
</div>
</body>
</html>