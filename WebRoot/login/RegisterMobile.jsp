<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
			var basePath = '<%=request.getContextPath()%>';
			function register() {
				var url = basePath + '/login/register';
				$('#userform').attr('action', url);
				$('#userform').submit();
			}
		</script>
	</head>
	<body>
		<br/><br/><br/>
		<div class="easyui-navpanel">
			<header>
				<div class="m-toolbar">
					<span class="m-title">帐号注册</span>
				</div>
			</header>
			<form id="userform" method="post">
				<ul class="m-list">
					<li><span>用户名：</span><br><input name="user.name" class="easyui-textbox" prompt="" style="width:100%"></li>
					<li><span>登录密码：</span><br><input id="pwd" name="user.password" type="password" class="easyui-textbox" prompt="" style="width:100%"></li>
					<li><span>确认登录密码：</span><br><input id="repwd" type="password" class="easyui-textbox" prompt="" style="width:100%"></li>
					<div style="text-align:center;margin-top:30px">
						<a href="#" class="easyui-linkbutton c6" plain="true" outline="true" style="width:100%;height:40px" onclick="register();"><span style="font-size:16px">注   册</span></a> 
					</div>
				</ul>
			</form>
		</div>
	</body>
</html>