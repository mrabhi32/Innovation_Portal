<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var dataIds = [ "challenge_id", "persue_points", "idea_generation_points", "prototype_points",
			"scaleup_points", "total_points" , "last_updated_date" , "last_updated_by" ];
	var tableHeaders = [ "CHALLENGE NO", "ENROLLED-PERSUED CHALLENGE", "END OF IDEA GENERATION",
			"COMPLETION OF PROTOTYPE", "SUCCESSFULLY SCALE-UP", "TOTAL PONTS IN BANK" , "LAST UPDATED DATE" ,"LAST UPDATED BY" ];
	var table;
	function loadTable(jsonData) {
		table = createTable(jsonData.gridModel, dataIds, tableHeaders);
	}
	$(document).ready(
			function() {
				ajaxServerCall("personalPointsTracker", "GET",
						null, loadTable);
			});
</script>
</head>
<body class="skin-blue">
	<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="wrapper">
		<div class="content-wrapper">
			<section class="content-header">
				<br>
				<ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i>Home</a></li>
					<li class="active">Dashboard</li>
					<li class="active">Personal Tracker</li>
				</ol>
			</section>
			<br>
			<div class="main-content">
				<div class="panel panel-Primary">
					<h3 class="panel-heading">Value Creation Track-record -Opportunity Realisation</h3>
					<table class="table list">
						<tbody>
							<tr>
								<td style="width: 35%">
									<p class="text-info  pull-left" style="margin-top: 1px;">Challenges Pursued (NOs)</p>
								</td>
								<td></td>
								<td>
									<p class="stat text-danger h3 pull-left"
										style="margin-top: 1px;">
										<span class="label label-primary"> 
										<s:property value="%{trackerVO.challengesPursued}"/>
										</span>
								</td>
								<td>
									<p class="text-info  pull-left" style="margin-top: 1px;">Idea Generated (NOs)</p>
								</td>
								<td></td>
								<td>
									<p class="stat text-danger h3 pull-left"
										style="margin-top: 1px;">
										<span class="label label-primary">
										 <s:propertyv value="%{trackerVO.ideaGenerated}" />
										</span>
								</td>
							</tr>
							
							<tr>
								<td>
									<p class="text-info  pull-left" style="margin-top: 1px;">Savings Potential (INR)</p>
								</td>
								<td></td>
								<td>
									<p class="stat text-danger h3 pull-left"
										style="margin-top: 1px;">
										<span class="label label-primary"> 
										<s:property value="%{trackerVO.savingPotential}" />
										</span>
								</td>
								<td>
									<p class="text-info  pull-left" style="margin-top: 1px;">Ideas Selected For Prototyping (NOs)</p>
								</td>
								<td></td>
								<td>
									<p class="stat text-danger h3 pull-left"
										style="margin-top: 1px;">
										<span class="label label-primary">
										<s:property	value="%{trackerVO.ideaProtyping}" />
										</span>
								</td>
							</tr>
							
							<tr>
								<td>
									<p class="text-info  pull-left" style="margin-top: 1px;">Savings Actualized (INR)</p>
								</td>
								<td></td>
								<td>
									<p class="stat text-danger h3 pull-left"
										style="margin-top: 1px;">
										<span class="label label-primary"> 
										<s:property	value="%{trackerVO.savingActualized}" />
										</span>
								</td>

								<td>
									<p class="text-info  pull-left" style="margin-top: 1px;">Idea Implemented (NOs)</p>
								</td>
								<td></td>
								<td>
									<p class="stat text-danger h3 pull-left"
										style="margin-top: 1px;">
										<span class="label label-primary">
										<s:property	value="%{trackerVO.ideaImplemented}" /></span>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel panel-primary">
				<h3 class="panel-heading">Challenge Bank & Reward Status</h3>
			</div>
		</div>
	<%@include file="../uni/footer.jsp"%>
</div>
</body>
</html>

