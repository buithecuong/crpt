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
    <jsp:include page="managerSideBar.jsp"/>
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
                  
                  <c:if test="${error != null}">
					<div  style='width: 50%; margin-left: auto; margin-right: auto; margin-top: 200px; text-align: center;'>${error}</div>
				</c:if>
				<div id="chartContainer" style="height: 370px; width: 100%;"></div>
			
				<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
                </div>
                
                               
                 <div class="card-body table-responsive p-0">
                <table class="table table-striped table-valign-middle">
                  <thead>
                  <tr>
                    <th>Date No</th>
                    <th>Username</th>
                    <th>Date</th>
                    <th>Hours</th>
                  </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${dataPoints}" var="record" varStatus="loop">
                     <tr>
                       <td width="60" align="center">
                       <img src="${pageContext.request.contextPath}/resources/images/date.png" alt="Task#" class="img-circle img-size-32 mr-2">
                       ${loop.index+1}
                   </td>
                   <td width="60" align="center">${record.employee.username}</td>
                   <td width="60" align="center">
                       <small class="text-success mr-1">
                        <i class="fas fa-arrow-up"></i>
                        ${record.date}
                      </small>
                   </td>
                   <td width="60" align="center">${record.hours}</td>
                   
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

            <div class="card">
              <div class="card-header border-0">
                <h3 class="card-title">Active Week Working Hours</h3>
                <div class="card-tools">
                  <a href="#" class="btn btn-tool btn-sm">
                    <i class="fas fa-download"></i>
                  </a>
                  <a href="#" class="btn btn-tool btn-sm">
                    <i class="fas fa-bars"></i>
                  </a>
                </div>
              </div>
              <div class="card-body table-responsive p-0">
                <table class="table table-striped table-valign-middle">
                  <thead>
                  <tr>
                    <th>Task No</th>
                    <th>Date</th>
                    <th>Username</th>
                    <th>Tasks</th>
                    <th>Hours</th>
                    <th>Status</th>
                    <th>Date</th>
                  </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${employeetimesheetList}" var="record" varStatus="loop">
                     <tr>
                       <td width="60" align="center">
                       <img src="${pageContext.request.contextPath}/resources/images/task.png" alt="Task#" class="img-circle img-size-32 mr-2">
                       ${loop.index+1}
                   </td>
                   <td width="60" align="center">
                       <small class="text-success mr-1">
                        <i class="fas fa-arrow-up"></i>
                        ${record.date}
                      </small>
                   </td>
                  <td width="60" align="center">${record.employee.username}</td>
                   <td width="60" align="center">${record.jobTitle}</td>
                   <td width="60" align="center">${record.hours}</td>
                   <td width="60" align="center">${record.status}</td>
                   </tr>
                  </c:forEach>                 
                  
                  </tbody>
                </table>
              </div>
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col-md-6 -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-header border-0">
                <div class="d-flex justify-content-between">
                  <h3 class="card-title">Today TimeSheet</h3>
                  <a href="javascript:void(0);">View Report</a>
                </div>
              </div>
              <div class="card-body">
                <div class="d-flex">
                     <table class="table table-striped table-valign-middle">
                      <thead>
                      <tr>
                        <th>Task No</th>
                        <th>Date</th>
                        <th>Username</th>
                        <th>Tasks</th>
                        <th>Hours</th>
                        <th>Status</th>
                      </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${currentdateemployeetimesheetList}" var="record" varStatus="loop">
                         <tr>
                           <td width="60" align="center">
                           <img src="${pageContext.request.contextPath}/resources/images/task.png" alt="Task#" class="img-circle img-size-32 mr-2">
                           ${loop.index+1}
                       </td>
                       <td width="60" align="center">
                           <small class="text-success mr-1">
                            <i class="fas fa-arrow-up"></i>
                            ${record.date}
                          </small>
                       </td>
                       <td width="60" align="center">${record.employee.username}</td>
                       <td width="60" align="center">${record.jobTitle}</td>
                       <td width="60" align="center">${record.hours}</td>
                       <td width="60" align="center">${record.status}</td>
                      </tr>
                      </c:forEach>                 
                      
                      </tbody>
                    </table>
                </div>
                <!-- /.d-flex -->

                <div class="position-relative mb-4">
                  <canvas id="sales-chart" height="200"></canvas>
                </div>

                <div class="d-flex flex-row justify-content-end">
                  <span class="mr-2">
                    <i class="fas fa-square text-primary"></i> This year
                  </span>

                  <span>
                    <i class="fas fa-square text-gray"></i> Last year
                  </span>
                </div>
              </div>
            </div>
            <!-- /.card -->
             <div class="card">
              <div class="card-header border-0">
                <div class="d-flex justify-content-between">
                  <h3 class="card-title">Employee Objectives</h3>
                  <a href="javascript:void(0);">View Report</a>
                </div>
              </div>
              <div class="card-body">
                <div class="d-flex">
                     <table class="table table-striped table-valign-middle">
                      <thead>
                      <tr>
                        <th>Obj No</th>
                        <th>Username</th>
                        <th>Objective Name</th>
                        <th>Duration (Months)</th>
                        <th>Type</th>
                        <th>Status</th>
                      </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${employeeObjectiveList}" var="record" varStatus="loop">
                         <tr>
                           <td width="60" align="center">
                           <img src="${pageContext.request.contextPath}/resources/images/objective.png" alt="Obj#" class="img-circle img-size-32 mr-2">
                           ${loop.index +1}
                       </td>
                       <td width="60" align="center">${record.employee.username}</td>
                       <td width="60" align="center">${record.name}</td>
                       <td width="60" align="center">${record.duration}</td>
                       <td width="60" align="center">${record.type}</td>
                       <td width="60" align="center">
                           <small class="text-success mr-1">
                            <i class="fas fa-arrow-up"></i>
                            ${record.status}
                          </small>
                       </td>
                      </tr>
                      </c:forEach>                 
                      
                      </tbody>
                    </table>
                </div>
                <!-- /.d-flex -->

                <div class="position-relative mb-4">
                  <canvas id="sales-chart" height="200"></canvas>
                </div>

                <div class="d-flex flex-row justify-content-end">
                  <span class="mr-2">
                    <i class="fas fa-square text-primary"></i> This year
                  </span>

                  <span>
                    <i class="fas fa-square text-gray"></i> Last year
                  </span>
                </div>
              </div>
            </div>
            <!-- /.card -->

            <div class="card">
              <div class="card-header border-0">
                <h3 class="card-title">Quarterly Overview</h3>
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
                <div class="d-flex justify-content-between align-items-center border-bottom mb-3">
                  <p class="text-success text-xl">
                    <i class="ion ion-ios-refresh-empty"></i>
                  </p>
                  <p class="d-flex flex-column text-right">
                    <span class="font-weight-bold">
                      <i class="ion ion-android-arrow-up text-success"></i> 12%
                    </span>
                    <span class="text-muted">PERFORMANCE RATE</span>
                  </p>
                </div>
                <!-- /.d-flex -->
                <div class="d-flex justify-content-between align-items-center border-bottom mb-3">
                  <p class="text-warning text-xl">
                    <i class="ion ion-ios-cart-outline"></i>
                  </p>
                  <p class="d-flex flex-column text-right">
                    <span class="font-weight-bold">
                      <i class="ion ion-android-arrow-up text-warning"></i> 0.8%
                    </span>
                    <span class="text-muted">OVERTIME RATE</span>
                  </p>
                </div>
                <!-- /.d-flex -->
                <div class="d-flex justify-content-between align-items-center mb-0">
                  <p class="text-danger text-xl">
                    <i class="ion ion-ios-people-outline"></i>
                  </p>
                  <p class="d-flex flex-column text-right">
                    <span class="font-weight-bold">
                      <i class="ion ion-android-arrow-down text-danger"></i> 1%
                    </span>
                    <span class="text-muted">VIOLATION RATE</span>
                  </p>
                </div>
                <!-- /.d-flex -->
              </div>
            </div>
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
