<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<link href="<c:url value="/resources/css/login_style.css" />" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">

<div class="testbox">
  <h1>CRPT Login</h1>

  <form id="loginForm" modelAttribute="employee" action="loginProcess"
		method="post">
     
  <label id="icon" for="name"><i class="icon-user"></i></label>
  <input type="text"  name="username" id="username" placeholder="Username/Email" required/>
  <label id="icon" for="name"><i class="icon-shield"></i></label>
  <input type="password" name="password" id="password" placeholder="Password" required/>
  <div> 
   <label>
        <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
      </label>
   </div>
   
   <div> 
   <a href="register" class="button">Register</a>
   </div>
   <div> 
   <input type="submit" id="login" class="btn btn-primary btn-large btn-block" value="Login">
   </div>

   
  </form>
  
</div>

<script>

</script>

</body>
</html>