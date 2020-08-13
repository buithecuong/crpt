<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	padding-top: 4em;
	font-family: Georgia, "Times New Roman", Times, serif;
	color: purple;
	background-color: yellow;
	
	}
table{
      border: solid 1px #000000;
      }
</style>
</head>
<body>
   
  <center><h1>List of Timesheet</h1></center>
       <table border=1 frame=void rules=rows class="table" style="width: 300px" align="center">
         <tr>
         <th>SrNo</th>
           <th>Job Title</th>
           <th>Hours</th>
           <th>Status</th>
           <th>Date</th>
           <th>Edit/Delete</th>
         </tr>
         <c:forEach items="${timesheetList}" var="record">
         <tr>
           <td width="60" align="center">${record.srNo}</td>
           <td width="60" align="center">${record.jobTitle}</td>
           <td width="60" align="center">${record.hours}</td>
           <td width="60" align="center">${record.status}</td>
           <td width="60" align="center">${record.date}</td>
           <td width="60" align="center"><a href="edit?id=${record.srNo}">Edit</a>/<a href="delete?id=${record.srNo}" onclick="return confirm('Do you really want to delete?')">Delete</a></td>
         </tr>
      </c:forEach>
    </table>
    <br>
       <br>   <br>   <br>   <br>   
    <center>
		<a href="welcomeAdmin">HOME </a>
	</center>
    
  </body>
</html>
