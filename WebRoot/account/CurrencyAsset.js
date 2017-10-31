$(function() {
	$('#info').datagrid({
		url : basePath + '/currency/currencyinfo',
		method : 'post',
		fitColumns : true,
		rownumbers : true,
		autoRowHeight : true,
		singleSelect : true,
		columns : [ [ {
			field : 'id',
			title : 'ID',
			align : 'center',
			hidden : true
		}, {
			field : 'name',
			title : '货币名称',
			align : 'center',
			hidden : true
		}, {
			field : 'fBuyPri',
			title : '现汇买入价',
			align : 'center',
			width : 70
		}, {
			field : 'mBuyPri',
			title : '现钞买入价',
			align : 'center',
			width : 70
		}, {
			field : 'fSellPri',
			title : '现汇卖出价',
			align : 'center',
			width : 70
		}, {
			field : 'mSellPri',
			title : '现钞卖出价',
			align : 'center',
			width : 70
		}, {
			field : 'bankConversionPri',
			title : '银行折算价/中间价',
			align : 'center',
			width : 70
		}, {
			field : 'date',
			title : '发布日期',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				var date = new Date(value);
				return dateFormat(date);
			}
		}, {
			field : 'time',
			title : '发布时间',
			align : 'center',
			width : 70
		} ] ],
		pagination : true,
		pageSize : 10
	});
});
function saveData() {
	$.messager.confirm('警告', '是否提交保存？', function(r) {
		if (r) {
			$('#dataform').submit();
		}
	});
}
function clearData() {
	$.messager.confirm('警告', '是否重置表单数据？', function(r) {
		if (r) {
			resetData();
		}
	});
}