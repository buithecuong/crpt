<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style type="text/css">
body {
	padding-top: 4em;
	font-family: Georgia, "Times New Roman", Times, serif;
	color: purple;
	background-color: yellow;
	
	}
	div, p, th, td
{
    font-size: 14px;
}
table{
      border: solid 1px #000000;
       border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
      }
      
th
{
    background-color: blue;
    color: white;
    padding: 10px;
}

td
{
    padding: 5px;
}
</style>
</head>
<body>
   
  <center><h1>TIMESHEET</h1></center>
       <table border=1 frame=void rules=rows class="table" style="width: 300px" align="center" >
         <tr>
           
           <th>  Id  </th>
           <th>  jobTitle  </th>
           <th>  hours  </th>
            <th>  statusCheck  </th>
            <th>  date  </th>
           <th>  Edit/Delete</th>
         </tr>
         <c:forEach items="${timesheetList}" var="timesheet">
         <tr>
           <td width="60" align="center">${timesheet.id}</td>
           <td width="60" align="center">${timesheet.jobTitle}</td>
           <td width="60" align="center">${timesheet.hours}</td>
           <td width="60" align="center">${timesheet.statusCheck}</td>
           <td width="60" align="center">${timesheet.datee}</td>
           <td width="60" align="center"><a href="edit?id=${timesheet.id}">Edit</a>/<a href="delete?id=${timesheet.id}" onclick="return confirm('Do you really want to delete?')">Delete</a></td>
         </tr>
      </c:forEach>
    </table>
    <br>
       <br>   <br>   <br>   <br>   
    <center>
		<a href="home">BACK </a>
	</center>
    



</body>
</html>