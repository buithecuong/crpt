<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<style type="text/css">
body {
	
	font-family: Georgia, "Times New Roman", Times, serif;
	color: black;
	background-color: yellow;
	font-size: 50 ;
	}
	
	 a:link {
    color: red ;
    size: 30}
  a:visited {
    color: green;
    size: 30 }
</style>
</head>
<body>
<p>Employee: ${employee}</p>  
<center><h1>ADMIN HERE</h1></center> 
	


	<table align ="center">
		<tr>
			<td>Welcome ${firstname}</td>
			
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
			<td><a href="welcome">Home</a></td>
			<td><a href="list">List OF Employees</a></td>
		
		</tr>
	</table>
	<a href="logout">Logout</a>
</body>
</html>