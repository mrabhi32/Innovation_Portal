<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var dataIds = [ "id", "challengeNo", "userId", 
			"persuePoints", "ideaGenerationPoints","prototypePoints","scaleupPoints","totalPoints","buddy", "startDate",  "endDate", "STATUS"];
	var tableHeaders = [ "ID","CHALLENGE NO", "USER ID",
			"ENROLL / ONBOARD", "END of GENERATION","COMPLETION of PROTOTYPE", "SUCCESSFUL Scale-Up","TOTAL","BUDDY", "START DATE",  "END DATE", "STATUS"];

	function loadTable(jsonData) {
		try{
			var divElement = document.getElementById('tableBody');
			divElement.removeChild(document.getElementById("jsonTable_wrapper"));
		}catch(e){
		}
		var datatable = createTable(jsonData.gridModel, dataIds, tableHeaders);
	}
	$(document).ready(
			function() {
				ajaxServerCall("ChallengePoints.action", "GET",
						null, loadTable);
			}
	);
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
    <div class="wrapper">   
      <div class="content-wrapper">
		<section class="content-header">
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
					<li class="active">Challeneg Point</li>
				</ol>			
			</section>
				<br>
			<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Challenge Points</h3>
				<%@include file="report.jsp"%>
				</div>
			</div>
      </div>
	  	<%@include file="../uni/footer.jsp"%>
    </div>
</body>
</html>

