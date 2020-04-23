<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
var dataIds = [ "id", "empName",  "userName", "role","responsiblityLevel",  "capability", "buddy" ];
var tableHeaders = [ "ID", "FULL NAME" ,"USER ID", "ROLE" , "RESPONSIBLITY",   "CAPABILITY", "BUDDY"];
	var datatable;
	function loadTable(jsonData) {
		datatable = createTable(jsonData.gridModel, dataIds, tableHeaders);
		datatable.makeEditable({
			sUpdateURL:"EditIEmployeeCapability.action",
			fnOnEdited: function(status)
			{ 	
				ajaxServerCall("ReportAction?method:getUserList", "GET",null, loadTable);
			},
			"aoColumns":[
						    null,
						    null,
						    null,
						    null,
						    null,
						    {
						    	type:'select',
						        onblur:'cancel',
						        submit: 'Update',
						        event:'click',
						        data : "{'FS1':'FS1','FS2':'FS2','FS3':'FS3'}",
						        "submitdata" : function(){
						        	return {
						        		"userId": document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[2].innerText 
						        		,"capability":document.getElementsByName("value")[0].value
						        	}
						        }

						    },
						    {
						    	type:'select',
						        onblur:'cancel',
						        submit: 'Update',
						        event:'click',
						        data : "{'YES':'YES','NO':'NO'}",
						        "submitdata" : function(){
						        	return {
						        		"userId": document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[2].innerText 
						        		,"buddy":document.getElementsByName("value")[0].value
						        	}
						        }

						    }
						]
			});
	}
	$(document).ready(
			function() {
				ajaxServerCall("ReportAction?method:getUserList", "GET",
						null, loadTable);
			});
	
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="content">
		<!-- <div class="header">
			<ul class="breadcrumb">
				<li><a href="dashboardAction?method:setUpDashboard">DashBoard</a>
				</li>
				<li><a href="#"></a>Reports</li>
				<li class="active">Challenges List</li>
			</ul>

		</div> -->
		<div class="main-content">
			<div class="panel panel-default">
				<%@include file="report.jsp"%>
			</div>
			<!-- container -->
		</div>
			<%@include file="../uni/footer.jsp"%>
	</div>
	<div id = "editForm" style="display: none;">
			<%@include file="editChallengeForm.jsp"%>
	</div>
</body>
</html>

