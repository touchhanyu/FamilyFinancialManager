<%@page contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<br/><br/><br/>
		<form id="info">
			<table align="center">
				<tr>
					<td class="INPUT_TXT">菜单ID</td>
					<td class="INPUT_BOX">
						<input class="easyui-textbox" id="" name="menu.id"/>
					</td>
				</tr>
				<tr>
					<td class="INPUT_TXT">菜单名称</td>
					<td class="INPUT_BOX">
						<input class="easyui-textbox" id="" name="menu.text"/>
					</td>
				</tr>
				<tr>
					<td class="INPUT_TXT">菜单图标</td>
					<td class="INPUT_BOX">
						<input class="easyui-textbox" id="" name="menu.iconCls"/>
					</td>
				</tr>
				<tr>
					<td class="INPUT_TXT">菜单顺序</td>
					<td class="INPUT_BOX">
						<input class="easyui-textbox" id="" name="menu.mOrder"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>