<!--Eric made this-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Application</title>
</head>
<body>
	<form action="NewApplicationServlet" method="post">
		Applying for: <select name="jobType">
			<c:forEach items="${jobList}" var="job">
				<option value="${job.jobId}"><c:out value="${job.jobDescription}" /></option>
			</c:forEach>
		</select><br>
		Name: <input type="text" name="name"><br>
		Address: <input type="text" name="address"><br>
		Date of Birth: <input type="text" name="birthday"><br>
		Education: <input type="text" name="education"><br>
		Job History: <br><textarea name="jobHistory" rows="4" cols="40"></textarea><br>
		References: <br><textarea name="references" rows="4" cols="40"></textarea><br>
		Drug Use: <input type="text" name="drugUse"><br>
		Veteran Status: <input type="text" name="veteran"><br>
		Nationality: <input type="text" name="citizen"><br>
		<input type="submit" value="Submit Application">
	</form>
	
	<a href="index.jsp">Return to login</a>
</body>
</html>