<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
body {
	padding-top: 4em;
	font-family: Georgia, "Times New Roman", Times, serif;
	color: purple;
	background-color: yellow;
	}
</style>
<p>Employee Objective: ${employeeobjective}</p>
<center><h1>Edit Objective</h1></center>

<form:form method="POST" modelAttribute="employeeobjective" action="updateEmployeeObj">
	<table align= "center">
		<tr>
			<td>srNo :</td>
			<td><form:input path="srNo" /></td>
		</tr>
		<tr>
			<td>Name :</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td>Duration :</td>
			<td><form:input path="duration" /></td>
		</tr>
		<tr>
			<td>Type :</td>
			<td><form:input path="type" /></td>
		</tr>
		<tr>
			<td>Description :</td>
			<td><form:input path="description" /></td>
		</tr>
		<tr>
			<td>Status :</td>
			<td><form:input path="status" /></td>
		</tr>
		<tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
