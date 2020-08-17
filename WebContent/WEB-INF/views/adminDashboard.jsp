<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>CRPT | Dashboard</title>
 
  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link href="<c:url value="/resources/css/all.min.css" />" rel="stylesheet" type="text/css">
  <!-- IonIcons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link href="<c:url value="/resources/css/adminlte.min.css" />" rel="stylesheet" type="text/css">
 <script type="text/javascript">
    window.onload = function() {
     
    var dps = [];
    <c:if test="${error == null}">
    var chart = new CanvasJS.Chart("chartContainer", {
        theme: "light2", // "light1", "dark1", "dark2"
        animationEnabled: true,
        title: {
            text: "Daily Timesheet Historical"
        },
        data: [{
            type: "column",
            dataPoints: dps
            
        }]
    });
    </c:if>
     
    var xValue;
    var yValue;
     
    <c:forEach items="${dataPoints}" var="dataPoint">
        yValue = parseInt("${dataPoint.hours}");
        xValue = new Date("${dataPoint.date}");
        xValue.setDate(xValue.getDate() + 1)
        dps.push({
            x : xValue,
            y : yValue,
        });		
    </c:forEach>	

     
    <c:if test="${error == null}">
    chart.render();
    </c:if>
     
    }
</script>
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
    <jsp:include page="adminSideBar.jsp"/>
</div>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">CRPT Dashboard</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard</li>
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
            <!-- /.card -->
            <div class="card">
              <div class="card-header border-0">
                <div class="d-flex justify-content-between">
                  <h3 class="card-title">Total working hours/week: ${totalTimeSheetHours} hrs</h3>
                  <a href="javascript:void(0);">View Report</a>
                </div>
              </div>
              <div class="card-body">
                <div class="d-flex">
                  
                  </div>
                
                               
                 <div class="card-body table-responsive p-0">
                <table class="table table-striped table-valign-middle">
                  <thead>
                  <tr>
                    <th>Employee No</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Team</th>
                    <th>Role</th>
                    <th>Edit/Delete</th>
                  </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${employeeList}" var="record" varStatus="loop">
                     <tr>
                       <td width="60" align="center">
                       <img src="${pageContext.request.contextPath}/resources/images/employee.png" alt="Task#" class="img-circle img-size-32 mr-2">
                       ${loop.index+1}
                   </td>
                   <td width="60" align="center">${record.name}</td>
                   <td width="60" align="center">${record.username}</td>
                   <td width="60" align="center">${record.team}</td>
                   <td width="60" align="center">${record.status}</td>
                   <td width="60" align="center"><a href="edit?id=${record.id}">Edit</a>/<a href="delete?id=${record.id}" onclick="return confirm('Do you really want to delete?')">Delete</a></td>
                   </td>
                  </tr>
                  </c:forEach>                 
                  
                  </tbody>
                </table>
              </div>

                <!-- /.d-flex -->

                <div class="position-relative mb-4">
                  <canvas id="visitors-chart" height="200"></canvas>
                </div>
                
                <div class="d-flex flex-row justify-content-end">
                  <span class="mr-2">
                    <i class="fas fa-square text-primary"></i> This Week
                  </span>

                  <span>
                    <i class="fas fa-square text-gray"></i> Last Week
                  </span>
                </div>
              </div>
            </div>
            <!-- /.card -->

           
            <!-- /.card -->
          </div>
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
