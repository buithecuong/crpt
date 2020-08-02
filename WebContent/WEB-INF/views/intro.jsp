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
            <h1 class="m-0 text-dark">CRPT Timesheet</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="adminIntro">Home</a></li>
              <li class="breadcrumb-item active">EMPLOYEE DETAILS</li>
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
                  <h3 class="card-title">EMPLOYEE DETAILS</h3>
                  <a href="javascript:void(0);">View Report</a>
                </div>
              </div>
              <div class="card-body">
                <p>Employee: ${employee}</p>
                <center><h2>EMPLOYEE DETAILS</h2></center>

                <table align="center" border="1">
                    <tr>
                        <td>Welcome ${firstname}</td>

                    </tr>
                    <tr>
                        <td>ID : ${employee.id}</td>
                    </tr>
                    <tr>
                        <td>Name : ${employee.name}</td>
                    </tr>
                    <tr>
                        <td>User Name : ${employee.username}</td>
                    </tr>
                    <tr>
                        <td>Team : ${employee.team}</td>
                    </tr>
                    <tr>
                        <td>Status : ${employee.status}</td>
                    </tr>

                    <tr>
                    </tr>
                </table>
                
            <br><br><br>v
                <center>
                    <a href="welcome">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="logout">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <a href="datepicker">Daily Timesheet</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    
                    
                </center>
               </div>
            </div>
            <!-- /.card -->

            
          </div>
          <div class="col-lg-6">
            <div class="card">
              <div class="card-header border-0">
                <h3 class="card-title">Timesheet History</h3>
                <div class="card-tools">
                  <a href="#" class="btn btn-sm btn-tool">
                    <i class="fas fa-download"></i>
                  </a>
                  <a href="#" class="btn btn-sm btn-tool">
                    <i class="fas fa-bars"></i>
                  </a>
                </div>
              </div>
              <div class="card-body">
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
              </div>
            </div>
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
