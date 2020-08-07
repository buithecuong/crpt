  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <img src="${pageContext.request.contextPath}/resources/images/crpt.jfif" alt="CRPT Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">CRPT</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="${pageContext.request.contextPath}/resources/images/cbui.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="intro" class="d-block">${firstname}</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            
          </li>
          <li class="nav-item">
            <a href="datepickerRow" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Timesheet single row
                <span class="right badge badge-danger">New</span>
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="datepicker" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Timesheets
                <span class="right badge badge-danger">New</span>
              </p>
            </a>
          </li>
          <li class="nav-item has-treeview">
            <a href="timesheet_bk" class="nav-link">
              <i class="nav-icon fas fa-copy"></i>
              <p>
                Layout Options
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">6</span>
              </p>
            </a>
          </li>
          <li class="nav-header">View</li>
          <li class="nav-item">
            <a href="list" class="nav-link">
              <i class="nav-icon fas fa-calendar-alt"></i>
              <p>
                List Employees
                <span class="badge badge-info right">2</span>
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="viewTimesheetList" class="nav-link">
              <i class="nav-icon fas fa-calendar-alt"></i>
              <p>
                List Timesheets
                <span class="badge badge-info right">2</span>
              </p>
            </a>
          </li>
          <li class="nav-header">LABELS</li>
          <li class="nav-item">
            <a href="viewDailyTimesheetList" class="nav-link">
              <i class="nav-icon far fa-circle text-danger"></i>
              <p class="text">DailyTimeSheet List</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="viewDailyTimesheetChart" class="nav-link">
              <i class="nav-icon far fa-circle text-danger"></i>
              <p class="text">DailyTimeSheet Chart</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="getContact" class="nav-link">
              <i class="nav-icon far fa-circle text-warning"></i>
              <p>Warning</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="contact" class="nav-link">
              <i class="nav-icon far fa-circle text-info"></i>
              <p>Contact</p>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  