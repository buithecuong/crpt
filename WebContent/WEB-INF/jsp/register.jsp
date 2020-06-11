<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style type="text/css">
body {
	padding-top: 8em;
	font-family: Georgia, "Times New Roman", Times, serif;
	color: purple;
	background-color: black;
}

.error {
	color: red;
}
</style>
</head>
<body>

	<div style="background-color: yellow"; width:200px; margin:auto;">
		<center>
			<h1>Employee Registration</h1>
		</center>
		<form:form id="regForm" modelAttribute="employee"
			action="registerProcess" method="post">

			<table align="center">

				<tr>

					<td><form:input type="hidden" path="id" name="id" id="id" /></td>
					<td><form:errors cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="name">Name</form:label></td>
					<td><form:input path="name" name="name" id="name" /></td>
					<td><form:errors cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="username">Username</form:label></td>
					<td><form:input path="username" name="username" id="username" />
					<td><form:errors cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" name="password"
							id="password" /></td>
					<td><form:errors cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="team">Team</form:label></td>
					<td><form:input path="team" name="team" id="team" /></td>
					<td><form:errors cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="status">Status</form:label></td>
					<td><input type="radio" name="status" value="admin">
						Admin</td>
					<td><input type="radio" name="status" value="user">
						User<br></td>




					<td><form:errors path="status" cssClass="error" /></td>
					<td style="font-style: italic; color: red;">${error}</td>
				</tr>
				<tr>
					<td></td>
					<td><form:button id="register" name="register">Register</form:button>
					</td>
				</tr>
				<tr>
					<td style="font-style: italic; color: red;">${message}</td>
				</tr>
				<tr>
					<td></td>
					<td><a href="welcome">Home</a></td>
				</tr>
			</table>


		</form:form>
	</div>


</body>
</html>
