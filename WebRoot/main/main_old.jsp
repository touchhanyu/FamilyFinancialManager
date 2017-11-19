<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	function buildTree(url, id) {
		$('#menu').tree({
			url: basePath + '/sys/' + url + '?pid=' + id,
			loadFilter: function(data) {
				return data;
			},
			onClick: function(node) {
				if (node.href != undefined && node.href != '') {
					if ($('#tabsPage').tabs('exists', node.text)) {// 已存在选中该标签页
						$('#tabsPage').tabs('select', node.text);
					} else {
						var url = basePath + node.href;
						var content = '<iframe id="mainPage" src="' + url
							+ '" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>';
						$('#tabsPage').tabs('add', {
							id: node.id,
							title: node.text,
							//href: basePath + node.href,
							content: content,
							closable: true
						});
					}
				}
			}
		});
	}
	function menuClick(obj) {
		if (obj.name == 'home') {
			window.location.reload();
		} else {
			buildTree(obj.name, obj.id);
		}
	}
	function logout() {
		var url = basePath + '/sys/logout';
		$.post(url, function() {
			window.location.reload();
		}, 'text');
	}
</script>
<title>家庭财务管理系统-Family Financial Managment</title>
</head>
<body>
	<div class="easyui-panel" style="padding:5px 0px 5px 5px;width:100%;border:1px solid #ccc">
		<a href="#" class="easyui-linkbutton" plain="true" id="" name="home" onclick="menuClick(this)">主页</a>
		<s:iterator value="menus" var="menu">
			<a href="#" class="easyui-linkbutton" plain="true" id='<s:property value="#menu.id"/>'
				name='menusList' onclick="menuClick(this)"><s:property value="#menu.text"/></a>
		</s:iterator>
		<a href="#" class="easyui-menubutton" menu="#mm1" iconCls="icon-edit">编辑</a>
		&nbsp&nbsp&nbsp&nbsp
		<span style="text-align:right">
			<a href="#" class="easyui-linkbutton" iconCls="icon-man" plain="true"><s:property value='#session.user.name'/></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-exit" plain="true" onclick="logout()">退出</a>
		</span>
	</div>
	<div id="mm1" style="width:160px;">
		<div iconCls="icon-undo">Undo</div>
		<div iconCls="icon-redo">Redo</div>
		<div class="menu-sep"></div>
		<div>Cut</div>
		<div>Copy</div>
		<div>Paste</div>
		<div class="menu-sep"></div>
		<div iconCls="icon-remove">Delete</div>
		<div>Select All</div>
	</div>
	<div class="easyui-layout" style="width:100%;height:580px;">
		<div region="west" title="系统信息" split="true" style="width:15%;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div id="menu" title="菜单" style="padding:5px;"></div>
				<div id="calendar" title="日历" data-options="collapsed:false,collapsible:false" style="padding:5px;">
					<div class="easyui-calendar"
						data-options="validator:function(date){if(date.getDay()==1){return true;}else{return false;}}"></div>
				</div>
			</div>
		</div>
		<div id="content" region="center" title="" style="padding:5px">
			<div id="tabsPage" class="easyui-tabs" data-options="fit:true,border:false,plain:true" style="width:400px;height:100px"></div>
		</div>
	</div>
</body>
</html>