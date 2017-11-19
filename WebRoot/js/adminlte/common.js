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
				makeGrid($el, param.columns, d.rows);
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
				htmlStr += '<li><a name="_menunode" href="#" data="' + row.href + '" level="' + row.text + '"><i class="fa ' + row.iconCls + '"></i> <span>' + row.text + '</span></a></li>';
			} else {
				var level = row.text;
				htmlStr += '<li class="treeview"><a name="_menunode" href="#" level="' + level + '"><i class="fa ' + row.iconCls + '"></i> <span>' + row.text + '</span>';
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
			htmlStr += '<li><a name="_menunode" href="#" data="' + children.href + '" level="' + tLevel + '"><i class="fa ' + children.iconCls + '"></i> ' + children.text + '</a></li>';
		} else {
			htmlStr += '<li class="treeview"><a name="_menunode" href="#" level="' + level + '"><i class="fa ' + children.iconCls + '"></i> <span>' + children.text + '</span>';
			htmlStr += '<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>';
			htmlStr += makeSubMenu(children.children, tLevel);
		}
	}
	htmlStr += '</ul>';
	return htmlStr;
}
function makeGrid(obj, columns, data) {
	var htmlStr = '<div class="col-md-6"><table class="table table-striped table-bordered">';
	htmlStr += '<thead><tr>';
	/* 表头 */
	for (var i = 0; i < columns.length; i++) {
		htmlStr += '<th name="' + columns[i].name + '_th"';
		if (columns[i].hide)
			htmlStr += ' style="display: none"';
		htmlStr += '>' + columns[i].title + '</th>';
	}
	htmlStr += '</tr></thead><tbody>';
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
	htmlStr += '</tbody></table></div>';
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