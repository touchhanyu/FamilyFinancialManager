/**
 * 判断浏览器类型
 */
function browserVersion() {
	var agent = navigator.userAgent;
	var app = navigator.appVersion;
	var clientVerion = '', mobile = false, ios = false;
	if (agent.indexOf('Trident') > -1) {// IE内核
		clientVerion = 'IE';
	} else if (agent.indexOf('Edge') > -1) {// WIN10 Edge
		clientVerion = 'Edge';
	} else if (agent.indexOf('Chrome') > -1) {// Chrome谷歌
		clientVerion = 'Chrome';
	}
	if (agent.match(/AppleWebKit.*Mobile.*/) != null) {// 是否为移动终端
		mobile = true;
	}
	if (agent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)) {// ios终端
		ios = true;
	}
	if (agent.indexOf('Android') > -1 || agent.indexOf('Linux') > -1) {// android终端或者uc浏览器
		if (mobile)
			clientVerion = 'Android';
	}
	if (ios) {
		if (agent.indexOf('iPhone') > -1) {
			clientVerion = 'iPhone';
		} else if (agent.indexOf('iPad') > -1) {
			clientVerion = 'iPad';
		}
	}
	if (agent.indexOf('MicroMessenger') > -1) {// 微信
		clientVerion = 'WeChat';
	}
//	if (clientVerion == '')
//		alert(agent);
	return clientVerion;
}
/**
 * 登记当前用户状态
 */
function regUserStatus() {
	
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
	var isNegate = false;// 是否负数
	if (amount < 0) {
		isNegate = true;
		amount = -amount;
	}
	amount = (amount + '');
	var index = amount.indexOf('.');
	var num;
	var res = '';
	if (index > -1) {
		num = amount.substring(0, index);// 整数位
		res = amount.substring(index, amount.length);// 小数位
	} else {
		num = amount;
	}
	while (true) {
		if (num.length < 4) {
			res = num + res;
			break;
		}
		res = num.substring(num.length - 3, num.length) + res;// 取最后三位
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
/**
 * 初始化combobox
 * 
 * @param inputId 控件ID
 * @param type 数据字典类型
 */
function initCombobox(inputId, url) {
	$(inputId).combobox({
		url : url,
		valueField : 'dictCode',
		textField : 'dictValue',
		panelHeight : 'auto'
	});
}

$.fn.extend({
	menulist : function(param) {
		var $el = this;
		$.post(param.url, param.param, function(data) {
			$el.append(buildMenu(data, param));
			$('a[name=_menulist]').click(param, function() {
				_menulistClick(this, param);
			});
		}, 'json');
	},
	alert : function(param) {
		var $el = this;
		buildAlert($el, param.text, param.type);
	}
});
/**
 * 生成菜单
 * 
 * @param menuList
 * @returns {String}
 */
function buildMenu(menuList) {
	var html = '';
	if (menuList != null && menuList.length > 0) {
		for (var i = 0; i < menuList.length; i++) {
			var menu = menuList[i];
			html += '<li role="presentation" class="dropdown" role="presentation">';
			html += '<a id='+ menu.id + ' name=_menulist data="' + menu.href + ' "';
			html += 'class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup=true aria-expanded=false>' + menu.text;
			var flag = false;
			if ((menu.state || menu.state == 'true') && menu.children != null) {
				flag = true;
				html += ' <span class="caret">';
			}
			html += '</span></a>';
			if (flag)
				html += '<ul class="dropdown-menu" aria-labelledby=dropdownMenu>' + buildSubMenu(menu) + '</ul>';
			html += '</li>';
		}
	}
	return html;
}
/**
 * 生成子菜单
 * 
 * @param menu
 * @returns {String}
 */
function buildSubMenu(menu) {
	var html = '';
	var state = menu.state;
	var children = menu.children;
	if (children == null || children == undefined) {
		html += '<li><a name=_menulist data="' + menu.href + '">' + menu.text + '</a></li>';
		return html;
	}
	for (var i = 0; i < children.length; i++) {
		var submenu = children[i];
		var sstate = submenu.state;
		if (sstate == 'true') {
			html += '<li class=dropdown-submenu role=presentation>';
			html += '<a class=dropdown-toggle data-toggle=dropdown role=button aria-haspopup=true aria-expanded=false>';
			html += submenu.text + '</a><ul class=dropdown-menu>';
			html += buildSubMenu(submenu);
			html += '</ul></li>';
		} else {
			html += '<li><a name=_menulist data="' + submenu.href + '">' + submenu.text + '</a></li>';
		}
	}
	return html;
}
/**
 * 菜单单击事件
 * 
 * @param obj
 * @param param
 */
function _menulistClick(obj, param) {
	param.onclick(obj);
}
/**
 * 
 * @param str
 */
function buildAlert(obj, str, type) {
	var $divObj = $(document.createElement("div"));
	if (type == '1') {
		$divObj.addClass("alert alert-info");
	} else if (type == '2') {
		$divObj.addClass("alert alert-success");
	} else if (type == '3') {
		$divObj.addClass("alert alert-warning");
	} else if (type == '4') {
		$divObj.addClass("alert alert-danger");
	}
	$divObj.attr("role", "alert");
	$divObj.html(str);
	$divObj.hide();
	obj.append($divObj);
	$divObj.slideToggle("slow").delay(1000).slideToggle("slow");
}