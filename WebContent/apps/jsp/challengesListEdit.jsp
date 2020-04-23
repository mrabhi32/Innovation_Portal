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
		datatable.makeEditable({
			sUpdateURL:"EditChallengeStatus.action",
			fnOnEdited: function(status)
			{ 	
				//alertify.set({ delay: 30000 });
				//alertify.log(status);
				ajaxServerCall("ReportAction?method:getChallengeList", "GET",null, loadTable);
			},
			"aoColumns":[
			    null,
			    null,
			    {
			        type:'textarea',
			        onblur:'cancel',
			        submit: 'Update',
			        //event:'click',
			        "submitdata" : function(){
			        	return {
			        		"challengeVo.id": document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[0].innerText 
			        		,"challengeVo.challenge":document.getElementsByName("value")[0].value
			        	}
			        }

			    },
			    null,
			    null,
			    null,
			    null,
			    null
			]
			});
	}
	$(document).ready(
			function() {
				ajaxServerCall("ReportAction?method:getChallengeList", "GET",
						null, loadTable);
			});
	
/* 	function editRow() {
        document.getElementById('Id').value=rowData[0];
        document.getElementById('challenge').value=rowData[2];
        document.getElementById('challengeType').value=rowData[3];
        document.getElementById('category').value=rowData[4];
        document.getElementById('potentialRsMil').value=rowData[5];
        
        
        $("#editForm").dialog({
	        autoOpen : true,
	        height: 800,
	        width : 600,
	        model : true
        });
    }  */
	
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="content">
		<section class="content-header">
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
					<li class="active">Challenge List</li>
				</ol>			
			</section>
				<br>
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

