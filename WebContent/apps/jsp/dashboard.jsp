<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var pie;
	var pieSize = 0;
	function handleChange(value) {
		window.location = "dashboardAction.action?method:setUpDashboard&selectedCompanyName="
				+ value; //or you can submit a form from here or make an ajax call
	}
	function getPieChart(value) {
		window.location = "dashboardAction.action?method:setUpDashboard&loadPieChart="
				+ value; //or you can submit a form from here or make an ajax call
	}
	function personalTracker() {
		window.location = "setUpPersonalTrackerDashboard";
	}
	
	$(document).ready(
			function() {
				loadPotentialPie();
			});
	function loadPie(chart){
		document.getElementById("chart-caption").innerHTML = chart.label;
		legend(document.getElementById("pieLegend"), chart.data);
		var canvasDiv = document.getElementById('chart');
		canvasDiv.removeChild(document.getElementById("chart-area"));
		canvas = document.createElement('canvas');
		canvas.setAttribute('width', 285);
		canvas.setAttribute('height', 285);
		canvas.setAttribute('id', 'chart-area');
		canvasDiv.appendChild(canvas);
	
		var ctx = document.getElementById("chart-area").getContext("2d");
		pieSize  = chart.data.length;
		pie = new Chart(ctx).Pie(chart.data);
	}
	
	function loadPotentialPie(){
		ajaxServerCall("resultPieChart.action", "GET",null, loadPie);
	}
	function loadCategoryPie(){
		if(document.getElementById("role").value == "potential"){
			loadPotentialPie();
		}else{
			ajaxServerCall("loadCategoryBasedPieChart.action", "GET",null, loadPie);
		}
	}
</script>
</head>

<body class="skin-blue">
	<%@include file="../uni/userConfig.jsp"%>
	<%-- <s:if test="%{userRole != null && userRole.equals('I7')}">
<div style="font-family: Arial;">
			<s:select name="Company List" list="compNames" headerKey="-1" headerValue="All Companies" 
				cssClass="selectbox_bg2"  onchange="handleChange(this.value)" value="selectedCompanyName" />
			<tr><td><s:hidden key="userRole"/>
			<input type="hidden" name="userRole"></input>
			</td></tr>
</div>
	<%@include file="I5_I7_dashboard.jsp"%>
</s:if>
<s:elseif test="%{userRole != null && userRole.equals('I-5')}">

	<%@include file="I5_I7_dashboard.jsp"%>
</s:elseif>
<s:else> --%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="wrapper">
		<div class="content-wrapper">
		
			<section class="content-header">
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>			
			</section>

			<!-- Main content -->
			<section class="content">
			<div class="panel panel-primary">
				<div class="panel-heading">PROGRESS OF CHALLENGES</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-2 col-md-2 col-lg-2">
							<!-- small box -->
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.totalChaInBank}" />
									</h3>
									<p>Challenges in Bank</p>
								</div>
								<a href="getChallengeEditListPage.action"
									class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-green">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.totalChaPersured}" />
									</h3>
									<p>Challenges being Pursued</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-2 col-md-2 col-lg-2">
							<!-- small box -->
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.totalchaClosed}" />
									</h3>
									<p>Challenge Closed</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-red">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.savingPotential}" />
									</h3>
									<p>Saving Potential(Million)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-2 col-md-2 col-lg-2">
							<!-- small box -->
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.savingActualized}" />
									</h3>
									<p>Savings Actualized (Million)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
					</div>
				</div>
			</div>


			<div class="panel panel-primary">
				<div class="panel-heading">PORTFOLIO OF CHALLENGE</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.individualCount}" />
									</h3>
									<p>Individual Orbit-shift (Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.individualAspiredCount}" />
									</h3>
									<p>Individual Orbit-shift (Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-green">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.functionalCount}" />
									</h3>
									<p>Functional Orbit-shift (Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.functionalAspiredCount}" />
									</h3>
									<p>Functional Orbit-shift(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.industrialCount}" />
									</h3>
									<p>Industry Orbit-shift(Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.industrialAspiredCount}" />
									</h3>
									<p>Industry Orbit-shift(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-red">
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.organisationalCount}" />
									</h3>
									<p>Organization Orbit-shift(Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{chaMatrixVO.organisationalAspiredCount}" />
									</h3>
									<p>Organization Orbit-shift(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
					</div>
					<!-- /.row -->
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">PEOPLE CAPABILITY MATRIX</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>
										<s:property value="%{manpowerEmp.innovattionStarter}" />
									</h3>
									<p>Innovation Starters(Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{manpowerVO.innovattionStarter}" />
									</h3>
									<p>Innovation Starters(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-green">
								<div class="inner">
									<h3>
										<s:property value="%{manpowerEmp.fs1}" />
									</h3>
									<p>FS1(Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{manpowerVO.fs1}" />
									</h3>
									<p>FS1(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>
										<s:property value="%{manpowerEmp.fs2}" />
									</h3>
									<p>FS2(Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{manpowerVO.fs2}" />
									</h3>
									<p>FS2(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-sm-3 col-md-3 col-lg-3">
							<!-- small box -->
							<div class="small-box bg-red">
								<div class="inner">
									<h3>
										<s:property value="%{manpowerEmp.fs3}" />
									</h3>
									<p>FS3(Actual)</p>
								</div>
								<div class="inner">
									<h3>
										<s:property value="%{manpowerVO.fs3}" />
									</h3>
									<p>FS3(Aspired)</p>
								</div>

								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">PIE CHART</div>
				<div class="panel-body">
					<div class="row">
						<section class="col-sm-12 col-md-12 col-lg-12"> <!-- Custom tabs (Charts with tabs)-->
						<div class="nav-tabs-custom">
							<!-- Tabs within a box -->
							<ul class="nav nav-tabs pull-right">
								<li class="active"><a href="#revenue-chart"
									data-toggle="tab">Area</a></li>
								<li><a href="#sales-chart" data-toggle="tab">Donut</a></li>
								<li class="pull-left header"><i class="fa fa-inbox"></i>
									Sales</li>
							</ul>
							<div class="tab-content no-padding">
								<!-- Morris chart - Sales -->
								<div class="chart tab-pane active" id="revenue-chart"
									style="position: relative; height: 300px;"></div>
								<div class="chart tab-pane" id="sales-chart"
									style="position: relative; height: 300px;"></div>
							</div>
						</div>
						</section>
					</div>
				</div>
			</div>
			<div class="row">
				<section class="col-sm-12 col-md-12 col-lg-12"> <!-- Calendar -->
				<div class="box box-solid bg-green-gradient">
					<div class="box-header">
						<i class="fa fa-calendar"></i>
						<h3 class="box-title">Calendar</h3>
						<!-- tools box -->
						<div class="pull-right box-tools">
							<!-- button with a dropdown -->
							<div class="btn-group">
								<button class="btn btn-success btn-sm dropdown-toggle"
									data-toggle="dropdown">
									<i class="fa fa-bars"></i>
								</button>
								<ul class="dropdown-menu pull-right" role="menu">
									<li><a href="#">Add new event</a></li>
									<li><a href="#">Clear events</a></li>
									<li class="divider"></li>
									<li><a href="#">View calendar</a></li>
								</ul>
							</div>
							<button class="btn btn-success btn-sm" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
							<button class="btn btn-success btn-sm" data-widget="remove">
								<i class="fa fa-times"></i>
							</button>
						</div>
						<!-- /. tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body no-padding">
						<!--The calendar -->
						<div id="calendar" style="width: 100%"></div>
					</div>
				</div>
				</section>
			</div>
			</section>
		</div>
	<%@include file="../uni/footer.jsp"%>
	</div>
</body>
</html>