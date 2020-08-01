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
	var tableRowCount = null;

	function addRow(tableID) {

		var table = document.getElementById(tableID);

		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var cell1 = row.insertCell(0);
		var element1 = document.createElement("input");
		element1.type = "checkbox";
		element1.name = "chkbox[]";
		cell1.appendChild(element1);

		/*  var cell2 = row.insertCell(1);
		cell2.innerHTML = rowCount;  */

		var cell3 = row.insertCell(1);
		var element3 = document.createElement("input");
		element3.type = "text";
		element3.name = "txtbox[]";
		cell3.appendChild(element3);

		var cell4 = row.insertCell(2);
		var element4 = document.createElement("input");
		element4.type = "number";
		element4.name = "txtbox[]";
		cell4.appendChild(element4);

		var cell5 = row.insertCell(3);
		var element5 = document.createElement("input");
		element5.type = "text";
		element5.name = "txtbox[]";
		cell5.appendChild(element5);
		
		var cell6 = row.insertCell(4);
		
		var element6 = document.createElement("input");
		element6.type = "text";
		element6.name = "txtbox[]";
		cell6.appendChild(element6);

		tableRowCount = table.rows.length

	}

	function deleteRow(tableID) {
		try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;

			for (var i = 0; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					table.deleteRow(i);
					rowCount--;
					i--;
				}
			}
			tableRowCount = table.rows.length

		} catch (e) {
			alert(e);
		}
	}
	
	 function timeSheetRecords(tableID) {
		var listOfObjects = [];
		var listOfColumns = ['id', 'title', 'hrs', 'status', 'date'];
		
		 var table = document.getElementById(tableID);
		 alert ("this alert is working before for loop ")
		 
		for (var i = 1, row ; i < table.rows.length; i++) {
			
			var objectDict = {};
			
			alert ("Alert for row: " + i)
		   //iterate through rows
		   //rows would be accessed using the "row" variable assigned in the for loop
		     // GET THE CELLS COLLECTION OF THE CURRENT ROW.
            var objCells = table.rows.item(i).cells;

            // LOOP THROUGH EACH CELL OF THE CURENT ROW TO READ CELL VALUES.
            for (var j = 0; j < objCells.length; j++) {
            	
            	var textVal = objCells.item(j).children[0].value
            	alert ('Column ' + j + '(' + textVal + ')' )
            	objectDict[listOfColumns[j]] = textVal;
            }
            console.log('Dict: ', objectDict); 
            
			 
		    
		   
            alert ("this alert is before add list")
		   listOfObjects.push(objectDict)   ;
		   } 
		   
		   alert ("this alert is working")
		   console.log('Dict: ',String(listOfObjects)); 
		   alert ("this alert is working")
		   
		return listOfObjects;

	} 
	 
	 
	 
</SCRIPT>
<body>


	<%
		out.println("The timesheet for the day " + request.getParameter("day"));
	%><br />

	<form:form name="regForm"
		onSubmit=" return timeSheetRecords('TimeSheet')" method="post"
		action="save" modelAttribute="timeSheetForm">

		<input type="button" value="Add Row" onclick="addRow('dataTable')" />

		<input type="button" value="Delete Row"
			onclick="deleteRow('TimeSheet')" />

		<table id="TimeSheet" width="350px" border="1">
			<tr>
				<th></th>
				<th>Job_Title</th>
				<th>Hours</th>
				<th>Status</th>
				<th>Date</th>

			</tr>
			<tr>
				<td><input type="checkbox" name="chk" /></td>
				<td><input type="text" name="jobTitle" id="jobTitle" /></td>
				<td><input type="number" name="hours" id="hours" /></td>
				<td><input type="text" name="status" id="status" /></td>
				<td><input type="text" name="dateCreated" id="dateCreated"
					value="<%=request.getParameter("day")%>" /></td>
			</tr>

		</table>
		<br />
		<input type="submit" value="Save" />
	</form:form>







</body>
</html>