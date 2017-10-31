<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/color.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.4/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.4/jquery.easyui.mobile.js"></script>
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
</script>
<title>Family Financial Managment</title>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-buttongroup m-buttongroup-justified" style="width:100%">
				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" id="" name="home" onclick="menuClick(this)">主页</a>
				<s:iterator value="menus" var="menu">
					<a href="#" class="easyui-linkbutton" plain="true" id='<s:property value="#menu.id"/>'
						name='menusList' onclick="menuClick(this)"><s:property value="#menu.text"/></a>
				</s:iterator>
			</div>
		</header>
		<div class="easyui-accordion" fit="true" border="false">
			<div title="菜单">
				<div id="menu" data-options="animate:true"></div>
			</div>
			<div title="页面">
				<div id="tabsPage" class="easyui-tabs" data-options="fit:true,border:false,plain:true"></div>
			</div>
		</div>
	</div>
</body>
</html>