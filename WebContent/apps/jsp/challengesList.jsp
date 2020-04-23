<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var dataIds = [ "id", "plant", "challenge", "challengeType", "category",
			"potentialRsMil", "userId", "status" ];
	var tableHeaders = [ "ID", "PLANT", "CHALLENGE", "CHALLENGE TYPE",
			"CATEGORY", "POTENTIAL-RS-MIL", "USER ID", "STATUS" ];
	var datatable;
	function loadTable(jsonData) {
		datatable = createTable(jsonData.gridModel, dataIds, tableHeaders);
	}
	$(document).ready(
			function() {
				ajaxServerCall("ReportAction?method:getChallengeList", "GET",
						null, loadTable);
			});
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	   <div class="wrapper">
      
      <!-- Right side column. Contains the navbar and content of the page -->
      <div class="content-wrapper">
		<section class="content-header">
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
					<li class="active">Challenge List</li>
				</ol>			
			</section>
				<br>
			<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Challenge List</h3>
				</div>
			</div>
      </div>
	  	<%@include file="../uni/footer.jsp"%>
    </div>
</body>
</html>

