<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Offer Letter</title>
</head>
<body>
	<a>Offer letter has been sent to <c:out value="${application.name}" /></a><br>
	<img src="Images/happy_cat.png" alt="Congratulations!" height="300">

	<form action="ApplicationListServlet" method="post">
		<input type="submit" value="Return to application list">
	</form>
	
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>