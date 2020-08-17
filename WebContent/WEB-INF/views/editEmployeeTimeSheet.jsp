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
<p>Employee TimeSheet: ${employeetimesheet}</p>
<center><h1>Edit Employee</h1></center>

<form:form method="POST" modelAttribute="employeetimesheet" action="updateEmployeeTimeSheet">
	<table align= "center">
		<tr>
			<td>SrNo :</td>
			<td><form:input path="srNo" /></td>
		</tr>
		<tr>
			<td>Job_Title :</td>
			<td><form:input path="jobTitle" /></td>
		</tr>
		<tr>
			<td>Hours :</td>
			<td><form:input path="hours" /></td>
		</tr>
		<tr>
			<td>Status :</td>
			<td><form:input path="status" /></td>
		</tr>
        <tr>
			<td>Date :</td>
			<td><form:input path="date" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
