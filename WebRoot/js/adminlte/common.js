$.fn.extend({
	adminmenu : function(param) {
		var $el = this;
		if (param.type == null || param.type == undefined)
			param.type = 'GET';
		if (param.dataType == null || param.dataType == undefined)
			param.dataType = 'json';
		$.ajax({
			url : param.url,
			type : param.type,
			data : param.data,
			dataType : param.dataType,
			success : function(d) {
				var htmlStr = makeMenu(param.header, d);
				$el.append(htmlStr);
				$('a[name=_menunode]').click(this, function() {
					makeBreadcrumb(this);
					param.onclick(this);
				});
			}
		});
	},
	grid : function(param) {
		var $el = this;
		if (param.type == null || param.type == undefined)
			param.type = 'GET';
		if (param.dataType == null || param.dataType == undefined)
			param.dataType = 'json';
		$.ajax({
			url : param.url,
			type : param.type,
			data : param.data,
			dataType : param.dataType,
			success : function(d) {
				makeGrid($el, param, d.rows);
			}
		});
	}
});
function makeMenu(header, data) {
	var htmlStr = '<li class="header">' + header + '</li>';
	if (data != null && data != undefined) {
		for (var i = 1; i <= data.length; i++) {
			var row = data[i - 1];
			if (row.href == null || row.href == undefined)
				row.href = '#';
			if (row.text == null || row.text == undefined)
				row.text = '';
			if (row.iconCls == null || row.iconCls == undefined)
				row.iconCls = 'fa-circle-o';
			if (row.children == null) {
				htmlStr += '<li><a id="_menuid_' + row.id + '" name="_menunode" href="#" data="' + row.href + '" level="' + row.text + '"><i class="fa ' + row.iconCls + '"></i> <span>' + row.text + '</span></a></li>';
			} else {
				var level = row.text;
				htmlStr += '<li class="treeview"><a id="_menuid_' + row.id + '" name="_menunode" href="#" level="' + level + '"><i class="fa ' + row.iconCls + '"></i> <span>' + row.text + '</span>';
				htmlStr += '<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>';
				htmlStr += makeSubMenu(row.children, level);
			}
		}
	}
	return htmlStr;
}
/**
 * 生成子菜单
 */
function makeSubMenu(childrens, level) {
	var htmlStr = '<ul class="treeview-menu">';
	for (var i = 1; i <= childrens.length; i++) {
		var children = childrens[i - 1];
		var tLevel = level + ',' + children.text;
		if (children.href == null || children.href == undefined)
			children.href = '#';
		if (children.text == null || children.text == undefined)
			children.text = '';
		if (children.iconCls == null || children.iconCls == undefined)
			children.iconCls = 'fa-circle-o';
		if (children.children == null || children.children == undefined) {
			htmlStr += '<li><a id="_menuid_' + children.id + '" name="_menunode" href="#" data="' + children.href + '" level="' + tLevel + '"><i class="fa ' + children.iconCls + '"></i> ' + children.text + '</a></li>';
		} else {
			htmlStr += '<li class="treeview"><a id="_menuid_' + children.id + '" name="_menunode" href="#" level="' + level + '"><i class="fa ' + children.iconCls + '"></i> <span>' + children.text + '</span>';
			htmlStr += '<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>';
			htmlStr += makeSubMenu(children.children, tLevel);
		}
	}
	htmlStr += '</ul>';
	return htmlStr;
}
function makeGrid(obj, param, data) {
	/* 标题 */
	var htmlStr = '<div class="box-header">';
	if (param.icon != null && param.icon != undefined) {
		htmlStr += '<i class="ion ' + param.icon + '">';
	}
	htmlStr += '</i><h3 class="box-title">';
	if (param.title != null && param.title != undefined) {
		htmlStr += '';
	} else {
		htmlStr += param.title;
	}
	htmlStr += '</h3><div class="box-tools pull-right"><div class="input-group input-group-sm" style="width: 150px;">';
	htmlStr += '<input type="text" name="table_search" class="form-control pull-right" placeholder="Search"><div class="input-group-btn">';
	htmlStr += '<button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button></div></div></div>';
	/**/
	htmlStr += '<div class="box-body no-padding"><table class="table table-striped"><tr>';
	var columns = param.columns; // 表头格式
	/* 表头 */
	for (var i = 0; i < columns.length; i++) {
		htmlStr += '<th name="' + columns[i].name + '_th" style="display: ';
		if (columns[i].hide) { // 是否隐藏
			htmlStr += 'none';
		} else {
			htmlStr += 'inline';
		}
		if (columns[i].width != null && columns[i].width != undefined) { // 列宽
			htmlStr += ';width: 10px';
		}
		htmlStr += '">' + columns[i].title + '</th>';
	}
	htmlStr += '</tr>';
	for (var i = 0; i < data.length; i++) {
		htmlStr += '<tr>';
		var row = data[i];
		for (var j = 0; j < columns.length; j++) {
			var value = row[columns[j].field];
			if (value == null || value == undefined)
				value = '';
			htmlStr += '<td name="' + columns[j].name + '"';
			if (columns[j].hide)
				htmlStr += ' style="display: none"';
			htmlStr += '>'
			if (columns[j].value == null || columns[j].value == undefined) {
				htmlStr += value + '</td>';
			} else {
				htmlStr += columns[j].value + '</td>';
			}
		}
		htmlStr += '</tr>';
	}
	htmlStr += '</table></div>';
	htmlStr += '<div class="box-footer clearfix no-border"><ul class="pagination pagination-sm no-margin pull-right">';
	htmlStr += '<li><a href="#">1</a></li>';
	htmlStr += '</ul></div>';
	obj.append(htmlStr);
}
function makeBreadcrumb(param) {
	var $param = $(param);
	var level = $param.attr("level");
	var htmlStr = '<h1>' + param.text + '</h1><ol class="breadcrumb"><li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>';
	var href = $param.attr("data");
	if (href != null && href != undefined) {
		var levels = level.split(',');
		for (var i = 0; i < levels.length; i++) {
			if (i == levels.length - 1) {
				htmlStr += '<li class="active">' + levels[i] + '</li>';
			} else {
				htmlStr += '<li><a href="#">' + levels[i] + '</a></li>';
			}
		}
		htmlStr += '</ol>';
		$('#content-header').html(htmlStr);
	}
}
/**
 * 货币格式化
 * 
 * @param amount
 * @returns
 */
function currencyFormat(amount) {
	if (isNaN(amount)) {
		return amount;
	}
	var isNegate = false; // 是否负数
	if (amount < 0) {
		isNegate = true;
		amount = -amount;
	}
	amount = (amount + '');
	var index = amount.indexOf('.');
	var num;
	var res = '';
	if (index > -1) {
		num = amount.substring(0, index); // 整数位
		res = amount.substring(index, amount.length); // 小数位
	} else {
		num = amount;
	}
	while (true) {
		if (num.length < 4) {
			res = num + res;
			break;
		}
		res = num.substring(num.length - 3, num.length) + res; // 取最后三位
		num = num.substring(0, num.length - 3);
		if (num.length > 0) {
			res = ',' + res;
		}
	}
	if (isNegate)
		res = '-' + res;
	return res;
}
/**
 * 日期格式化
 * 
 * @param date
 * @returns {String}
 */
function dateFormat(date) {
	date = new Date(date);
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	if (m < 10) {
		m = '0' + m;
	}
	var d = date.getDate();
	if (d < 10) {
		d = '0' + d;
	}
	return y + '-' + m + '-' + d;
}