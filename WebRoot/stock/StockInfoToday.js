$(function() {
	$('#stockname').combobox({
		url : basePath + '/stock/queryStockName',
		valueField : 'name',
		textField : 'gname',
		mode : 'remote',
		hasDownArrow : false,
		delay : 400,
		prompt : '股票名称或拼音',
		iconCls : 'icon-search',
		iconAlign : 'right',
		formatter : function(row) {
			var html = "<table><tr><td width='50%'><font style='font-size:small'>";
			html += row.gname + "</font></td><td><font style='text-align:right;font-size:x-small;color:green;'>";
			html += row.gid + "<font/>&nbsp;</td></tr></table>";
			return html;
		},
		onBeforeLoad : function(param) {
			if (param.q == null || param.q == undefined || param.q.trim() == '')
				return false;
			param.q = param.q.trim();
		}
	});
	$('#info').datagrid({
		url : basePath + '/stock/queryTodayStock',
		method : 'post',
		title : '股票信息',
		fitColumns : true,
		rownumbers : true,
		autoRowHeight : false,
		singleSelect : true,
		toolbar : '#tb',
		columns : [ [ {
			field : 'gid',
			title : '股票编号',
			align : 'center',
			hidden : true
		}, {
			field : 'name',
			title : '股票名称',
			align : 'center',
			width : 70
		}, {
			field : 'gType',
			title : '涨跌标志',
			hidden : true
		}, {
			field : 'nowPri',
			title : '当前价格',
			align : 'center',
			width : 70,
			editor : {
				type : 'numberbox'
			},
			formatter : function(value, row, index) {
				var amount;
				if (row.gType == 'red') {
					amount = '<font color=red>' + currencyFormat(value) + '↑';
				} else if (row.gType == 'green') {
					amount = '<font color=green>' + currencyFormat(value) + '↓';
				} else {
					amount = '<font>' + currencyFormat(value);
				}
				return amount + '</font>';
			}
		}, {
			field : 'todayMax',
			title : '今日最高价',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'todayMin',
			title : '今日最低价',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'traNumber',
			title : '成交量',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'traAmount',
			title : '成交金额',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
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
		onClickRow : function(index, row) {
			$('#stockdetail').propertygrid('load', {
				q : row.gid
			});
		}
	});
	$('#stockdetail').propertygrid({
		url : basePath + '/stock/queryTodayStockDetail',
		method : 'get',
		title : '股票明细信息',
		showGroup : true,
		scrollbarSize : 0,
		columns : [ [ {
			field : 'id',
			title : 'ID',
			width : 100,
			hidden : true
		}, {
			field : 'dictCode',
			title : 'DICTCODE',
			width : 100,
			hidden : true
		}, {
			field : 'name',
			title : '指标名称',
			width : 100,
			align : 'center',
		}, {
			field : 'value',
			title : '指标值',
			width : 100,
			align : 'center',
			disable : true,
			formatter : function(value, row, index) {
				if (row.dictCode == 'gid') {
					return value;
				}
				return currencyFormat(value);
			}
		} ] ]
	});
})
function queryInfo() {
	var gid = $('#stockname').combobox('getValue');
	$('#info').datagrid('load', {
		q : gid
	});
}