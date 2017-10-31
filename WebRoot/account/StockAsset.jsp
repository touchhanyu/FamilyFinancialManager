<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/account/StockAsset.js"></script>
		<script type="text/javascript">
			var basePath = '${pageContext.request.contextPath}';
		</script>
	</head>
	<body>
		<form id="dataform">
			<div class="easyui-accordion" data-options="multiple:true" style="width:100%">
				<div title="资产信息" data-options="iconCls:'icon-tip',collapsed:false,collapsible:true" style="overflow:auto;padding:10px;">
					<table width="50%">
						<tr>
							<td>
								<font class="FONT_COM">当前资产：<span id="nowAsset"><s:property value="total"/></span></font>
							</td>
							<td>当前收益率：<font><span id="rate"></span></font></td>
						</tr>
						<tr>
							<td colspan="2"><font class="FONT_COM">投资本金：<span id="totalAsset"><s:property value="worth"/></span></font></td>
						</tr>
					</table>
				</div>
				<div title="录入信息" data-options="iconCls:'icon-edit',collapsed:false" style="overflow:auto;padding:10px;">
					<table align="center">
						<tr>
							<td class="INPUT_TXT">股票名称</td>
							<td class="INPUT_BOX">
								<input id="stockName" name="detail.detailId" required="required" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">交易类型</td>
							<td class="INPUT_BOX">
								<input id="tranType" name="detail.type" required="required" missingMessage="不能为空"/>
							</td>
						</tr>
						<tr>
							<td class="INPUT_TXT">交易价格</td>
							<td class="INPUT_BOX">
								<input id="price" name="detail.price" class="easyui-numberbox"
									data-options="min:0,precision:2,groupSeparator:','" required="required" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">交易数量</td>
							<td class="INPUT_BOX">
								<input id="num" name="detail.num" class="easyui-numberspinner"
									data-options="value:100,min:100,increment:100,formatter:currencyFormat" required="required" missingMessage="不能为空"/>
							</td>
						</tr>
						<tr>
							<td class="INPUT_TXT">交易日期</td>
							<td class="INPUT_BOX">
								<input id="tranDate" name="detail.tranDate" class="easyui-datebox" required="required" missingMessage="不能为空"/>
							</td>
							<td class="INPUT_TXT">交易手续费</td>
							<td class="INPUT_BOX">
								<input id="fee" name="detail.fee" class="easyui-numberbox" data-options="min:0,precision:2,groupSeparator:','" value="5"/>
							</td>
						</tr>
					</table>
					<div style="text-align:center;padding:5px">
						<a class="easyui-linkbutton" icon="icon-ok" onclick="saveData();">提交</a>
						<a class="easyui-linkbutton" icon="icon-clear" onclick="clearData();">重置</a>
					</div>
				</div>
				<div title="明细数据" data-options="iconCls:'icon-search',collapsed:false,collapsible:false" style="padding:10px;">
					<table id="stockinfo"></table>
					<div id="tb" style="padding:5px">
						&nbsp交易类型
						<select id="tb_type" class="easyui-combobox" panelHeight="auto" style="width:100px">
							<option value="1">买入</option>
							<option value="2">卖出</option>
						</select>
						&nbsp交易日期
						<input id="tb_date1" class="easyui-datebox" style="width:100px"/>
						 至 <input id="tb_date2" class="easyui-datebox" style="width:100px"/>
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryStock()">查询</a>
					</div>
				</div>
			</div>
		<input type="hidden" name="balance.id" value='<s:property value="balanceId"/>'/>
		</form>
	</body>
</html>