<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>CRPT | EMPLOYEE</title>
 
  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link href="<c:url value="/resources/css/all.min.css" />" rel="stylesheet" type="text/css">
  <!-- IonIcons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link href="<c:url value="/resources/css/adminlte.min.css" />" rel="stylesheet" type="text/css">
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
<!--
`body` tag options:

  Apply one or more of the following classes to to the body tag
  to get the desired effect

  * sidebar-collapse
  * sidebar-mini
-->
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  <!-- Navbar -->
  <div id="header">
    <jsp:include page="navBar.jsp"/>
</div>
 
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <div id="header">
    <jsp:include page="sideBar.jsp"/>
</div>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">CRPT Employee</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="adminIntro">Home</a></li>
              <li class="breadcrumb-item active">Employee</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-6">
            <div class="card">
              <div class="card-header border-0">
                <div class="d-flex justify-content-between">
                  <h3 class="card-title">Employee Edit</h3>
                  <a href="javascript:void(0);">View Report</a>
                </div>
              </div>
              <div class="card-body">
                <p>Employee: ${employee}</p>
                <center><h1>Edit Employee</h1></center>

                <form:form method="POST" modelAttribute="employee" action="updateSave">
                    <table align= "center">
                        <tr>
                            <td>Id :</td>
                            <td><form:input path="id" /></td>
                        </tr>
                        <tr>
                            <td>Name :</td>
                            <td><form:input path="name" /></td>
                        </tr>
                        <tr>
                            <td>Username :</td>
                            <td><form:input path="username" /></td>
                        </tr>
                        <tr>
                            <td>Password :</td>
                            <td><form:input path="password" /></td>
                        </tr>
                        <tr>
                            <td>Team :</td>
                            <td><form:input path="team" /></td>
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
               </div>
            </div>
            <!-- /.card -->

            
          </div>
          <!-- /.col-md-6 -->
          <!-- /.col-md-6 -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
   <div id="header">
    <jsp:include page="footer.jsp"/>
</div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="resources/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="resources/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE -->
<script src="dist/js/adminlte.js"></script>

<!-- OPTIONAL SCRIPTS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<script src="dist/js/demo.js"></script>
<script src="dist/js/pages/dashboard.js"></script>
</body>
</html>
