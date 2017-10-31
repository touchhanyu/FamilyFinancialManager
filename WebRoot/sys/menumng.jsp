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
		<script type="text/javascript" src="${pageContext.request.contextPath}/sys/menumng.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<div class="easyui-accordion" data-options="multiple:true">
			<div id="menu" title="系统菜单管理" data-options="iconCls:'icon-edit',collapsed:false,collapsible:false" style="padding:5px;"></div>
			<div id="mm" class="easyui-menu" style="width:120px;">
				<div onclick="addMenu(0);" data-options="iconCls:'icon-add'">添加同级菜单</div>
				<div onclick="addMenu(1);" data-options="iconCls:'icon-add'">添加子菜单</div>
				<div onclick="editMenu();" data-options="iconCls:'icon-edit'">修改</div>
				<div onclick="removeMenu();" data-options="iconCls:'icon-remove'">删除</div>
			</div>
			<div id="dialog"></div>
		</div>
	</body>
</html>