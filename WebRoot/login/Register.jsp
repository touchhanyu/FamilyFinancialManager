<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/login/Register.js"></script>
		<script type="text/javascript">
			var basePath = '<%=request.getContextPath()%>';
		</script>
	</head>
	<body>
		<br/><br/><br/>
		<form id="userform" method="post">
			<table align="center">
				<tr>
					<td>
						<div class="easyui-panel" title="帐号注册" style="width:400px;padding:30px 70px 20px 70px">
							<div><font color="red">*</font>用户名：</div>
							<div style="margin-bottom:20px">
								<input name="user.name" class="easyui-textbox" style="width:100%;height:32px"/>
							</div>
							<div><font color="red">*</font>登录密码：</div>
							<div style="margin-bottom:20px">
								<input id="pwd" name="user.password" class="easyui-textbox" type="password" style="width:100%;height:32px"/>
							</div>
							<div><font color="red">*</font>确认登录密码：</div>
							<div style="margin-bottom:20px">
								<input id="repwd" class="easyui-textbox" type="password" style="width:100%;height:32px"/>
							</div>
							<div style="margin-bottom:20px">
								<a herf="#" class="easyui-linkbutton" style="padding:5px 0px;width:100%;background:#0099CC" onclick="register();">
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