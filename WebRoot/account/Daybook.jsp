<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/account/Daybook.js"></script>
</head>
<body>
	<section class="content">
		<form>
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">录入信息</h3>
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>资金方向</label>
								<select id="direction" class="form-control select2" style="width: 100%;"/>
							</div>
							<div class="form-group">
								<label>交易账户</label>
								<select id="type" class="form-control select2" style="width: 100%;"/>
							</div>
							<div class="form-group">
								<label>交易日期</label>
								<div class="input-group date date-picker">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control pull-right" id="datepicker"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>金额</label>
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-cny"></i></span>
									<input type="text" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label>用途</label>
								<select id="purpose" class="form-control select2" style="width: 100%;"/>
							</div>
							<div class="bootstrap-timepicker">
								<div class="form-group">
									<label>交易时间</label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-clock-o"></i>
										</div>
										<input id="timepicker" type="text" class="form-control pull-right timepicker"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>备注</label>
								<textarea id="remark" class="form-control" rows="4"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<center>
						<button id="submitbtn" type="submit" class="btn btn-flat btn-primary">提交</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button id="canclebtn" class="btn btn-flat btn-danger" data-toggle="modal" data-target="#modal-default">重置</button>
					</center>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">JQuery DataTable</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</div>
					</div>
					<div class="box-body">
						<table id="datatable" class="table table-striped table-bordered"></table>
					</div>
				</div>
			</div>
		</form>
		<div class="modal fade" id="modal-default">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Default Modal</h4>
					</div>
					<div class="modal-body">
						<p>One fine body&hellip;</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save changes</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>