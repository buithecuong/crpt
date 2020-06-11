<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<style type="text/css">
body {
	padding-top: 1em;
	font-family: Georgia, "Times New Roman", Times, serif;
	color: purple;
	background-color: yellow;
}
</style>
</head>
<body>
	<p>Employee: ${employee}</p>
	<center><h2>EMPLOYEE DETAILS</h2></center>

	<table align="center" border="1">
		<tr>
			<td>Welcome ${firstname}</td>

		</tr>
		<tr>
			<td>ID : ${employee.id}</td>
		</tr>
		<tr>
			<td>Name : ${employee.name}</td>
		</tr>
		<tr>
			<td>User Name : ${employee.username}</td>
		</tr>
		<tr>
			<td>Team : ${employee.team}</td>
		</tr>
		<tr>
			<td>Status : ${employee.status}</td>
		</tr>

		<tr>
		</tr>
	</table>
	
<br><br><br>v
	<center>
		<a href="welcome">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="logout">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		
	</center>
</body>
</html>