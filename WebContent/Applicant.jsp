<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="table.css">
<title>Applicant info</title>
</head>
<body>
<!-- this is page 2 -->
<p> User: <c:out value="  ${user.username}" />         HR Role: <c:out value="  ${user.chUserRoles[0].chHrrole.roleDescription}" /></p>
<br>
<h4>Application ID: <c:out value="${application.appid}" /></h4>
<br>
<table>
<tr>
<th> Activity</th>
<th> Activity Status</th>
<th> Action</th>
</tr>
<c:forEach items="${activitymap}" var="entry">
			<tr>
				<td><c:out value="  ${entry.key.chActivity.actdescription}" /></td>
				<td>
				<c:choose>
						<c:when test="${entry.key.actstatus ==  \"P\"}">
							<img src="Images/burger.png" alt="Pass" height="40">
						</c:when>
						
						<c:when test="${entry.key.actstatus ==  \"F\"}">
							<img src="Images/sad_cat.png" alt="Fail" height="40">
						</c:when>
						
						<c:otherwise>
							<img src="Images/cat.png" alt="In Process" height="40">
						</c:otherwise>
					</c:choose>
				</td>
				<td> 
				<c:if test="${entry.value==1}">
   <form action="ApplicantServlet" method="Post">
						<input type="submit" value="update">
						<input type="hidden" name="actid" value="${entry.key.chActivity.actid}">
					</form>
</c:if>
</td>
			</tr>
		</c:forEach>
		</table>
		
<form action="ApplicationListServlet" method="post">
		<input type="submit" value="Return to application list">
	</form>	
	
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>	
</body>
</html>