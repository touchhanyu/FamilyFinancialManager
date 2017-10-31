<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>家庭财务管理系统-Family Financial Managment</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/navbar.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/dashboard.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.5/css/carousel.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/hubspot-messenger/build/css/messenger.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/hubspot-messenger/build/css/messenger-theme-future.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hubspot-messenger/build/js/messenger.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hubspot-messenger/build/js/messenger-theme-future.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/main/main.js"></script>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	var ws;// WebSocket对象
	$(function() {
		Messenger().post({
			message : '<s:property value='#session.user.name'/>' + '，欢迎回来!',
			type : 'success',
			showCloseButton: true,
			hideAfter: 3
		});
	})
	function wsOpen() {
		if ('WebSocket' in window) {
			if (ws == null || ws == 'undefined') {
				ws = new WebSocket('wss://localhost:8443' + basePath + '/websocket');
				ws.onopen = function() {
					Messenger().post({
						message : '进入...',
						type : 'info',
						showCloseButton: true,
						hideAfter: 2
					});
				}
				ws.onmessage = function(evt) {
					Messenger().post({
						message : evt.data,
						type : 'info',
						showCloseButton: true,
						hideAfter: 2
					});
				}
				ws.onclose = function() {
					ws = null;
					Messenger().post({
						message : '退出...',
						type : 'info',
						showCloseButton: true,
						hideAfter: 2
					});
				}
			}
		} else {
		}
	}
	function wsClose() {
		ws.close();
	}
</script>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" class="disabled">FFM</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse navbar-left">
				<ul class="nav nav-pills" role="tablist" id="menuList"></ul>
			</div>
			<form class="navbar-form navbar-left" role="search">
				<input id="search" type="text" class="form-control" placeholder="搜索..."/>
			</form>
			<ul class="nav nav-pills navbar-right" role="tablist">
				<li><a href="#">个人设置</a></li>
				<li><a href="#">消息<span class="badge">3</span></a></li>
				<li><a href="#" onclick="wsOpen();">进入</a></li>
				<li><a href="#" onclick="wsClose();">退出</a></li>
			</ul>
		</div>
	<div id="alertDiv"></div>
	</nav>

	<div class="container-fluid">
   		<div class="row">
   			<div class="col-sm-3 col-md-2 sidebar">
   				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#">Overview</a></li>
					<li><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
   			</div>
   		</div>
   	</div>
   	<div class="container-fluid" id="mainfrme">
   		<ul class="nav nav-tabs" role="tablist">
   			<li role="presentation"><a>1</a></li>
   			<li role="presentation"><a>Message</a></li>
   		</ul>
   	</div>
<%-- 
	<div class="container-fluid">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#" data-slide-to="0" class="active"></li>
				<li data-target="#" data-slide-to="1"></li>
				<li data-target="#" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img class="first-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="First slide">
					<div class="container">
						<div class="carousel-caption">
							<h1>Example headline.</h1>
							<p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>
							<p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
						</div>
					</div>
				</div>
				<div class="item">
					<img data-src="holder.js/1140x500/auto/#666:#444/text:Second slide" alt="Second slide">
				</div>
				<div class="item">
					<img data-src="holder.js/1140x500/auto/#555:#333/text:Third slide" alt="Third slide">
				</div>
			</div>
			<a class="left carousel-control" href="#" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	 --%>
</body>
</html>