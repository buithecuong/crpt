<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	margin: 8px 0;
	padding: 12px 20px;
	display: inline-block;
	border: 2px solid green;
	box-sizing: border-box;
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


		var tableRowCount=null;


		function addRow(tableID) {

			var table = document.getElementById(tableID);

			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);

			var cell1 = row.insertCell(0);
			var element1 = document.createElement("input");
			element1.type = "checkbox";
			element1.name="chkbox[]";
			cell1.appendChild(element1);
			
			var cell2 = row.insertCell(1);
			cell2.innerHTML = rowCount ;

			var cell3 = row.insertCell(2);
			var element3 = document.createElement("input");
			element3.type = "text";
			element3.name = "txtbox[]";
			cell3.appendChild(element3);

			var cell4 = row.insertCell(3);
			var element4 = document.createElement("input");
			element4.type = "text";
			element4.name = "txtbox[]";
			cell4.appendChild(element4);
			
			var cell5 = row.insertCell(4);
			var element5 = document.createElement("input");
			element5.type = "text";
			element5.name = "txtbox[]";
			cell5.appendChild(element5);
			
			tableRowCount = table.rows.length
			

		}

		function deleteRow(tableID) {
			try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;

			for(var i=0; i<rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if(null != chkbox && true == chkbox.checked) {
					table.deleteRow(i);
					rowCount--;
					i--;
				}

				tableRowCount = table.rows.length
			}
			}catch(e) {
				alert(e);
			}
		}

	</SCRIPT>
<body>

	<form action="timesheetdao.jsp">

		<%   
	 out.println("The timesheet for the day " + request.getParameter("day"));
	 
	 
	 %><br />
		<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />

		<INPUT type="button" value="Delete Row"
			onclick="deleteRow('dataTable')" />

		


		<TABLE id="dataTable" width="350px" border="1">
			<tr>
				<th></th>
				<th>Sr_No</th>
				<th>Job_Title</th>
				<th>Hours</th>
				<th>Status</th>
			</tr>
			<TR>
				<TD><INPUT type="checkbox" name="chk" /></TD>
				<TD><INPUT name="Sr_No"></TD>
				<TD><INPUT name="Job_Title"></TD>
				<TD><INPUT name="Hours"></TD>
				<TD><INPUT name="Status"></TD>


			</TR>


		</TABLE>
		
		<%   
	 out.println("The timesheet for the day " + request.getParameter("day"));
	 request.setAttribute("RowCount", request.getParameter("tableRowCount"));
	 
	 %>
		
		<input type="submit" value="Send"  />

	</form>

</body>
</html>