<%@page contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/login/Login_easyui.js"></script>
		<script type="text/javascript">
			var basePath = '<%=request.getContextPath()%>';
		</script>
	</head>
	<title>家庭财务管理系统-Family Financial Managment</title>
	<body>
		<br/><br/><br/>
		<form id="loginform">
			<table align="center">
				<tr>
					<td>
						<div class="easyui-panel" title="登录" style="width:400px;padding:30px 70px 20px 70px">
							<div style="margin-bottom:10px">
								<input id="username" name="user.name" class="easyui-textbox" style="width:100%;height:40px;padding:12px"
									data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38" required="required" missingMessage="请输入用户名"/>
							</div>
							<div style="margin-bottom:10px">
								<input id="password" name="user.password" class="easyui-textbox" style="width:100%;height:40px;padding:12px"
									type="password" data-options="prompt:'password',iconCls:'icon-lock',iconWidth:38" required="required" missingMessage="请输入密码"/>
							</div>
							<div style="margin-bottom:10px">
								<input name="autologin" type="checkbox" checked="true"/>
								<span>下次自动登录</span>
							</div>
							<div>
								<a herf="#" class="easyui-linkbutton" style="padding:5px 0px;width:100%;background:#33CC33" onclick="login();">
									<span style="font-size:14px;color:white">登   录</span>
								</a>
							</div>
							<div style="padding:5px 0;">
								<span>没有帐号？</span>
								<a href="#" class="easyui-linkbutton" style="padding:5px 0px;width:30%;background:#0099CC" onclick="register();">
									<span style="font-size:14px;color:white">注   册</span>
								</a>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>