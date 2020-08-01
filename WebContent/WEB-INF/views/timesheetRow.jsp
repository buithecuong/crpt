<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
Body {
	font-family: Calibri, Helvetica, sans-serif;
	background-color: white;
}

button {
	background-color: #4CAF50;
	width: 100%;
	color: orange;
	padding: 15px;
	margin: 10px 0px;
	border: none;
	cursor: pointer;
}

form {
	border: 3px solid #f1f1f1;
}

input[type=text] {
	width: 100%;
	display: inline-block;
	box-sizing: border-box;
}

td {
	margin: 8px 0;
}

button:hover {
	opacity: 0.7;
}

.container {
	padding: 25px;
	background-color: lightblue;
}

table {
	border: 1px solid black;
}
</style>
<SCRIPT language="javascript">
		 
	 
	 
</SCRIPT>
<body>


	<%
		out.println("The timesheet for the day " + request.getParameter("day"));
	%><br />

	<form:form name="regForm"  method="post"
		action="addTimesheetRow" modelAttribute="timesheet">

		
		<table id="TimeSheet" width="350px" border="1">
			<tr>
				<th>Job_Title</th>
				<th>Hours</th>
				<th>Status</th>
				<th>Date</th>

			</tr>
			<tr>
				<td><input type="text" name="jobTitle" id="jobTitle" /></td>
				<td><input type="number" name="hours" id="hours" /></td>
				<td><input type="text" name="status" id="status" /></td>
				<td><input type="text" name="date" id="date"
					value="<%=request.getParameter("day")%>" /></td>
			</tr>

		</table>
		<br />
		<input type="submit" value="addTimesheetRow" />
	</form:form>







</body>
</html>