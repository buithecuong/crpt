<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<link href="<c:url value="/resources/css/register_style.css" />" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">

<div class="testbox">
  <h1>CRPT Registration</h1>

  <form modelAttribute="employee"
			action="registerProcess" onSubmit=" return formValidation();" method="post">
      <hr>
    <div class="team">
      <input type="radio" value="HR" id="radioOne" name="team" />
      <label for="radioOne" class="radio" chec>HR</label>
      <input type="radio" value="Engineer" id="radioTwo" name="team" checked/>
      <label for="radioTwo" class="radio">Engineer</label>
    </div>
  <hr>
  <label id="icon" for="name"><i class="icon-user"></i></label>
  <input type="text" name="name" id="name" placeholder="Name" required/>
  <label id="icon" for="username"><i class="icon-user"></i></label>
  <input type="text" name="username" id="name" placeholder="Username" required/>
  <label id="icon" for="email"><i class="icon-envelope "></i></label>
  <input type="text" name="email" id="email" placeholder="Email" required/>
  <div>
  <label id="icon" for="password"><i class="icon-shield"></i></label>
  <input type="password" name="password" id="password" placeholder="Password" required/>
  </div>
  <div class="role">
    <input type="radio" value="admin" id="admin" name="status" checked/>
    <label for="admin" class="radio" chec>Admin</label>
    <input type="radio" value="user" id="user" name="status" />
    <label for="user" class="radio">User</label>
    <input type="radio" value="manager" id="manager" name="status" />
    <label for="manager" class="radio">Manager</label>
   </div> 
   <p>By clicking Register, you agree on our <a href="#">terms and condition</a>.</p>
   <label>
        <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
      </label>
<div>    
<a href="login" class="button">Login</a>
</div>
   <div> 
   <input type="submit" id="register" name="register" class="btn btn-primary btn-large btn-block" value="Register">
   </div>
   
  </form>
</div>

<script>
function formValidation() {
		var name = document.regForm.name;
		var password = document.regForm.password;
		var username = document.regForm.username;

		if (allLetter(name)) {
			if (passid_validation(password, 7, 12)) {
				if (allLetter1(username)) {
					return true;
				}
			}
		}

		return false;

	}
	function allLetter(name) {
		var letters = /^[A-Za-z]+$/;
		if (name.value.match(letters)) {
			return true;
		} else {
			alert('Name must have alphabet characters only');
			name.focus();
			return false;
		}
	}

	function passid_validation(password, mx, my) {
		var password_len = password.value.length;
		if (password_len == 0 || password_len >= my || password_len < mx) {
			alert("Password should not be empty / length be between " + mx
					+ " to " + my);
			password.focus();
			return false;
		}
		return true;
	}

	function allLetter1(username) {
		var letters = /^[A-Za-z]+$/;
		if (username.value.match(letters)) {
			return true;
		} else {
			alert('Userame must have alphabet characters only');
			username.focus();
			return false;
		}
	}
</script>

</body>
</html>