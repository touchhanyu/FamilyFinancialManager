<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/Ionicons/css/ionicons.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/jvectormap/jquery-jvectormap.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/bootstrap-daterangepicker/daterangepicker.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/bootstrap-datepicker/css/bootstrap-datepicker.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/datatables.net-bs/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/iCheck/flat/blue.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/select2/css/select2.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/AdminLTE/css/AdminLTE.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/AdminLTE/css/skins/skin-blue.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/moment/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/bootstrap-datepicker/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/datatables.net/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/select2/js/i18n/zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/AdminLTE/js/adminlte.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/main/main.js"></script>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	function logout() {
		var url = basePath + '/sys/logout';
		$.post(url, function() {
			window.location.reload();
		}, 'text');
	}
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<a href="#" class="logo">
				<span class="logo-mini"><b>F</b>FM</span>
				<span class="logo-lg"><b>F</b>FM</span>
			</a>
			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
					<span class="sr-only">Toggle navigation</span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown message-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-envelope-o"></i>
								<span class="label label-success">4</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">You have 4 messages</li>
								<li>
									<ul class="menu">
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="./js/adminlte/AdminLTE/img/user2-160x160.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>H4<small><i class="fa fa-clock-o"></i> 5 mins</small></h4>
												<p>text?</p>
											</a>
										</li>
									</ul>
								</li>
								<li class="footer"><a href="#">See All Messages</a></li>
							</ul>
						</li>
						<li class="dropdown notifications-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-bell-o"></i>
								<span class="label label-warning">10</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">You have 10 notifications</li>
								<li>
									<ul class="menu">
										<li>
											<a href="#">
												<i class="fa fa-users text-aqua"></i> 5 new members joined today
											</a>
										</li>
									</ul>
								</li>
								<li class="footer"><a href="#">View all</a></li>
							</ul>
						</li>
						<li class="dropdown tasks-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-flag-o"></i>
								<span class="label label-danger">9</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">You have 9 tasks</li>
								<li>
									<ul class="menu">
										<li>
											<a href="#">
												<h3>H3<small class="pull-right">20%</small></h3>
												<div class="progress xs">
													<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">20% Complete</span>
													</div>
												</div>
											</a>
										</li>
									</ul>
								</li>
								<li class="footer">
									<a href="#">View all tasks</a>
								</li>
							</ul>
						</li>
						<li class="dropdown user user-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<img src="./js/adminlte/AdminLTE/img/user2-160x160.jpg" class="user-image" alt="User Image">
								<span class="hidden-xs">${session.user.name}</span>
							</a>
							<ul class="dropdown-menu">
								<li class="user-header">
									<img src="./js/adminlte/AdminLTE/img/user2-160x160.jpg" class="img-circle" alt="User Image">
									<p>${session.user.name}<small>欢迎回来!</small></p>
								</li>
								<li class="user-body">
									<div class="row">
										<div class="col-xs-4 text-center">
											<a href="#">Row1</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Row2</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Row3</a>
										</div>
									</div>
								</li>
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat" onclick="logout()"><span class="glyphicon glyphicon-off"></span>&nbsp;退  出</a>
									</div>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<aside class="main-sidebar">
			<section class="sidebar">
				<!-- 搜索框 -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
						</span>
					</div>
				</form>
				<ul id="menu" class="sidebar-menu" data-widget="tree"></ul>
			</section>
		</aside>
		<div class="content-wrapper">
			<div class="row">
				<div class="col-md-12">
					<div class="nav-tabs-custom">
						<ul id="tabsnav" class="nav nav-tabs">
							<li class="active"><a href="#homepage" data-toggle="tab">主页 <i class="fa fa-times"></i></a></li>
						</ul>
						<div id="tabcontext" class="tab-content">
							<div class="active tab-pane" id="homepage">
								<section class="content-header">
									<h1>
										${session.user.name}
										<small>欢迎回来</small>
									</h1>
									<ol class="breadcrumb">
										<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
									</ol>
								</section>
								<hr>
								<section class="content">
									<div class="row">
										<!-- 动态行 -->
										<!-- <div class="col-xs-12">
											<div class="box">
												<div class="box-header">
													<h3 class="box-title">JQuery DataTable</h3>
												</div>
												<div class="box-body">
													<table id="datatable" class="table table-striped table-bordered"></table>
												</div>
											</div>
										</div> -->
										<section class="col-lg-7 connectedSortable">
											<div class="box box-primary">
												<div class="box-header">
													<i class="ion ion-clipboard"></i>
													<h3 class="box-title">待办任务</h3>
													<div class="box-tools pull-right">
														<button type="button" class="btn btn-default pull-right"><i class="fa fa-plus"></i> Add item</button>
													</div>
												</div>
												<div class="box-body">
													<ul class="todo-list">
														<li>
															<span class="handle">
																<i class="fa fa-ellipsis-v"></i>
																<i class="fa fa-ellipsis-v"></i>
															</span>
															<input type="checkbox" value="">
															<span class="text">Design a nice theme</span>
															<small class="label label-danger"><i class="fa fa-clock-o"></i> 2 mins</small>
															<div class="tools">
																<i class="fa fa-edit"></i>
																<i class="fa fa-trash-o"></i>
															</div>
														</li>
														<li>
															<span class="handle">
																<i class="fa fa-ellipsis-v"></i>
																<i class="fa fa-ellipsis-v"></i>
															</span>
															<input type="checkbox" value="">
															<span class="text">Make the theme responsive</span>
															<small class="label label-info"><i class="fa fa-clock-o"></i> 4 hours</small>
															<div class="tools">
																<i class="fa fa-edit"></i>
																<i class="fa fa-trash-o"></i>
															</div>
														</li>
														<li>
															<span class="handle">
																<i class="fa fa-ellipsis-v"></i>
																<i class="fa fa-ellipsis-v"></i>
															</span>
															<input type="checkbox" value="">
															<span class="text">Let theme shine like a star</span>
															<small class="label label-warning"><i class="fa fa-clock-o"></i> 1 day</small>
															<div class="tools">
																<i class="fa fa-edit"></i>
																<i class="fa fa-trash-o"></i>
															</div>
														</li>
														<li>
															<span class="handle">
																<i class="fa fa-ellipsis-v"></i>
																<i class="fa fa-ellipsis-v"></i>
															</span>
															<input type="checkbox" value="">
															<span class="text">Let theme shine like a star</span>
															<small class="label label-success"><i class="fa fa-clock-o"></i> 3 days</small>
															<div class="tools">
																<i class="fa fa-edit"></i>
																<i class="fa fa-trash-o"></i>
															</div>
														</li>
														<li>
															<span class="handle">
																<i class="fa fa-ellipsis-v"></i>
																<i class="fa fa-ellipsis-v"></i>
															</span>
															<input type="checkbox" value="">
															<span class="text">Check your messages and notifications</span>
															<small class="label label-primary"><i class="fa fa-clock-o"></i> 1 week</small>
															<div class="tools">
																<i class="fa fa-edit"></i>
																<i class="fa fa-trash-o"></i>
															</div>
														</li>
														<li>
															<span class="handle">
																<i class="fa fa-ellipsis-v"></i>
																<i class="fa fa-ellipsis-v"></i>
															</span>
															<input type="checkbox" value="">
															<span class="text">Let theme shine like a star</span>
															<small class="label label-default"><i class="fa fa-clock-o"></i> 1 month</small>
															<div class="tools">
																<i class="fa fa-edit"></i>
																<i class="fa fa-trash-o"></i>
															</div>
														</li>
													</ul>
												</div>
												<div class="box-footer clearfix no-border">
													<ul class="pagination pagination-sm inline">
														<li><a href="#">&laquo;</a></li>
														<li><a href="#">1</a></li>
														<li><a href="#">2</a></li>
														<li><a href="#">3</a></li>
														<li><a href="#">&raquo;</a></li>
													</ul>
												</div>
											</div>
										</section>
										<section class="col-lg-5 connectedSortable">
											<!-- 日历 -->
											<div class="box box-solid bg-blue-gradient">
												<div class="box-header">
													<i class="fa fa-calendar"></i>
													<h3 class="box-title">日历</h3>
													<div class="pull-right box-tools">
														<div class="btn-group">
															<button type="button" class="btn btn-outline btn-sm dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bars"></i></button>
															<ul class="dropdown-menu pull-right" role="menu">
																<li><a href="#">Add new event</a></li>
																<li><a href="#">Clear events</a></li>
																<li class="divider"></li>
																<li><a href="#">View calendar</a></li>
															</ul>
														</div>
														<button type="button" class="btn btn-outline btn-sm" data-widget="collapse"><i class="fa fa-minus"></i></button>
														<button type="button" class="btn btn-outline btn-sm" data-widget="remove"><i class="fa fa-times"></i></button>
													</div>
												</div>
												<div class="box-body no-padding">
													<div id="calendar" style="width: 100%"></div>
												</div>
												<div class="box-footer text-black">
													<div class="row">
														<div class="col-sm-6">
															<div class="clearfix">
																<span class="pull-left">Task #1</span>
																<small class="pull-right">90%</small>
															</div>
															<div class="progress xs">
																<div class="progress-bar progress-bar-blue" style="width: 90%;"></div>
															</div>
															
															<div class="clearfix">
																<span class="pull-left">Task #2</span>
																<small class="pull-right">70%</small>
															</div>
															<div class="progress xs">
																<div class="progress-bar progress-bar-blue" style="width: 70%;"></div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- 地图 -->
											<div class="box box-solid bg-light-blue-gradient">
												<div class="box-header">
													<div class="pull-right box-tools">
														<button type="button" class="btn btn-primary btn-sm daterange pull-right" data-toggle="tooltip" title="Date range"><i class="fa fa-calendar"></i></button>
														<button type="button" class="btn btn-primary btn-sm pull-right" data-widget="collapse" data-toggle="tooltip" title="Collapse" style="margin-right: 5px;"><i class="fa fa-minus"></i></button>
													</div>
													<i class="fa fa-map-marker"></i>
													<h3 class="box-title">地图</h3>
													<div class="box-body">
														<div id="world-map" style="height: 250px; width: 100%;"></div>
													</div>
													<div class="box-footer no-border">
														<div class="row">
															<div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
																<div id="sparkline-1"></div>
																<div class="knob-label">Visitors</div>
															</div>
															<div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
																<div id="sparkline-2"></div>
																<div class="knob-label">Online</div>
															</div>
															<div class="col-xs-4 text-center">
																<div id="sparkline-3"></div>
																<div class="knob-label">Exists</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</section>
									</div>
								</section>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>