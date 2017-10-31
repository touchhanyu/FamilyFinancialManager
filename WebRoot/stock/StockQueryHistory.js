$(function() {
	$('#stockName').combobox({
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
	$('#stockhistory').datagrid({
		url : basePath + '/stock/queryStockHistory',
		method : 'post',
		fitColumns : true,
		rownumbers : true,
		autoRowHeight : false,
		striped : true,
		pageSize : 50,
		pageList : [30, 50, 100],
		pagination : true,
		columns : [ [ {
			field : 'gid',
			title : '股票编号',
			align : 'center',
			hidden : true
		}, {
			field : 'name',
			title : '股票名称',
			align : 'center',
			width : 50
		}, {
			field : 'publicDate',
			title : '发布日期',
			align : 'center',
			width : 50,
			formatter : function(value, row, index) {
				var date = new Date(value);
				return dateFormat(date);
			}
		}, {
			field : 'todayMax',
			title : '当日最高价',
			align : 'center',
			width : 40,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'todayMin',
			title : '当日最低价',
			align : 'center',
			width : 40,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'nowPri',
			title : '当日收盘价格',
			align : 'center',
			width : 40,
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
			field : 'traNumber',
			title : '成交量',
			align : 'center',
			width : 50,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'traAmount',
			title : '成交金额',
			align : 'center',
			width : 50,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'sub1',
			title : '当日浮动值',
			align : 'center',
			width : 40,
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		} ] ]
	});
});
function queryHistory() {
	var gid = $('#stockName').combobox('getValue');
	var startDate = $('#startDay').datebox('getValue');
	var endDate = $('#endDay').datebox('getValue');
	$('#stockhistory').datagrid('load', {
		q : gid,
		startDate : startDate,
		endDate : endDate
	});
}