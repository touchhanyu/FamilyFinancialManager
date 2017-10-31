<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/metro/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/mobile.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/color.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.mobile.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
			function login() {
				var url = basePath + '/login/login';
				var fm = $('#loginform');
				fm.attr('action', url);
				fm.submit();
			}
			function register() {
				window.location.href = basePath + "/login/RegisterMobile.jsp";
			}
		</script>
	</head>
	<body>
		<br/><br/><br/>
		<div class="easyui-navpanel">
			<header>
				<div class="m-toolbar">
					<span class="m-title">登  录</span>
				</div>
			</header>
			<div style="margin:20px auto;width:100px;height:100px;border-radius:100px;overflow:hidden">
				<img src="${pageContext.request.contextPath}/File/images/login1.jpg" style="margin:0;width:100%;height:100%;">
			</div>
			<form id="loginform">
				<div style="padding:0 20px">
					<div style="margin-bottom:10px">
						<input id="username" name="user.name" class="easyui-textbox" style="width:100%;height:40px;padding:12px"
							data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38" required="required" missingMessage="请输入用户名"/>
					</div>
					<div style="margin-bottom:10px">
						<input id="password" name="user.password" class="easyui-textbox" style="width:100%;height:40px;padding:12px"
							type="password" data-options="prompt:'password',iconCls:'icon-lock',iconWidth:38" required="required" missingMessage="请输入密码"/>
					</div>
					<div style="margin-bottom:10px">
						<center>记住我&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name="autologin" class="easyui-switchbutton"/></center>
					</div>
					<div style="text-align:center;margin-top:30px">
						<a href="#" class="easyui-linkbutton c1" style="width:100%;height:40px" onclick="login();"><span style="font-size:16px">登   录</span></a>
						<br/><br/>
						<a href="#" class="easyui-linkbutton c6" plain="true" outline="true" style="width:100%;height:40px" onclick="register();"><span style="font-size:16px">注   册</span></a>
					</div>
				</div>
				<input type="hidden" name="clientType" value="mobile"/>
			</form>
		</div>
	</body>
</html>