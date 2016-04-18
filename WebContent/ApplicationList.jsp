<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Back</title>
</head>
<body>
<!--  this is page 1 -->
<!-- insert list of applicants here and show their app status -->
<p> User: <c:out value="  ${user.username}" />         HR Role: <c:out value="  ${user.chUserRoles[0].chHrrole.roleDescription}" /></p>
<table>
<tr>
<th> Application ID</th>
<th> Applicant Name</th>
<th> Application Status</th>
<th> Action</th>
</tr>
<c:forEach items="${applicationList}" var="applicant">
			<tr>
				<td><c:out value="  ${applicant.appid}" /></td>
				<td><c:out value="  ${applicant.name}" /></td>
				<td><c:out value="  ${applicant.appstatus}" /></td>
				<td> <form action="SessionApplicationServlet" method="Post">
						<input type="submit" value="view">
						<input type="hidden" name="appid" value="${applicant.appid}">
					</form></td>
			</tr>
		</c:forEach>
		</table>
<form action="LogoutServlet" method="post">
	<input type="submit" value="Logout">
</form>
		
</body>
</html>