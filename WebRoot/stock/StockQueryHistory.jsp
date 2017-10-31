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
		<script type="text/javascript" src="${pageContext.request.contextPath}/stock/StockQueryHistory.js"></script>
		<script type="text/javascript">
			var basePath = '<%=request.getContextPath()%>';
		</script>
	</head>
	<body>
		<div class="easyui-accordion" data-options="multiple:true">
			<div title="录入信息" data-options="iconCls:'icon-query',collapsed:false,collapsible:false" style="overflow:auto;padding:10px;">
				<form id="info" method="post">
					<table align="center">
						<tr>
							<td class="INPUT_TXT">起始时间</td>
							<td class="INPUT_BOX">
								<input id="startDay" class="easyui-datebox" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">结束时间</td>
							<td class="INPUT_BOX">
								<input id="endDay" class="easyui-datebox" missingMessage="不能为空"/>
							</td>
						</tr>
						<tr>
							<td class="INPUT_TXT">股票名称</td>
							<td class="INPUT_BOX">
								<input id="stockName" class="easyui-combobox" required="required" missingMessage="不能为空"/>
							</td>
						</tr>
					</table>
					<div style="text-align:center;padding:5px">
						<a class="easyui-linkbutton" icon="icon-search" onclick="queryHistory();">查询</a>
					</div>
				</form>
			</div>
			<div title="查询结果" data-options="iconCls:'icon-search',collapsed:false,collapsible:false" style="overflow:auto;padding:10px;">
				<table id="stockhistory" width="90%"/>
			</div>
		</div>
	</body>
</html>