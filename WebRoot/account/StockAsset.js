$(function() {
	$('#dataform').form({
		url : basePath + '/account/saveUserStock',
		method : 'post',
		onSubmit : function() {
			if (!$('#dataform').form('validate')) {
				return false;
			}
			$.messager.progress();// 进度条
		},
		success : function() {
			$.messager.progress('close');
			$.messager.alert('提示', '保存成功');
			window.location.reload();
		}
	});
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
			if (param.q == null || param.q == undefined
					|| param.q.trim() == '')
				return false;
			param.q = param.q.trim();
		}
	});
	$('#tranType').combobox({
		panelHeight : 'auto',
		editable : false,
		value : '1',
		data : [ {
			text : '买入',
			value : '1'
		}, {
			text : '卖出',
			value : '2'
		} ]
	});
	$('#stockinfo').datagrid({
		url : basePath + '/account/userstock',
		method : 'post',
		fitColumns : true,
		rownumbers : true,
		autoRowHeight : true,
		singleSelect : true,
		toolbar : '#tb',
		columns : [ [ {
			field : 'id',
			title : 'ID',
			align : 'center',
			hidden : true
		}, {
			field : 'gid',
			title : '股票代码',
			align : 'center',
			hidden : true
		}, {
			field : 'gname',
			title : '股票名称',
			align : 'center',
			width : 100,
			formatter : function(value, row, index) {
				return row['detailValue'];
			},
			editor : {
				type : 'combobox',
				options : {
					url : basePath + '/stock/queryStockName',
					valueField : 'gid',
					textField : 'gname',
					panelHeight : 'auto'
				}
			}
		},{
			field : 'type',
			title : '交易类型',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				if (value == '1') {
					return '买入';
				} else {
					return '卖出';
				}
			}
		}, {
			field : 'price',
			title : '交易价格',
			align : 'center',
			width : 70
		}, {
			field : 'num',
			title : '交易数量',
			align : 'center',
			width : 70
		}, {
			field : 'fee',
			title : '交易手续费',
			align : 'center',
			width : 70
		}, {
			field : 'tranDate',
			title : '交易日期',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				var date = new Date(value);
				return dateFormat(date);
			}
		}, {
			field : 'oper',
			title : '操作',
			align : 'center',
			width : 50,
			formatter : function(value, row, index) {
				if (row.type == '2') {
					return '';
				}
				return '<a href="#" onclick="clickRowFn(\'d\',' + row.id + ');">删除</a>';
			}
		} ] ],
		pagination : true,
		pageSize : 10
	});
	assetFomat();
});
/**
 * 格式化资产数值
 */
function assetFomat() {
	var total = $('#totalAsset').html();
	var now = $('#nowAsset').html();
	var benifit = now - total;
	var rate = 0;
	if (total != 0) {
		rate = benifit * 100 / total;
	}
	$('#rate').html(rate.toFixed(2) + '%');
	if (benifit > 0) {
		$('#nowAsset').addClass("FONT_RED");
		$('#rate').addClass("FONT_RED");
	} else if (benifit < 0) {
		$('#nowAsset').addClass("FONT_GRN");
		$('#rate').addClass("FONT_GRN");
	} else {
		$('#nowAsset').addClass("FONT_BLK");
		$('#rate').addClass("FONT_BLK");
	}
}
function clickRowFn(type, id) {
	if (type == 'd') {
		$.messager.confirm('警告', '是否删除？', function(r) {
			if (r) {
				var url = basePath + '/account/removeUserStock?detail.id=' + id;
				$.post(url, function(data) {
					$.messager.alert('提示', '操作成功！');
					window.location.reload();
				}, 'text');
			}
		});
	}
}
function queryStock() {
	$('#stockinfo').datagrid('load', {
		type : $('#tb_type').combobox('getValue'),
		date1 : $('#tb_date1').datebox('getValue'),
		date2 : $('#tb_date2').datebox('getValue')
	});
}
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
function resetData() {
	$('#stockName').combobox('setValue', '');
	$('#tranDate').datebox('setValue', '');
	$('#price').numberbox('setValue', '');
	$('#num').numberspinner('setValue', '');
}