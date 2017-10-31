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
		<script type="text/javascript" src="${pageContext.request.contextPath}/account/Daybook.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<form id="dataform">
			<div class="easyui-accordion" data-options="multiple:true">
				<div title="录入信息" data-options="iconCls:'icon-edit',collapsed:false" style="overflow:auto;padding:10px;">
					<table align="center">
						<tr>
							<td class="INPUT_TXT">资金方向</td>
							<td class="INPUT_BOX">
								<input id="direction" name="dayBook.direction" class="easyui-combobox" required="required" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">金额</td>
							<td class="INPUT_BOX">
								<input id="amount" name="dayBook.amount" class="easyui-numberbox"
									data-options="min:0,precision:2,groupSeparator:','" required="required" missingMessage="不能为空"/>
							</td>
						</tr>
						<tr>
							<td class="INPUT_TXT">交易账户</td>
							<td class="INPUT_BOX">
								<input id="type" name="dayBook.type" required="required" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">用途</td>
							<td class="INPUT_BOX">
								<input id="purpose" name="dayBook.purpose" class="easyui-combobox" required="required" missingMessage="不能为空"/>
							</td>
						</tr>
						<tr>
							<td class="INPUT_TXT">交易日期</td>
							<td class="INPUT_BOX">
								<input id="tranDate" name="dayBook.tranDate" class="easyui-datebox" required="required" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">交易时间</td>
							<td class="INPUT_BOX">
								<input id="tranTime" name="dayBook.tranTime" class="easyui-timespinner" data-options="showSeconds:true"/>
							</td>
						</tr>
						<tr>
							<td class="INPUT_TXT">备注</td>
							<td class="INPUT_BOX" colspan=3>
								<input type="textarea" id="remark" name="dayBook.remark" class="easyui-textbox" data-options="multiline:true" style="width:100%;height:100px"/>
							</td>
						</tr>
					</table>
					<div style="text-align:center;padding:5px">
						<a class="easyui-linkbutton" id="submitbtn" icon="icon-ok" onclick="saveData();">提交</a>
						<a class="easyui-linkbutton" icon="icon-clear" onclick="clearData();">重置</a>
						<input type="hidden" id="opertype"/>
						<input type="hidden" id="id" name="dayBook.id"/>
					</div>
				</div>
				<div title="数据" data-options="iconCls:'icon-search',collapsed:false,collapsible:false" style="padding:10px;">
					<div id="info"></div>
					<div id="tb" style="padding:5px">
						资金方向<input id="gdirection" name="gdirection" class="easyui-combobox"/>
						&nbsp交易日期<input id="tb_date1" class="easyui-datebox" style="width:100px"/>
						至 <input id="tb_date2" class="easyui-datebox" style="width:100px"/>
						&nbsp<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryInfo()">查询</a>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>