<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/account/DaybookMonthlyReporting.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<form id="info">
			<div class="easyui-panel" title="图表信息" style="width:99%;padding:30px 70px 20px 70px">
				<table align="center">
					<tr>
						<td class="INPUT_TXT">图表年份</td>
						<td class="INPUT_BOX">
							<input id="year" name="year" class="easyui-combobox" required="required" missingMessage="不能为空"/>
						</td>
						<td class="INPUT_TXT">图表月份</td>
						<td class="INPUT_BOX">
							<input id="month" name="month" class="easyui-combobox" required="required" missingMessage="不能为空"/>
						</td>
					</tr>
				</table>
				<br/>
				<center>
					<a class="easyui-linkbutton" icon="icon-print" onclick="reporting();">生成图表</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a id="detailbtn" herf="#" class="easyui-linkbutton" data-options="toggle:true" onclick="showDetailDialog(this);">汇总明细信息</a>
				</center>
			</div>
		</form>
		<div id="detailDialog" class="easyui-dialog" title="个人月度账务统计" style="width:340px;height:250px;padding:5px"
			data-options="iconCls:'icon-save',minimizable:true,maximizable:true,closable:true,collapse:true,closed:true,resizable:true">
			<center>
				<div id="detailinfo" class="easyui-propertygrid"/>
			</center>
		</div>
		<div class="easyui-panel" title="个人账务月度明细图表" style="text-align:center;width:99%" data-options="iconCls:'icon-title-chart'">
			<img id="reportPic" src=""/>
		</div>
	</body>
</html>