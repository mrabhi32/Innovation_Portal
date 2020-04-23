<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var dataIds = ["id","challenge","frame","idea","investment","implementationTime","priority","ideaStatus"];
	var tableHeaders = ["ID","<div style='padding-left:5em;padding-right:5em;'>CHALLENGE</div>","FRAME","<div style='padding-left:8em;padding-right:8em;'>IDEA</div>","INVESTMENT","IMPLEMENTATION TIME","PRIORITY","STATUS"];

	function refreshGrid(){
		ajaxServerCall("getOpenChallengeIdeas", "GET",null, loadTable);
	}
	function loadTable(jsonData) {
		var datatable = createTable(jsonData.gridModel,dataIds,tableHeaders);
		datatable.makeEditable({
			//sUpdateURL:"EditIdeaStatus.action",
			sUpdateURL:function(value, settings)
            {
				var id = document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[0].innerText;
        		var ideaStatus = document.getElementsByName("value")[0].value;
			    ajaxServerCall("EditIdeaStatus.action?ideaVo.id="+id+"&ideaVo.ideaStatus="+ideaStatus, "GET",null, refreshGrid);
            },
			fnOnEdited: function(status)
			{ 	
				//alertify.set({ delay: 30000 });
				//alertify.success("Edit action finished.");
				//ajaxServerCall("getOpenChallengeIdeas", "GET",null, loadTable);
			},
			"aoColumns":[
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    {
			        type:'select',
			        onblur:'cancel',
			        submit: 'Save',
			        data : "{'':'Please select...','Catogorized in to A, B, C, D':'Catogorized in to A, B, C, D','Prototyping the Intent':'Prototyping the Intent','Escalated to Plant Head / COO':'Escalated to Plant Head / COO','Idea Scale-Up':'Idea Scale-Up','ITransfer to idea Bank':'ITransfer to idea Bank','Closed':'Closed'}",
			        event:'click',
			        "submitdata" : function(){
			        	/* return {
			        		"ideaVo.id": document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[0].innerText,
			        		"ideaVo.ideaStatus":document.getElementsByName("value")[0].value
			        	} */

			    }
			    }
			]
			});
	}
	$(document).ready(
			function() {
				ajaxServerCall("getOpenChallengeIdeas", "GET",
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
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
					<li class="active">Track Idea</li>
				</ol>			
			</section>
				<br>
			<div class="main-content">
				<div class="panel panel-primary">
					<h3 class="panel-heading">Track Idea </h3>
					<%@include file="report.jsp"%>
				</div>
			</div>
      </div>
	  <%@include file="../uni/footer.jsp"%>
    </div>
</body>
</html>

