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
		<script type="text/javascript" src="${pageContext.request.contextPath}/sys/permissionmng.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<div class="easyui-accordion" data-options="multiple:true">
			<div id="sysmenu" title="用户菜单权限管理" data-options="iconCls:'icon-edit',collapsed:false,collapsible:false" style="padding:5px;">
			</div>
		</div>
		<div style="text-align:center;padding:5px">
			<a class="easyui-linkbutton" id="submitbtn" icon="icon-ok" onclick="saveData();">保存</a>
		</div>
	</body>
</html>