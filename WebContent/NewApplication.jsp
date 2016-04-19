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
		Name: <input type="text" name="name" value="<c:out value="${namdef}"/>">
			<i style="color:red"><c:out value="${namerror}" /></i>
			<br>
		Address: <input type="text" name="address" value="<c:out value="${adddef}"/>">
			<i style="color:red"><c:out value="${adderror}" /></i>
			<br>
		Date of Birth: <input type="text" name="birthday" value="<c:out value="${dobdef}"/>">
			<i style="color:red"><c:out value="${doberror}" /></i>
			<br>
		Education: <input type="text" name="education" value="<c:out value="${edudef}"/>">
			<i style="color:red"><c:out value="${eduerror}" /></i>
			<br>
		Job History: 
			<i style="color:red"><c:out value="${joberror}" /></i>
			<br>
			<textarea name="jobHistory" rows="4" cols="40"><c:out value="${jobdef}"/></textarea>
			<br>
		References: 
			<i style="color:red"><c:out value="${referror}" /></i>
			<br>
			<textarea name="references" rows="4" cols="40"><c:out value="${refdef}"/></textarea>
			<br>
		Drug Use: <input type="text" name="drugUse" value="<c:out value="${drudef}"/>">
			<i style="color:red"><c:out value="${druerror}" /></i>
			<br>
		Veteran Status: <input type="text" name="veteran" value="<c:out value="${vetdef}"/>">
			<i style="color:red"><c:out value="${veterror}" /></i>
			<br>
		Nationality: <input type="text" name="citizen" value="<c:out value="${natdef}"/>">
			<i style="color:red"><c:out value="${naterror}" /></i>
			<br>
		<input type="submit" value="Submit Application">
	</form>
	
	<a href="index.jsp">Return to login</a>
</body>
</html>