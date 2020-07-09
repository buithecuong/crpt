<%@page import="org.TestDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
        int Sr_No = Integer.valueOf(request.getParameter("Sr_No"));
		String jobTitle = request.getParameter("Job_Title");
		int hours = Integer.valueOf(request.getParameter("Hours"));
		String status = request.getParameter("Status");
		  request.getAttribute("tableRowCount").toString();
		 
		System.out.println("Number of rows " + request.getAttribute("tableRowCount").toString());
		// Using the TestDao Class 
		//String results = TestDao.login(email, password);
		Boolean results = TestDao.inserttimesheet(Sr_No,jobTitle,hours,status);
		if (results) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>