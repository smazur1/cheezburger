<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Applicant info</title>
</head>
<body>
<!-- this is page 2 -->
<p> User: <c:out value="  ${user.username}" />         HR Role: <c:out value="  ${user.chUserRoles[0].chHrrole.roleDescription}" /></p>
		
<table>
<tr>
<th> Activity</th>
<th> Activity Status</th>
<th> Action</th>
</tr>
<c:forEach items="${activitymap}" var="entry">
			<tr>
				<td><c:out value="  ${entry.key.chActivity.actdescription}" /></td>
				<td><c:out value="  ${entry.key.actstatus}" /></td>
				<td> 
				<c:if test="${entry.value==1}">
   <form action="ApplicantServlet" method="Post">
						<input type="submit" value="view">
						<input type="hidden" name="actid" value="${entry.key.chActivity.actid}">
					</form>
</c:if>
</td>
			</tr>
		</c:forEach>
		</table>
</body>
</html>