<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>家庭财务管理系统-Family Financial Managment</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/AdminLTE/css/AdminLTE.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/Ionicons/css/ionicons.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/adminlte/iCheck/square/blue.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminlte/iCheck/icheck.min.js"></script>
<!-- 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/signin.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/login/Login.js"></script>
<script type="text/javascript">
	var basePath = '<%=request.getContextPath()%>';
	$(function() {
		$('input').iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%' // optional
		});
	})
</script>
</head>
<body class="hold-transition login-page">
	<div id="loginsucc" class="alert alert-success" role="alert" style="display: none">
		<string>登录成功！</string>
	</div>
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>F</b>FM</a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">登录</p>
			<form id="loginform" method="post">
				<div class="form-group has-feedback">
					<input type="text" id="username" class="form-control" placeholder="用户名">
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" id="password" class="form-control" placeholder="密码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input id="autologin" type="checkbox" name="autologin" checked/> 下次自动登录</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登     录</button>
					</div>
				</div>
			</form>
			<a href="#">忘记密码</a><br>
			<span>没有帐号？</span><a href="#" class="text-center" onclick="register();">注     册</a>
		</div>
	</div>
</body>
<!-- 
<body>
	<div class="container">
		<div id="loginsucc" class="alert alert-success" role="alert" style="display: none">
			<string>登录成功！</string>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">登录</h3>
			</div>
			<div class="panel-body">
				<form id="loginform" class="form-signin" method="post">
					<h2 class="form-signin-heading"></h2>
					<label for="inputEmail" class="sr-only">用户名</label>
					<input type="text" id="username" name="user.name" class="form-control" placeholder="用户名" required autofocus/>
					<label for="inputPassword" class="sr-only">密码</label>
					<input type="password" id="password" name="user.password" class="form-control" placeholder="密码" required/>
					<div class="checkbox">
						<label>
							<input id="autologin" type="checkbox" name="autologin" checked="true"/> 下次自动登录
						</label>
					</div>
					<button class="btn btn-success btn-block" onclick="login();">登     录</button>
					<br/>
					<p>
						<span>没有帐号？</span>
						<button class="btn btn-primary btn-blocklogin navbar-right" onclick="register();">注     册</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
-->
</html>