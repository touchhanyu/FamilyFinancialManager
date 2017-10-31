<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/stock/StockInfoToday.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<div id="info" width="90%"></div>
		<br/>
		<table id="stockdetail" width="60%"></table>
		<div id="tb" style="padding:5px">
			&nbsp股票名称&nbsp<input id="stockname" name="stockname" class="easyui-combobox"/>
			&nbsp<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryInfo()">查询</a>
		</div>
	</body>
</html>