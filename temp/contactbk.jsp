<!DOCTYPE html>
<html>
<head>
<style>

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}

table {
  width:100%;
}
table, td {
  border: 1px solid black;
  border-collapse: collapse;
}
td {
  padding: 15px;
  text-align: left;
}
table#t01 tr {
  background-color: #eee;
}
input#submit_button {
  background-color: black;
  color: white;
}

</style>
<meta charset="ISO-8859-1">
<title>Contact</title>
</head>
<body>
<h2 style="text-align:center" >CRPT</h3>
<ul>
  <li><a class="active" href="#">Search</a></li>
  <li><a href="#">Contact</a></li>
  <li><a href="#">About Us</a></li>
</ul>
<h3>Contact Us</h3>
<form name="contactform" method="post" action="sendEmail">
<table width="450px" id="t01">
<tr>
  <td valign="top">
  <input  type="text" value="From Email" name="fromEmail" maxlength="50" size="30">
 </td>
 <td valign="top">
  <input  type="text" value="TO Email" name="toEmail" maxlength="80" size="30">
 </td>
</tr>
<tr>
 <td valign="top">
  <input  type="text" value="Name" name="name" maxlength="50" size="30">
 </td>
 <td valign="top" >
  <input  type="text" value="Phone Number" name="telephone" maxlength="30" size="30">
 </td>
</tr>
<tr>
<tr>
 <td valign="top" colspan="2">
  <input  type="text" value="Subject" name="subject" maxlength="40" size="40">
 </td>
</tr>
<tr>
 <td valign="top" colspan="2">
  <textarea  name="message" value="message" maxlength="1000" cols="120" rows="20"></textarea>
 </td>
</tr>
<tr>
 <td style="text-align:left" colspan="2">
  <input type="submit" value="sendEmail" size="30" id="submit_button"> 
 </td>
</tr>
</table>
</form>
</body>
</html>